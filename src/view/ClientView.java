package view;

import dto.account.GetAccountDto;
import dto.account.RegisterAccountDto;
import dto.inquiry.RegisterInquiryDto;
import dto.inquiry.GetInquiryListDto;
import dto.user.LoginDto;
import dto.user.client.RegisterClientDto;
import dto.user.client.UpdateClientDto;
import dto.inquiry.EditInquiryDto;
import enumeration.client.Gender;
import enumeration.inquiry.InquiryCategory;
import exception.*;
import exception.regex.RegexNotValidException;
import exception.verification.AuthFailureException;
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
        System.out.println("고객 메뉴에 진입했습니다.");
    }

    @Override
    protected void proceed() {
        mainMenu();
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. 내 계좌 관리\n2. 입출금\n3. 이체\n4. 마이페이지\n5. 개인 보안\n6. 고객 문의\n7. 로그아웃\n0. 초기 화면");
            System.out.print("입력 : ");
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
                    case "4" :
                        System.out.println("마이 페이지로 이동합니다.");
                        myPage();
                        break;
                    case "5" :
                        System.out.println("개인 보안 페이지로 이동합니다.");
                        // 필요없으면 지울 것. (captcha가 어디에 쓰일지 몰라서 넣어둠)
                        break;
                    case "6" :
                        System.out.println("고객 문의로 이동합니다.");
                        customerInquiry();
                        break;
                    case "7" :
                        System.out.println("로그아웃 후 메인으로 넘어갑니다 . . .");
                        proceed();
                    case "0" :
                        System.out.println("초기 화면으로 돌아갑니다.");
                        isRunning = false;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | IOException | NullPointerException e) {
                System.out.println("잘못된 입력입니다.");
            }
        }
	}

    public void mainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n1. 회원 가입\n2. 로그인");
            System.out.print("입력 : ");
            try {
                String input = br.readLine();
                switch (input) {
                    case "1":
                        signup();
                        login();
                        isRunning = false;
                        break;
                    case "2":
                        login();
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
    public void customerInquiry() {
        boolean isRunning = true;
        System.out.println("[문의 페이지]");
        while(isRunning) {
            System.out.println("원하시는 기능을 입력해 주세요");
            System.out.println("1. 문의 조회\n2. 문의 등록\n3. 문의 변경\n4. 문의 삭제");
            try {
                switch (br.readLine()) {
                    case "1" :
                        System.out.println("[문의 조회]");
                        for(GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList(userId)) {
                            System.out.println(getInquiryListDto.toString());
                        }
                        isRunning = false;
                        break;
                    case "2" :
                        System.out.println("[문의 등록]");
                        makeRegisterInquiry();
                        isRunning = false;
                        break;
                    case "3" :
                        for(GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList(userId)) {
                            System.out.println(getInquiryListDto.toString());
                        }
                        System.out.println("[문의 변경]");
                        System.out.print("변경할 문의를 선택해 주세요. (id) : ");
                        updateInquiry(Integer.parseInt(br.readLine()));
                        isRunning = false;
                        break;
                    case "4" :
                        System.out.println("[문의 삭제]");
                        System.out.println(inquiryService.getInquiryList(userId));
                        System.out.println("삭제할 문의를 선택해 주세요. (id) : ");
                        inquiryService.removeInquiry(Integer.parseInt(br.readLine()));
                        isRunning = false;
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

    public void updateInquiry(int id) {
        System.out.println("문의 변경 페이지입니다. 변경할 사항을 적어 주세요. ");
        String title = "";
        String content = "";
        String category = null;
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("카테고리를 지정해 주세요.");
            System.out.println("1. 계좌\n2. 거래");
            System.out.print("[입력] : ");
            try{
                category = br.readLine();
                switch (category) {
                    case "1" :
                        System.out.println("[계좌] 카테고리를 선택하셨습니다.");
                        isRunning = false;
                        break;
                    case "2" :
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
                inquiryService.editInquiry(EditInquiryDto.builder()
                        .category(InquiryCategory.ACCOUNT)
                        .title(title)
                        .content(content)
                        .id(id)
                        .build());
                System.out.println("문의를 수정하였습니다.");
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
        } else if (category.equals("2")) {
            try {
                inquiryService.editInquiry(EditInquiryDto.builder()
                        .category(InquiryCategory.TRANSACTION)
                        .title(title)
                        .content(content)
                        .id(id)
                        .build());
                System.out.println("문의를 수정하였습니다.");
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void makeRegisterInquiry() { // registerInquiry(RegisterInquiryDto registerInquiryDto)
        String title = "";
        String content = "";
        InquiryCategory category = null;
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("카테고리를 지정해 주세요.");
            System.out.println("1. 계좌\n2. 거래");
            System.out.print("[입력] : ");
            try {
                String input = br.readLine();
                switch (input) {
                    case "1":
                        System.out.println("[계좌] 카테고리를 선택하셨습니다.");
                        category = InquiryCategory.ACCOUNT;
                        isRunning = false;
                        break;
                    case "2":
                        System.out.println("[거래] 카테고리를 선택하셨습니다.");
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

        try {
            inquiryService.registerInquiry(RegisterInquiryDto.builder()
                    .category(category)
                    .title(title)
                    .content(content)
                    .authorId(userId)
                    .build());
            System.out.println("문의를 등록하였습니다.");
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void myPage() {
        boolean isRunning = true;
        System.out.println("[마이 페이지]");
        while(isRunning) {
            System.out.println("원하시는 메뉴를 입력해 주세요");
            System.out.println("1. 계정 정보 조회\n2. 계정 정보 수정\n3. 계정 정보 삭제");
            System.out.print("[입력] : ");
            try {
                switch (br.readLine()) {
                    case "1" :
                        System.out.println("[계정 정보 조회]");
                        System.out.println(clientService.getCurrentClient(userId).toString());
                        isRunning = false;
                        break;
                    case "2" :
                        System.out.println("[계정 정보 수정]");
                        System.out.println();
                        update();
                        isRunning = false;
                        break;
                    case "3" :
                        System.out.println("[계정 정보 삭제]");
                        System.out.println("계정 삭제를 진행합니다...");
                        clientService.removeClient(userId);
                        System.out.println("계정 삭제가 완료됐습니다. 다른 계정으로 회원가입 해 주세요.");
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

    public void update() {
        String email, password, name, gender, birthDate, userPhoneNumber = "";
        System.out.println("[계정 정보 - 업데이트]");
        System.out.print("업데이트에 필요한 정보를 입력해 주세요\n");

        while (true) {
            System.out.println("성함을 입력해 주세요");
            System.out.print("성함 : ");
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
            System.out.println("성별을 입력해 주세요");
            System.out.print("남성 (1), 여성 (2) : ");
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
            System.out.println("전화번호를 입력해 주세요");
            System.out.print("전화번호 (ex. 010-1100-2034) : ");
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
            System.out.println("생년월일을 입력해 주세요");
            System.out.print("생년월일 (ex.19800203) : ");
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
            System.out.println("이메일을 입력해 주세요");
            System.out.print("이메일 (ex. hyoseung@kosa.or.kr) : ");
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
            System.out.println("비밀번호를 입력해 주세요");
            System.out.print("비밀번호 (6글자 이상, 특수 문자, 영문자, 숫자만 가능) : ");
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
            if(gender.equals("1")) {
                clientService.updateClient(UpdateClientDto.builder().id(userId).phoneNumber(userPhoneNumber).birthDate(birthDate).name(name).password(password).email(email).gender(Gender.MALE).build());
            }
            else if(gender.equals("2")) {
                clientService.updateClient(UpdateClientDto.builder().id(userId).phoneNumber(userPhoneNumber).birthDate(birthDate).name(name).password(password).email(email).gender(Gender.FEMALE).build());
            }
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("업데이트 되었습니다.");
    }

    public void accountTransfer()  {
        String account = "";
        System.out.println("[계좌 리스트]");
        try {
            for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
                System.out.println(getAccountDto.toString());
            }
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
        while (true) {
            System.out.print("이체에 사용할 계좌를 입력해 주세요");
            System.out.print("[계좌 ID] : ");
            try {
                id = Integer.parseInt(br.readLine());
                break;
            } catch (IOException e) {
                System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
            }
        }
        while (true) {
            System.out.print("입금할 계좌의 번호를 입력해 주세요");
            System.out.print("[계좌 번호] : ");
            try {
                account = br.readLine();
                RegexValidator.validateRegex(account, RegexValidator.ACCOUNT_NUMBER_REGEX);
                break;
            } catch (IOException | BaseException e) {
                System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
            }
        }

        while (true) {
            System.out.println("이체하실 금액을 적어 주세요");
            System.out.print("[금액] : ");
            try{
                amount = Long.parseLong(br.readLine());
                break;
            } catch (IOException e) {
                System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요. ");
            }
        }

        try {
            transactionService.transfer(id, account, amount);
        } catch (BaseException e) {
            System.out.print(e.getMessage());
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
                System.out.println(e.getMessage());
                //e.printStackTrace();
            }

            System.out.print("비밀번호 : ");
            try {
                password = br.readLine();
            } catch (IOException e) {
                System.out.println(e.getMessage());
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
    				System.out.println(e.getMessage());
    			} catch (BaseException e) {
    				System.out.println(e.getMessage());
    			}
        	}
        	
            LoginDto loginDto = LoginDto.builder().email(email).password(password).build();

            try {
                userId = clientService.login(loginDto);
                System.out.println("로그인이 완료되었습니다.");
                break;
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void receivePaid() {
        Boolean isRunning = true;
        while(isRunning) {
            System.out.println("원하시는 기능을 입력해 주세요");
            System.out.println("1. 입금\n2. 출금");
            System.out.print("[입력] : ");
            try {
                switch (br.readLine()) {
                    case "1":
                        System.out.println("[계좌 리스트]");
                        for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
                            System.out.println(getAccountDto.toString());
                        }
                        System.out.println("입금할 계좌를 선택해 주세요 (ID 입력) : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.println("얼마를 입금하시겠습니까?");
                        amount = Long.parseLong(br.readLine());
                        transactionService.deposit(id, amount);
                        System.out.println("입금이 완료되었습니다.");
                        isRunning = false;
                        break;
                    case "2":
                        System.out.println("[계좌 리스트]");
                        for (GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
                            System.out.println(getAccountDto.toString());
                        }
                        System.out.println("출금할 계좌를 선택해 주세요 (ID 입력) : ");
                        id = Integer.parseInt(br.readLine());
                        System.out.println("출금할 금액을 입력해 주세요. ");
                        amount = Long.parseLong(br.readLine());
                        transactionService.withdraw(id, amount);
                        System.out.println("출금이 완료됐습니다.");
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

    public void signup() { // 회원가입
        String email, password, name, gender, birthDate, userPhoneNumber = "";
        System.out.println("회원가입 메뉴에 진입합니다.");
        System.out.print("회원 가입에 필요한 정보를 입력해 주세요\n");
        // boolean isRunning = true;

        while (true) {
            System.out.println("성함을 입력해 주세요");
            System.out.print("성함 : ");
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
            System.out.println("성별을 입력해 주세요");
            System.out.print("남성 (1), 여성 (2) : ");
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
            System.out.println("전화번호를 입력해 주세요");
            System.out.print("전화번호 (ex. 010-1100-2034) : ");
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
            System.out.println("생년월일을 입력해 주세요");
            System.out.print("생년월일 (ex.19800203) : ");
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
            System.out.println("이메일을 입력해 주세요");
            System.out.print("이메일 (ex. hyoseung@kosa.or.kr) : ");
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
            System.out.println("비밀번호를 입력해 주세요");
            System.out.print("비밀번호 (6글자 이상, 특수 문자, 영문자, 숫자만 가능) : ");
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
            if(gender.equals("1")) {
                clientService.register(RegisterClientDto.builder().phoneNumber(userPhoneNumber).birthDate(birthDate).name(name).password(password).email(email).gender(Gender.MALE).build());
            }
            else if(gender.equals("2")) {
                clientService.register(RegisterClientDto.builder().phoneNumber(userPhoneNumber).birthDate(birthDate).name(name).password(password).email(email).gender(Gender.FEMALE).build());
            }
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("계정을 생성했습니다.");
    }


    public void manageMyAccount() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("[내 계좌 관리]");
            System.out.println("1. 계좌 등록\n2. 계좌 삭제\n3. 계좌 조회");
            System.out.print("입력 : ");
            try {
                switch (br.readLine()) {
                    case "1":
                        accountService.registerAccount(returnRegisterAccountDto());
                        isRunning = false;
                        break;
                    case "2":
                        System.out.println("삭제할 계좌의 ID를 입력해 주세요");
                        System.out.print("[입력] : ");
                        accountService.removeAccount(Integer.parseInt(br.readLine()));
                        System.out.println("삭제가 완료됐습니다.");
                        isRunning = false;
                        break;
                    case "3":
                        for(GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
                            System.out.println(getAccountDto.toString());
                        }
                        isRunning = false;
                        return;
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

    public RegisterAccountDto returnRegisterAccountDto() {
        String bankName, number;
        while (true) {
            System.out.println("등록할 계좌의 정보를 입력해 주세요");
            System.out.print("[대상 은행] : ");
            try {
                bankName = br.readLine();
                break;
            } catch (IOException e) {
                System.out.print(e.getMessage());
            }
        }
        while (true) {
            System.out.print("\n[계좌 번호] :");
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
        System.out.println("계좌를 생성 중입니다 . . . ");
        return RegisterAccountDto.builder().ownerId(userId).bankName(bankName).number(number).build();
    }

}
