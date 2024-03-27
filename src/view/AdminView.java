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

    @Override
    protected void start() {
        System.out.println("관리자 페이지에 진입합니다...");
        settingMainAdmin();
    }


    @Override
    protected void proceed() {
        mainMenu();
        System.out.println("[관리자 메뉴]");
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("1. 고객 계정 관리\n2. 고객 계좌 관리\n3. 고객 문의 관리\n4. 고객 이체 내역 관리\n5. 로그아웃\n6. 초기 화면\n7. 종료\n0. 서브 관리자 생성");
            System.out.print("[입력] : ");
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
                        System.out.println("로그아웃 후 메인 화면으로 진입합니다.");
                        proceed();
                    case "6":
                        isRunning = false;
                        break;
					case "7" :
						end();
						break;
                    case "0":
                        registerSub();
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
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

    public void transferManage() {
        boolean isRunning = true;
        String accountID = "";
        while (true) {
            System.out.println("[고객 이체 내역 관리]");
            System.out.println(">> 전체 고객의 리스트를 불러 옵니다...");
            try {
                for (GetClientDto client : clientService.getClientList()) {
                    System.out.println(client.toString());
                }
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("관리할 고객(계정)을 선택해 주세요");
            System.out.println(">> 해당 고객의 전체 계좌를 출력합니다...");
            System.out.print("[계정 ID] : ");
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
            System.out.println("관리할 계좌의 ID를 선택해 주세요");
            System.out.print("[계좌 ID] :");
            try {
                accountID = br.readLine();
                break;
            } catch (IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
		System.out.println("계좌를 확인하는 중입니다...");

        while (true) {
            System.out.println("해당 계좌의 이체 내역을 불러 옵니다...");
            try {
                for (GetTransactionDto getTransactionDto : transactionService.getTransactionList(Integer.parseInt(accountID))) {
                    System.out.println(getTransactionDto.toString());
                }
                break;
            } catch (BaseException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
        while (true) {
            System.out.println("취소하고 싶은 이체 내역의 ID를 입력해 주세요");
            System.out.print("[이체 내역 ID] : ");
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

    public void inquiryManage() {
        boolean isRunning = true;
        String inquiryNumber = "";
        while (true) {
            System.out.println("[고객 문의 관리 페이지]");
            System.out.println(">> 전체 문의 리스트를 불러 옵니다...");
            try {
                for (GetInquiryListDto getInquiryListDto : inquiryService.getInquiryList()) {
                    System.out.println(getInquiryListDto.toString());
                }
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("변경할 문의를 입력해 주세요");
            System.out.print("[변경할 문의 ID] : ");
            try {
                inquiryNumber = br.readLine();
                break;
            } catch (IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            }
        }
        while (isRunning) {
            System.out.println("해당 문의의 변경된 상태를 지정하세요.");
            System.out.println("1. 반려\n2. 완료");
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

    public void accountManage() {
        boolean isRunning = true;
        String id = null;
        while (isRunning) {
            System.out.println("[고객 계좌 관리 페이지]");
            System.out.println("\n1. 고객 계좌 비활성화\n2. 고객 계좌 활성화");
            System.out.print("[입력] : ");
            try {
                switch (br.readLine()) {
                    case "1":
                        System.out.println("[고객 계좌 비활성화]");
                        System.out.println(">> 전체 고객의 리스트를 불러 옵니다...");
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        System.out.println("비활성화 대상 고객의 계좌를 불러 옵니다.");
                        System.out.print("[비활성화 대상 고객의 userID] : ");
                        id = br.readLine();
                        for (GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(id))) {
                            System.out.println(getAccountDto.toString());
                        }
                        System.out.print("[비활성화 대상 계좌 ID] : ");
                        accountService.deactivateAccount(Integer.parseInt(br.readLine()));
                        System.out.println("해당 계좌를 비활성화했습니다.");
                        isRunning = false;
                        break;
                    case "2":
                        System.out.println("[고객 계좌 활성화]");
                        System.out.println(">> 전체 고객의 리스트를 불러 옵니다...");
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        System.out.println("활성화 대상 고객의 계좌를 불러 옵니다.");
                        System.out.print("[활성화 대상 고객의 userID] : ");
                        id = br.readLine();
                        for (GetAccountDto getAccountDto : accountService.getAccountList(Integer.parseInt(id))) {
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
                    case "1":
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        isRunning = false;
                        break;
                    case "2":
                        System.out.println("[고객 계정 비활성화]");
                        for (GetClientDto client : clientService.getClientList()) {
                            System.out.println(client.toString());
                        }
                        System.out.println("[비활성화 ID] : ");
                        id = br.readLine();
                        clientService.deactivate(Integer.parseInt(id));
                        System.out.println("해당 계정을 비활성화했습니다.");
                        isRunning = false;
                        break;
                    case "3":
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
        System.out.println("=== 로그인 ===");
        login();
    }

    public void registerSub() {
        if (accountID != 1) {
            System.out.println("해당 기능을 이용할 수 없습니다.");
            return;
        }
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

        try {
            adminService.register(RegisterAdminDto.builder().email(email).password(password).name(name).build());
            System.out.println("[서브 관리자]를 생성했습니다. ");
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void end() {
		System.out.println("서비스를 종료합니다. 이용해 주셔서 감사합니다.");
		System.exit(0);
    }

    @Override
    public void login() {
        while (true) {
            System.out.println("로그인 메뉴에 진입합니다.");
            String password = null;
            String email = null;
            String inputAuthNumber = null;

            while (true) {
                System.out.println("계정에 사용된 이메일과 비밀번호를 입력하세요");
                System.out.print("이메일 : ");
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
                System.out.print("비밀번호 : ");
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
                accountID = adminService.login(loginDto);
                // System.out.println("[어카운트 ID] : " + accountID);
                System.out.println("로그인이 완료되었습니다.");
                break;
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
