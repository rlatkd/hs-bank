package view;

import dto.account.GetAccountDto;
import dto.inquiry.GetInquiryListDto;
import dto.transaction.GetTransactionDto;
import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import dto.user.admin.RegisterAdminDto;
import dto.user.admin.RegisterMainAdminDto;
import dto.user.client.GetClientDto;
import enumeration.admin.AdminType;
import exception.BaseException;
import exception.authentication.AuthFailureException;
import service.*;
import utils.CaptchaAuthentication;
import utils.RegexValidator;
import dto.user.admin.RegisterMainAdminDto;

import java.io.IOException;
import java.util.spi.AbstractResourceBundleProvider;

import dto.user.*;

public class AdminView extends View implements LoginView {
    String name, password, email;

    int accountID;

    private final InquiryService inquiryService;
    private final AdminService adminService;
    private final ClientService clientService;
    private final AccountService accountService;
    private final TransactionService transactionService;

    public AdminView() {
        this.adminService = AdminService.getInstance();
        this.clientService = ClientService.getInstance();
        this.accountService = AccountService.getInstance();
        this.inquiryService = InquiryService.getInstance();
        this.transactionService = TransactionService.getInstance();
    }
    
    public void settingMainAdmin() {
        RegisterMainAdminDto registerMainAdminDto = RegisterMainAdminDto.builder().email("mainadmin@admin.com").password("mainadmin99!").name("[메인 관리자]").build();
        try {
            adminService.checkExistMain(registerMainAdminDto);
        } catch (BaseException e) {
            System.out.print("");
            return;
        }
        try {
            adminService.registerMain(registerMainAdminDto);
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void start() {
    	System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |         관리자 서비스 입니다          |");
        settingMainAdmin();
    }
    
    public void mainMenu() {
        boolean isRunning = true;

		System.out.println("         |     이메일과 비밀번호를 입력해주세요     |");
		System.out.println("         |_________________________________|");
		System.out.println();
        login();
    }
    
    //로그인
    @Override
    public void login() {
        while (true) {
            String password = null;
            String email = null;
            String inputAuthNumber = null;

            while (true) {
            	System.out.println("[이메일]");
    			System.out.println();
    			System.out.print("> ");
                try {
                    email = br.readLine();
                    RegexValidator.validateRegex(email, RegexValidator.EMAIL_REGEX);
                    break;
                } catch (IOException e) {
                    System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
                } catch (BaseException e) {
                    System.out.println(e.getMessage());
                }
            }

            while (true) {
            	System.out.println();
    			System.out.println("[비밀번호]");
    			System.out.println();
    			System.out.print("> ");
                try {
                    password = br.readLine();
                    RegexValidator.validateRegex(password, RegexValidator.PASSWORD_REGEX);
                    break;
                } catch (IOException e) {
                    System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
                } catch (BaseException e) {
                    System.out.println(e.getMessage());
                }
            }

            LoginDto loginDto = LoginDto.builder().email(email).password(password).build();
            try {
                accountID = adminService.login(loginDto);
                // System.out.println("[어카운트 ID] : " + accountID);
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

    //메인메뉴
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
			System.out.println("[1] 고객 계정 관리			[2] 고객 계좌 관리		  [3] 고객 문의 관리");
			System.out.println("[4] 고객 이체 내역 관리		[5] 서브관리자 생성		  [6] 로그아웃");
			System.out.println("[7] 홈으로			[0] 종료");
			System.out.println();
			System.out.print("> ");
            try {
                switch (br.readLine()) {
                    case "1":
                        clientManage();
                        break;
                    case "2":
                        accountManage();
                        break;
                    case "3":
                        inquiryManage();
                        break;
                    case "4":
                        transferManage();
                        break;
                    case "5":
                        registerSub();
                        break;
                    case "6":
                        System.out.println("로그아웃 후 메인 화면으로 진입합니다.");
                        proceed();
                    case "7":
                        isRunning = false;
                        break;
					case "0" :
						end();
						break;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
    }
    
    //고객 계정관리
    public void clientManage() {
        boolean isRunning = true;
        String id = null;
        while (isRunning) {
        	System.out.println();
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |       고객 계정 관리 서비스입니다       |");
			System.out.println("         |      원하시는 메뉴를 선택 해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 고객 목록 확인       [2] 고객 계정 비활성화       [3] 고객 계정 활성화");
			System.out.println();
			System.out.print("> ");
            try {
                switch (br.readLine()) {
                    case "1":
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        break;
                    case "2":
                        
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |      비활성화할 고객을 입력해주세요     |");
            			System.out.println("         |_________________________________|");
            			System.out.println();
                        id = br.readLine();
                        clientService.deactivate(Integer.parseInt(id));
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |         계정을 비활성화했습니다        |");
            			System.out.println("         |_________________________________|");
                        isRunning = false;
                        break;
                    case "3":
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |       활성화할 고객을 입력해주세요      |");
            			System.out.println("         |_________________________________|");
            			System.out.println();
                        id = br.readLine();
                        clientService.activate(Integer.parseInt(id));
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |          계정을 활성화했습니다        |");
            			System.out.println("         |_________________________________|");
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
    
    //고객 계좌 관리
    public void accountManage() {
        boolean isRunning = true;
        String id = null;
        while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |       고객 계좌 관리 서비스입니다       |");
			System.out.println("         |      원하시는 메뉴를 선택 해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 고객 계좌 비활성화       [2] 고객 계좌 활성화");
			System.out.println();
			System.out.print("> ");
            try {
                switch (br.readLine()) {
                    case "1":
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        id = br.readLine();
                        for (GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(id))) {
                            System.out.println(getAccountDto.toString());
                        }
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |      비활성화할 계좌를 입력해주세요     |");
            			System.out.println("         |_________________________________|");
            			System.out.println();
                        accountService.deactivateAccount(Integer.parseInt(br.readLine()));
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |         계좌를 비활성화했습니다        |");
            			System.out.println("         |_________________________________|");
            			System.out.println();
                        isRunning = false;
                        break;
                    case "2":
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        id = br.readLine();
                        for (GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(id))) {
                            System.out.println(getAccountDto.toString());
                        }
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |        활성화할 계좌를 입력해주세요     |");
            			System.out.println("         |_________________________________|");
            			System.out.println();
                        accountService.activateAccount(Integer.parseInt(br.readLine()));
            			System.out.println("         ___________________________________");
            			System.out.println("         |                                 |");
            			System.out.println("         |          계좌를 활성화했습니다        |");
            			System.out.println("         |_________________________________|");
            			System.out.println();
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
    
  //고객 문의 관리
    public void inquiryManage() {
        boolean isRunning = true;
        String inquiryNumber = "";
        while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |       고객 문의 관리 서비스입니다       |");
			System.out.println("         |      변경하실 문의를 선택 해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
            try {
                for (GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList()) {
                    System.out.println(getInquiryListDto.toString());
                }
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
			System.out.print("> ");
            try {
                inquiryNumber = br.readLine();
                break;
            } catch (IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
        while (isRunning) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |      변경하실 상태를 선택 해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
			System.out.println("[1] 반려       [2] 완료");
			System.out.println();
			System.out.print("> ");
            try {
                switch (br.readLine()) {
                    case "1":
                        inquiryService.rejectInquiry(Integer.parseInt(inquiryNumber));
                        isRunning = false;
                        break;
                    case "2":
                        inquiryService.completeInquiry(Integer.parseInt(inquiryNumber));
                        isRunning = false;
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
    
    //고객 이체내역 관리
    public void transferManage() {
        boolean isRunning = true;
        String accountID = "";
        while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |     고객 이체내역 관리 서비스입니다      |");
			System.out.println("         |         고객을 선택 해주세요         |");
			System.out.println("         |_________________________________|");
			System.out.println();
            try {
                for (GetClientDto client : clientService.getClientList()) {
                    System.out.println(client.toString());
                }
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
            System.out.print("> ");
            try {
                for (GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(br.readLine()))) {
                    System.out.println(getAccountDto.toString());
                }
                break;
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
        while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |          계좌를 선택 해주세요        |");
			System.out.println("         |_________________________________|");
			System.out.println();
            try {
                accountID = br.readLine();
                break;
            } catch (IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }

        while (true) {
            try {
                for (GetTransactionDto getTransactionDto : transactionService.getTransactionList(Integer.parseInt(accountID))) {
                    System.out.println(getTransactionDto.toString());
                }
                break;
            } catch (BaseException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
        while (true) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |      취소할 이체내역을 선택해주세요      |");
			System.out.println("         |_________________________________|");
			System.out.println();
            try {
                transactionService.cancelTransaction(Integer.parseInt(br.readLine()));
                System.out.println("이체 내역을 취소했습니다.");
                break;
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }

    }
   
    //서브관리자 생성
    public void registerSub() {
        if (accountID != 1) {
			System.out.println("         ___________________________________");
			System.out.println("         |                                 |");
			System.out.println("         |         메인 관리자가 아닙니다        |");
			System.out.println("         |_________________________________|");
			System.out.println();
            return;
        }
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |      서브 관리자 생성 서비스입니다      |");
		System.out.println("         |        관리자 정보를 입력해주세요       |");
		System.out.println("         |_________________________________|");
		System.out.println();
        while (true) {
            System.out.println("[이름]");
            System.out.print("> ");
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
                System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
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
                System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
            }
        }

        try {
            adminService.register(RegisterAdminDto.builder().email(email).password(password).name(name).build());
    		System.out.println("         ___________________________________");
    		System.out.println("         |                                 |");
    		System.out.println("         |       서브 관리자를 생성했습니다       |");
    		System.out.println("         |_________________________________|");
    		System.out.println();
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    //종료
    @Override
    protected void end() {
		System.out.println("         ___________________________________");
		System.out.println("         |                                 |");
		System.out.println("         |          서비스를 종료합니다         |");
		System.out.println("         |        이용해주셔서 감사합니다         |");
		System.out.println("         |_________________________________|");
		System.exit(0);
    }

 

}
