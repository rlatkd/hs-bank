package view;

import dto.account.GetAccountDto;
import dto.inquiry.GetInquiryListDto;
import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import dto.user.admin.RegisterAdminDto;
import dto.user.client.GetClientDto;
import exception.BaseException;
import exception.authentication.AuthFailureException;
import service.ClientService;
import service.InquiryService;
import utils.CaptchaAuthentication;
import utils.RegexValidator;
import service.AdminService;
import java.io.IOException;
import dto.user.*;
import service.AccountService;

public class AdminView extends View implements LoginView {
	String name, password, email;

	private final InquiryService inquiryService;
	private final AdminService adminService;
	private final ClientService clientService;
	private final AccountService accountService;

	public AdminView() {
		this.adminService = AdminService.getInstance();
		this.clientService = ClientService.getInstance();
		this.accountService = AccountService.getInstance();
		this.inquiryService = InquiryService.getInstance();
	}
	@Override
	protected void start() {
		System.out.println("관리자 페이지에 진입합니다...");
	}


	@Override
	protected void proceed() {
		mainMenu();
		System.out.println("[관리자 메뉴]");
		boolean isRunning = true;
		while (isRunning) {
			System.out.println("1. 고객 계정 관리\n2. 고객 계좌 관리\n3. 고객 문의 관리\n4. 고객 이체 내역 관리\n5. 로그아웃\n6. 초기 화면\n0. 서브 관리자 생성");
			System.out.print("[입력] : ");
			try {
				switch (br.readLine()) {
					case "1" :
						clientManage();
						break;
					case "2" :
						accountManage();
						break;
					case "3" :
						inquiryManage();
						break;
					case "4" :
						transferManage();
						break;
					case "5" :
						System.out.println("로그아웃 후 메인 화면으로 진입합니다.");
						proceed();
					case "6" :
						isRunning = false;
						break;
					default:
						throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
			}
		}
	}

	public void transferManage() {
		boolean isRunning = true;
		while(isRunning) {
			System.out.println("[고객 이체 내역 관리]");
			System.out.println(">> 전체 고객의 리스트를 불러 옵니다...");
			try {
				for(GetClientDto client : clientService.getClientList()) {
					System.out.println(client.toString());
				}
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("관리할 고객(계정)을 선택해 주세요");
			System.out.println(">> 해당 고객의 전체 계좌를 출력합니다...");
			System.out.print("[계정 id] : ");
			try {
				for(GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(br.readLine()))) {
					System.out.println(getAccountDto.toString());
				}
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
			}


		}
	}
	public void inquiryManage() {
		boolean isRunning = true;
		String inquiryNumber = "";
		while(isRunning) {
			System.out.println("[고객 문의 관리 페이지]");
			System.out.println(">> 전체 문의 리스트를 불러 옵니다...");
			try {
				for(GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList()) {
					System.out.println(getInquiryListDto.toString());
				}
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
			System.out.println("변경할 문의를 입력해 주세요");
			System.out.print("[변경할 문의 ID] : ");
			try {
				inquiryNumber = br.readLine();
			} catch (IOException e) {
				System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
			}
			System.out.println("해당 문의의 변경된 상태를 지정하세요.");
			System.out.println("1. 반려\n2. 완료");
			try{
				switch (br.readLine()) {
					case "1" :
						inquiryService.rejectInquiry(Integer.parseInt(inquiryNumber));
						break;
					case "2" :
						inquiryService.completeInquiry(Integer.parseInt(inquiryNumber));
						break;
					default:
						throw new IllegalArgumentException();
				}
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
			}
		}
	}
	public void accountManage() {
		boolean isRunning = true;
		String id = null;
		while (isRunning) {
			System.out.println("[고객 계좌 관리 페이지]");
			System.out.println("\n1. 고객 계좌 비활성화\n2. 고객 계좌 활성화");
			System.out.print("[입력] : ");
			try {
				switch (br.readLine()) {
					case "1" :
						System.out.println("[고객 계좌 비활성화]");
						System.out.println(">> 전체 고객의 리스트를 불러 옵니다...");
						for(GetClientDto client : clientService.getClientList()) {
							System.out.println(client.toString());
						}
						System.out.println("비활성화 대상 고객의 계좌를 불러 옵니다.");
						System.out.print("[비활성화 대상 고객의 userID] : ");
						id = br.readLine();
						for(GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(id))) {
							System.out.println(getAccountDto.toString());
						}
						System.out.print("[비활성화 대상 계좌 ID] : ");
						accountService.deactivateAccount(Integer.parseInt(br.readLine()));
						System.out.println("해당 계좌를 비활성화했습니다.");
						isRunning = false;
						break;
					case "2" :
						System.out.println("[고객 계좌 활성화]");
						System.out.println(">> 전체 고객의 리스트를 불러 옵니다...");
						for(GetClientDto client : clientService.getClientList()) {
							System.out.println(client.toString());
						}
						System.out.println("활성화 대상 고객의 계좌를 불러 옵니다.");
						System.out.print("[활성화 대상 고객의 userID] : ");
						id = br.readLine();
						for(GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(id))) {
							System.out.println(getAccountDto.toString());
						}
						System.out.print("[활성화 대상 계좌 ID] : ");
						accountService.activateAccount(Integer.parseInt(br.readLine()));
						System.out.println("해당 계좌를 활성화했습니다.");
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
	public void clientManage() {
		boolean isRunning = true;
		String id = null;
		while (isRunning) {
			System.out.println("[고객 계정 관리 페이지]");
			System.out.println("1. 고객 계정 목록 전체 확인\n2. 고객 계정 비활성화\n3. 고객 계정 활성화");
			System.out.print("[입력] : ");
			try {
				switch (br.readLine()) {
					case "1" :
						for(GetClientDto client : clientService.getClientList()) {
							System.out.println(client.toString());
						}
						isRunning = false;
						break;
					case "2" :
						System.out.println("[고객 계정 비활성화]");
						for(GetClientDto client : clientService.getClientList()) {
							System.out.println(client.toString());
						}
						System.out.println("[비활성화 ID] : ");
						id = br.readLine();
						clientService.deactivate(Integer.parseInt(id));
						System.out.println("해당 계정을 비활성화했습니다.");
						isRunning = false;
						break;
					case "3" :
						System.out.println("고객 계정 활성화");
						System.out.println("[활성화 ID] : ");
						id = br.readLine();
						clientService.activate(Integer.parseInt(id));
						System.out.println("해당 계정을 활성화했습니다.");
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

	public void mainMenu() {
		boolean isRunning = true;
		System.out.println("[관리자 페이지]");
		while(isRunning) {
			System.out.println("1. 회원가입\n2. 로그인");
			System.out.print("[입력] : ");
			try{
				switch (br.readLine()) {
					case "1" :
						registerSub();
						isRunning = false;
						break;
					case "2" :
						login();
						isRunning = false;
						break;
					default:
						throw new IllegalArgumentException();
				}
			} catch (IllegalArgumentException | IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}
	}
	public void registerSub() {
		System.out.println("[서브 관리자 계정 생성]");
		while (true) {
			System.out.println("성함을 입력해 주세요");
			System.out.print("성함 : ");
			try {
				name = br.readLine();
				RegexValidator.validateRegex(name, RegexValidator.NAME_REGEX);
				break;
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
		while (true) {
			System.out.println("이메일을 입력해 주세요");
			System.out.print("이메일 (ex. hyoseung@kosa.or.kr) : ");
			try {
				email = br.readLine();
				RegexValidator.validateRegex(email, RegexValidator.EMAIL_REGEX);
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}

		while (true) {
			System.out.println("비밀번호를 입력해 주세요");
			System.out.print("비밀번호 (6글자 이상, 특수 문자, 영문자, 숫자만 가능) : ");
			try {
				password = br.readLine();
				RegexValidator.validateRegex(password, RegexValidator.PASSWORD_REGEX);
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
			}
		}

		try{
			adminService.register(RegisterAdminDto.builder().email(email).password(password).name(name).build());
			System.out.println("[서브 관리자]를 생성했습니다. ");
		} catch (BaseException e) {
			System.out.println(e.getMessage());
		}


	}

	@Override
	protected void end() {

	}

	@Override
	public void login() {
		System.out.println("로그인 메뉴에 진입합니다.");
		String password = null;
		String email = null;
		String inputAuthNumber = null;

		while (true) {
			System.out.println("계정에 사용된 이메일과 비밀번호를 입력하세요"); // 예외에 안 걸리면 성공
			System.out.print("이메일 : ");
			try {
				email = br.readLine();
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
				//e.printStackTrace();
			}

			System.out.print("비밀번호 : ");
			try {
				password = br.readLine();
			} catch (IOException e) {
				System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
				//e.printStackTrace();
			}

			//캡챠 인증 추가
			System.out.println();
			System.out.println("[캡챠인증] 아래의 그림과 같은 번호를 입력해 인증을 완료해주세요.");
			System.out.println();

			while (true) {
				try {
					String captchaNumbers = CaptchaAuthentication.generateCaptchaNumbers();
					CaptchaAuthentication.printCaptchaImage(captchaNumbers);
					System.out.print("인증번호 : ");
					inputAuthNumber = br.readLine();
					boolean matches = inputAuthNumber.matches(captchaNumbers);
					if (matches) {
						System.out.println("[캡챠인증] 인증에 성공했습니다.\n");
						break;
					} else {
						throw new AuthFailureException();
					}
				} catch (IOException e) {
					System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
				} catch (BaseException e) {
					System.out.println(e.getMessage());
				}
			}

			LoginDto loginDto = LoginDto.builder().email(email).password(password).build();

			try {
				adminService.login(loginDto);
				System.out.println("로그인이 완료되었습니다.");
				break;
			} catch (BaseException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
