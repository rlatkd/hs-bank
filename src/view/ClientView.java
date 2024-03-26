package view;

import dto.account.GetAccountDto;
import dto.account.RegisterAccountDto;
import dto.account.RemoveAccountDto;
import dto.inquiry.RegisterInquiryDto;
import dto.inquiry.GetInquiryListDto;
import dto.inquiry.RemoveInquiryDto;
import dto.transaction.DepositDto;
import dto.transaction.TransferDto;
import dto.transaction.WithdrawDto;
import dto.user.LoginDto;
import dto.user.client.RegisterClientDto;
import dto.user.client.UpdateClientDto;
import dto.inquiry.EditInquiryDto;
import enumeration.client.Gender;
import enumeration.inquiry.InquiryCategory;
import exception.*;
import exception.authentication.AuthFailureException;
import exception.regex.RegexNotValidException;
import service.AccountService;
import service.ClientService;
import service.InquiryService;
import service.TransactionService;
import utils.RegexValidator;
import utils.CaptchaAuthentication;
import java.io.IOException;

public class ClientView extends View implements LoginView {
	private final ClientService clientService;
	private final TransactionService transactionService;
	private final AccountService accountService;
	private final InquiryService inquiryService;

	int id;
	long amount;

	public ClientView() {
		this.transactionService = TransactionService.getInstance();
		this.clientService = ClientService.getInstance(); // 생성자마자 new 말고 getInstance로 가져오기
		this.accountService = AccountService.getInstance();
		this.inquiryService = InquiryService.getInstance();
	}

	@Override
	protected void start() {
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |          고객 서비스 입니다          |");
		System.out.println("         |      원하시는 메뉴를 선택 해주세요      |");
		System.out.println("         |_________________________________|");
		System.out.println();
	}

	//고객서비스
	public void mainMenu() {
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("[1] 로그인       [2] 회원가입");
			System.out.println();
			System.out.print("> ");
			
			try {
				String input = br.readLine();
				switch (input) {
				case "1":
					login();
					isRunning = false;
					break;
				case "2":
					signup();
					mainMenu();
					isRunning = false;
					break;

				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}
	}
	
	//로그인
	@Override
	public void login() {
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |         로그인 서비스 입니다         |");
		System.out.println("         |     이메일과 비밀번호를 입력해주세요     |");
		System.out.println("         |_________________________________|");
		System.out.println();
		String password = null;
		String email = null;
		String inputAuthNumber = null;

		while (true) {
//			System.out.println("계정에 사용된 이메일과 비밀번호를 입력하세요"); // 예외에 안 걸리면 성공
			System.out.println("[이메일]");
			System.out.println();
			System.out.print("> ");
			try {
				email = br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				// e.printStackTrace();
			}
			
			System.out.println();
			System.out.println("[비밀번호]");
			System.out.println();
			System.out.print("> ");
			try {
				password = br.readLine();
			} catch (IOException e) {
				System.out.println(e.getMessage());
				// e.printStackTrace();
			}

			// 캡챠 인증 추가
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |        !! CAPTCHA 인증 !!        |");
			System.out.println("         |     아래의 그림과 같은 번호를 입력해     |");
			System.out.println("         |          인증을 완료해주세요          |");
			System.out.println("         |_________________________________|");


			int count = 0;
			while (true) {
				try {
					String captchaNumbers = CaptchaAuthentication.generateCaptchaNumbers();
					CaptchaAuthentication.printCaptchaImage(captchaNumbers);

					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |           인증 예시:  1           |");
					System.out.println("         |                     2           |");
					System.out.println("         |                     3           |");
					System.out.println("         |                     4           |");
					System.out.println("         |                                 |");					
					System.out.println("         |               -> 1234           |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.println("[인증번호]");
					System.out.println();
					System.out.print("> ");
					inputAuthNumber = br.readLine();
					boolean matches = inputAuthNumber.matches(captchaNumbers);
					if (matches) {
						System.out.println("         ___________________________________");
						System.out.println("         |                                 |");
						System.out.println("         |          인증에 성공했습니다          |");
						System.out.println("         |_________________________________|");
						break;
					} else if (++count == 5) {
						System.out.println("         ___________________________________");
						System.out.println("         |                                 |");
						System.out.println("         |          인증에 실패했습니다          |");
						System.out.println("         |     10초간 대기 후 다시 시도해주세요     |");
						System.out.println("         |                                 |");	
						
						for (int i = 10; i > 0; i--) {
							if (i==10) 
								System.out.println("         |               " + i + "...             |");
							else 
								System.out.println("         |                " + i + "...             |");
							Thread.sleep(1000);
						}
						System.out.println("         |_________________________________|");
						System.out.println("         ___________________________________");
						System.out.println("         |                                 |");
						System.out.println("         |    CAPTCHA 인증을 다시 시도해주세요    |");
						System.out.println("         |_________________________________|");
						count = 0;
					} else {
						throw new AuthFailureException();
					}
				} catch (IOException e) {
					System.out.println(e.getMessage());
				} catch (InterruptedException e) {
					System.out.println("시스템에 오류가 발생했습니다. 다시 시도해주세요.");
				} catch (BaseException e) {
					System.out.println(e.getMessage());
				}
			}

			LoginDto loginDto = LoginDto.builder().email(email).password(password).build();

			try {
				userId = clientService.login(loginDto);
				System.out.println("         ___________________________________");
				System.out.println("         |                                 |");
				System.out.println("         |           로그인되었습니다           |");
				System.out.println("         |_________________________________|");
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//회원가입
	public void signup() {
		String email, password, name, gender, birthDate, userPhoneNumber = "";
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |         회원가입 서비스 입니다        |");
		System.out.println("         |         개인정보를 입력해주세요        |");
		System.out.println("         |_________________________________|");
		System.out.println();
		// boolean isRunning = true;

		while (true) {
			System.out.println("[이름]");
			System.out.println("예) 김덕배");
			System.out.println();
			System.out.print("> ");
			try {
				name = br.readLine();
				RegexValidator.validateRegex(name, RegexValidator.NAME_REGEX);
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[성별]");
			System.out.println("[1] 남성       [2] 여성");
			System.out.println("예) 1");
			System.out.println();
			System.out.print("> ");
			try {
				gender = br.readLine();
				if (gender.equals("1") || gender.equals("2")) {
					break;
				} else {
					throw new RegexNotValidException();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[전화번호]");
			System.out.println("예) 010-1234-5678");
			System.out.println();
			System.out.print("> ");
			try {
				userPhoneNumber = br.readLine();
				RegexValidator.validateRegex(userPhoneNumber, RegexValidator.PHONE_NUMBER_REGEX);
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[생년월일]");
			System.out.println("예) 19990101");
			System.out.println();
			System.out.print("> ");
			try {
				birthDate = br.readLine();
				RegexValidator.validateRegex(birthDate, RegexValidator.BIRTH_DATE_REGEX);
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |         회원정보를 입력해주세요        |");
		System.out.println("         |_________________________________|");
		
		while (true) {
			System.out.println();
			System.out.println("[이메일]");
			System.out.println("예) kosa123@hyosung.com");
			System.out.println();
			System.out.print("> ");
			try {
				email = br.readLine();
				RegexValidator.validateRegex(email, RegexValidator.EMAIL_REGEX);
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[비밀번호]");
			System.out.println("영문, 숫자, 특수문자가 최소 1개씩 들어간 6자리 이상을 입력해주세요");
			System.out.println();
			System.out.print("> ");
			try {
				password = br.readLine();
				RegexValidator.validateRegex(password, RegexValidator.PASSWORD_REGEX);
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}

		try {
			if (gender.equals("1")) {
				clientService.register(RegisterClientDto.builder().phoneNumber(userPhoneNumber).birthDate(birthDate)
						.name(name).password(password).email(email).gender(Gender.MALE).build());
			} else if (gender.equals("2")) {
				clientService.register(RegisterClientDto.builder().phoneNumber(userPhoneNumber).birthDate(birthDate)
						.name(name).password(password).email(email).gender(Gender.FEMALE).build());
			}
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |         회원가입이 완료되었습니다       |");
		System.out.println("         |_________________________________|");
		System.out.println();
	}
	
	//메인
	@Override
	protected void proceed() {
		mainMenu();
		boolean isRunning = true;
		while (isRunning) {
			System.out.println();
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |           메인 화면입니다           |");
			System.out.println("         |      원하시는 메뉴를 선택 해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 내 계좌 관리		[2] 입출금		[3] 계좌 이체");
			System.out.println("[4] 거래 내역 조회		[5] 고객 문의		[6] 마이페이지");
			System.out.println("[7] 로그아웃		[0] 홈으로");
			System.out.println();
			System.out.print("> ");
			try {
				switch (br.readLine()) {
				case "1":
					manageMyAccount();
					break;
				case "2":
					receivePaid();
					break;
				case "3":
					accountTransfer();
					break;
				case "4":
					//거래내역 조회 필요
					break;
				case "5":
					customerInquiry();
					break;
				case "6":
					myPage();
					break;
				case "7":
					proceed();
				case "0":
					isRunning = false;
					break;
				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException | NullPointerException e) {
				System.out.println("잘못된 입력입니다.");
			}
		}
	}
	
	//내 계좌 관리
	public void manageMyAccount() {
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |        내 계좌 관리 서비스입니다       |");
			System.out.println("         |       원하시는 메뉴를 선택 해주세요     |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 계좌 조회       [2] 계좌 등록       [3] 계좌 삭제       [4] 메인으로");
			System.out.println();
			System.out.print("> ");
			try {
				switch (br.readLine()) {
				case "1":
					System.out.println();
					for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
						System.out.println(getAccountDto.toString());
					}
					break;
				case "2":
					accountService.registerAccount(returnRegisterAccountDto());
					break;
				case "3":
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |       삭제할 계좌를 선택해주세요       |");
					System.out.println("         |_________________________________|");
					System.out.println();
					for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
						System.out.println(getAccountDto.toString());
					}
					System.out.print("> ");
					int id = Integer.parseInt(br.readLine());
					accountService.removeAccount(
							RemoveAccountDto
									.builder()
									.id(id)
									.ownerId(userId)
									.build()
					);
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |         삭제가 완료되었습니다         |");
					System.out.println("         |_________________________________|");
					break;
				case "4":
					isRunning = false;
					break;
				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}

		}
	}
	
	//계좌 등록 DTO
	public RegisterAccountDto returnRegisterAccountDto() {
		String bankName, number;
		while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |      등록할 계좌정보를 입력해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[은행명]");
			System.out.println();
			System.out.print("> ");
			try {
				bankName = br.readLine();
				break;
			} catch (IOException e) {
				System.out.print(e.getMessage());
			}
		}
		while (true) {
			System.out.println();
			System.out.println("[계좌번호]");
			System.out.println();
			System.out.print("> ");
			try {
				number = br.readLine();
				RegexValidator.validateRegex(number, RegexValidator.ACCOUNT_NUMBER_REGEX);
				break;
			} catch (IOException e) {
				System.out.print(e.getMessage());
			} catch (BaseException e) {
				System.out.print(e.getMessage());
			}
		}
		return RegisterAccountDto.builder().ownerId(userId).bankName(bankName).number(number).build();
	}
	
	//입출금
	public void receivePaid() {
		Boolean isRunning = true;
		while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |          입출금 서비스입니다          |");
			System.out.println("         |       원하시는 메뉴를 선택 해주세요     |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 입금       [2] 출금");
			System.out.println();
			System.out.print("> ");
			try {
				switch (br.readLine()) {
				case "1":
					System.out.println();
					for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
						System.out.println(getAccountDto.toString());
					}
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |       입금할 계좌를 선택해주세요       |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.print("> ");
					id = Integer.parseInt(br.readLine());
					System.out.println();
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |       입금할 금액을 입력해주세요        |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.print("> ");
					amount = Long.parseLong(br.readLine());
					transactionService.deposit(
							DepositDto.builder()
									.accountId(id)
									.amount(amount)
									.ownerId(userId)
									.build()
					);
					System.out.println();
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |         입금이 완료되었습니다         |");
					System.out.println("         |_________________________________|");
					isRunning = false;
					break;
				case "2":
					System.out.println();
					for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
						System.out.println(getAccountDto.toString());
					}
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |       출금할 계좌를 선택해주세요       |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.print("> ");
					id = Integer.parseInt(br.readLine());
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |       출금할 금액을 입력해주세요        |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.print("> ");
					amount = Long.parseLong(br.readLine());
					transactionService.withdraw(
							WithdrawDto.builder()
									.accountId(id)
									.amount(amount)
									.ownerId(userId)
									.build()
					);
					System.out.println();
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |         출금이 완료되었습니다         |");
					System.out.println("         |_________________________________|");
					isRunning = false;
					break;
				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	//계좌 이체
	public void accountTransfer() {
		String account = "";
		System.out.println();
		try {
			for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
				System.out.println(getAccountDto.toString());
			}
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
		while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |        본인 계좌를 선택해주세요        |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.print("> ");
			try {
				id = Integer.parseInt(br.readLine());
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
			}
		}
		while (true) {
			System.out.println();
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |      이체할 계좌번호를 입력해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.print("> ");
			try {
				account = br.readLine();
				RegexValidator.validateRegex(account, RegexValidator.ACCOUNT_NUMBER_REGEX);
				break;
			} catch (IOException | BaseException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
			}
		}

		while (true) {
			System.out.println();
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |       이체할 금액을 입력해주세요       |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.print("> ");
			try {
				amount = Long.parseLong(br.readLine());
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
			}
		}

		try {
//			transactionService.transfer(
//					id, account, amount
//			);
			transactionService.transfer(
					TransferDto.builder()
							.withdrawAccountId(id)
							.depositAccountNumber(account)
							.amount(amount)
							.withdrawAccountOwnerId(userId)
							.build()
			);
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |         이체가 완료되었습니다         |");
			System.out.println("         |_________________________________|");
		} catch (BaseException e) {
			System.out.print(e.getMessage());
		}
	}
	
	//고객 문의
	public void customerInquiry() {
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |         고객 문의 서비스입니다         |");
			System.out.println("         |       원하시는 메뉴를 선택 해주세요     |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 문의 조회       [2] 문의 등록       [3] 문의 변경       [4] 문의 삭제       [5] 메인으로");
			System.out.println();
			System.out.print("> ");
			try {
				switch (br.readLine()) {
				case "1":
					System.out.println();
					for (GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList(userId)) {
						System.out.println(getInquiryListDto.toString());
					}
					break;
				case "2":
					System.out.println();
					makeRegisterInquiry();
					break;
				case "3":
					System.out.println();
					for (GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList(userId)) {
						System.out.println(getInquiryListDto.toString());
					}
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |        변경할 문의를 선택해주세요       |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.print("> ");
					updateInquiry(Integer.parseInt(br.readLine()));
					break;
				case "4":
					System.out.println();
					System.out.println(inquiryService.getInquiryList(userId));
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |        삭제할 문의를 선택해주세요       |");
					System.out.println("         |_________________________________|");
					System.out.println();
					System.out.print("> ");
					int id = Integer.parseInt(br.readLine());
					inquiryService.removeInquiry(
							RemoveInquiryDto.builder()
									.id(id)
									.authorId(userId)
									.build()
					);
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |         문의가 삭제되었습니다         |");
					System.out.println("         |_________________________________|");
					break;
				case "5":
					isRunning = false;
				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	//문의 등록
	public void makeRegisterInquiry() { // registerInquiry(RegisterInquiryDto registerInquiryDto)
		String title = "";
		String content = "";
		InquiryCategory category = null;
		boolean isRunning = true;

		while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |       문의 카테고리를 선택해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 계좌       [2] 거래");
			System.out.println();
			System.out.print("> ");
			try {
				String input = br.readLine();
				switch (input) {
				case "1":
					category = InquiryCategory.ACCOUNT;
					isRunning = false;
					break;
				case "2":
					category = InquiryCategory.TRANSACTION;
					isRunning = false;
					break;
				default:
					throw new IllegalArgumentException("잘못된 카테고리입니다.");
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}

		while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |          문의를 작성해주세요          |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[제목]");
			System.out.print("> ");
			try {
				title = br.readLine();
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[내용]");
			System.out.print("> ");
			try {
				content = br.readLine();
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}

		try {
			inquiryService.registerInquiry(RegisterInquiryDto.builder().category(category).title(title).content(content)
					.authorId(userId).build());
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |          문의를 등록했습니다          |");
			System.out.println("         |_________________________________|");
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//문의 변경 - 프린트 미완
	public void updateInquiry(int id) {
		String title = "";
		String content = "";
		String category = null;
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("1. 계좌\n2. 거래");
			System.out.print("[입력] : ");
			try {
				category = br.readLine();
				switch (category) {
				case "1":
					System.out.println("[계좌] 카테고리를 선택하셨습니다.");
					isRunning = false;
					break;
				case "2":
					System.out.println("[거래] 카테고리를 선택하셨습니다.");
					isRunning = false;
					break;
				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("잘못된 입력입니다. 다시 입력해 주세요.");
			}
		}
		while (true) {
			System.out.print("[제목] : ");
			try {
				title = br.readLine();
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}
		while (true) {
			System.out.print("\n[본문] : ");
			try {
				content = br.readLine();
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}

		if (category.equals("1")) {
			try {
				inquiryService.editInquiry(EditInquiryDto.builder().category(InquiryCategory.ACCOUNT).title(title)
						.content(content).id(id).authorId(userId).build());
				System.out.println("문의를 수정하였습니다.");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		} else if (category.equals("2")) {
			try {
				inquiryService.editInquiry(EditInquiryDto.builder().category(InquiryCategory.TRANSACTION).title(title)
						.content(content).id(id).authorId(userId).build());
				System.out.println("문의를 수정하였습니다.");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	//마이페이지
	public void myPage() {
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |         마이페이지 서비스입니다        |");
			System.out.println("         |       원하시는 메뉴를 선택 해주세요     |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 계정 정보 수정       [2] 회원 탈퇴");
			System.out.println();
			System.out.print("> ");

			try {
				switch (br.readLine()) {
				case "1":
					System.out.println();
					System.out.println(clientService.getCurrentClient(userId).toString());
					update();
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |        회원 정보가 수정되었습니다      |");
					System.out.println("         |_________________________________|");
					break;
				case "2":
					System.out.println();
					clientService.removeClient(userId);
					System.out.println("         ___________________________________");
					System.out.println("         |                                 |");
					System.out.println("         |        회원 탈퇴가 완료되었습니다      |");
					System.out.println("         |        이용해주셔서 감사합니다         |");
					System.out.println("         |_________________________________|");
					proceed();
					break;
				default:
					throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	//계정 정보 수정 - 프린트 미완
	public void update() {
		String email, password, name, gender, birthDate, userPhoneNumber = "";
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |         개인정보를 수정합니다         |");
		System.out.println("         |   수정할 정보를 양식에 맞게 입력해주세요   |");		
		System.out.println("         |_________________________________|");
		System.out.println();

		while (true) {
			System.out.println("[이름]");
			System.out.println("예) 김덕배");
			System.out.println();
			System.out.print("> ");
			try {
				name = br.readLine();
				RegexValidator.validateRegex(name, RegexValidator.NAME_REGEX);
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[성별]");
			System.out.println("[1] 남성       [2] 여성");
			System.out.println("예) 1");
			System.out.println();
			System.out.print("> ");
			try {
				gender = br.readLine();
				if (gender.equals("1") || gender.equals("2")) {
					break;
				} else {
					throw new RegexNotValidException();
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[전화번호]");
			System.out.println("예) 010-1234-5678");
			System.out.println();
			System.out.print("> ");
			try {
				userPhoneNumber = br.readLine();
				RegexValidator.validateRegex(userPhoneNumber, RegexValidator.PHONE_NUMBER_REGEX);
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[생년월일]");
			System.out.println("예) 19990101");
			System.out.println();
			System.out.print("> ");
			try {
				birthDate = br.readLine();
				RegexValidator.validateRegex(birthDate, RegexValidator.BIRTH_DATE_REGEX);
				break;
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[이메일]");
			System.out.println("예) kosa123@hyosung.com");
			System.out.println();
			System.out.print("> ");
			try {
				email = br.readLine();
				RegexValidator.validateRegex(email, RegexValidator.EMAIL_REGEX);
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		while (true) {
			System.out.println();
			System.out.println("[비밀번호]");
			System.out.println("영문, 숫자, 특수문자가 최소 1개씩 들어간 6자리 이상을 입력해주세요");
			System.out.println();
			System.out.print("> ");
			try {
				password = br.readLine();
				RegexValidator.validateRegex(password, RegexValidator.PASSWORD_REGEX);
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("");
			}
		}

		try {
			if (gender.equals("1")) {
				clientService.updateClient(UpdateClientDto.builder().id(userId).phoneNumber(userPhoneNumber)
						.birthDate(birthDate).name(name).password(password).email(email).gender(Gender.MALE).build());
			} else if (gender.equals("2")) {
				clientService.updateClient(UpdateClientDto.builder().id(userId).phoneNumber(userPhoneNumber)
						.birthDate(birthDate).name(name).password(password).email(email).gender(Gender.FEMALE).build());
			}
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	protected void end() {

	}
}
