package view;

import dto.account.GetAccountDto;
import dto.account.RegisterAccountDto;
import dto.inquiry.RegisterInquiryDto;
import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import dto.user.client.RegisterClientDto;
import dto.user.client.UpdateClientDto;
import entity.Account;
import enumeration.client.Gender;
import enumeration.inquiry.InquiryCategory;
import exception.*;
import exception.view.InputNotCorrectException;
import service.AccountService;
import service.ClientService;
import service.InquiryService;
import service.TransactionService;

import java.io.IOException;

public class ClientView extends View implements LoginView {
    private final ClientService clientService;
    private final TransactionService transactionService;
    private final AccountService accountService;
    private final InquiryService inquiryService;

    int id;
    long amount;
    boolean matches;


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
        while (true) {
            {
                System.out.println("\n1. 회원 가입\n2. 로그인");
                System.out.print("입력 : ");
                try {
                    switch (br.readLine()) {
                        case "1":
                            signup(); // 회원가입 끝나면 로그인으로 보내주자...
                            login();
                            break;
                        case "2":
                            login();
                            break;
                        default:
                            throw new IllegalArgumentException();
                    }
                } catch (IllegalArgumentException | IOException e) {
                    System.out.print("잘못된 입력입니다. 다시 입력해 주세요.");
                    //System.out.println(e.getMessage());
                }
                break;
            }
        }
        // System.out.println("확인 (proceed)");
        while (true) {
            System.out.println("1. 내 계좌 관리\n2. 입출금\n3. 이체\n4. 마이페이지\n5. 개인 보안\n6.고객 문의\n0. 로그아웃");
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
                    case "6" :
                        System.out.println("고객 문의로 이동합니다.");
                        customerInquiry();
                        break;
                    case "0" :
                        System.out.println("로그아웃 후 메인으로 넘어갑니다 . . .");
                        proceed();
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | IOException | NullPointerException e) {
                System.out.println("잘못된 입력입니다.");
                // e.printStackTrace();
            }
        }
	}

    public void customerInquiry() {
        System.out.println("[문의 페이지]");
        System.out.println("원하시는 기능을 입력해 주세요");
        System.out.println("1. 문의 조회\n2. 문의 등록\n3. 문의 변경 ");
        try {
            switch (br.readLine()) {
                case "1" :
                    System.out.println("[문의 조회]");
                    System.out.println(inquiryService.getInquiryList(userId));
                case "2" :
                    System.out.println("[문의 등록]");
                    //String
                    //inquiryService.registerInquiry( );
            }
        } catch (IOException e) {
            System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
        } catch (BaseException e) {
            System.out.println(e.getMessage());
        }

    }

    public RegisterInquiryDto makeRegisterInquiryDto() { // registerInquiry(RegisterInquiryDto registerInquiryDto)
        String title = "";
        String content = "";
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

        while (true) {

        }
        // RegisterInquiryDto.builder();
    }
    public void myPage() {
        System.out.println("[마이 페이지]");
        System.out.println("원하시는 메뉴를 입력해 주세요");
        System.out.println("1. 계정 정보 조회\n2. 계정 정보 수정\n3. 계정 정보 삭제");
        try {
            switch (br.readLine()) {
                case "1" :
                    System.out.println("[계정 정보 조회]");
                    System.out.println(clientService.getCurrentClient(userId).toString());
                    break;
                case "2" :
                    System.out.println("[계정 정보 수정]");
                    System.out.println();
                    update();
                    break;
                case "3" :
                    System.out.println("[계정 정보 삭제]");
                    System.out.println("계정 삭제를 진행합니다...");
                    clientService.removeClient(userId);
                    System.out.println("계정 삭제가 완료됐습니다.");
                    break;
            }
        } catch (IOException e) {
            System.out.println("올바르지 않은 입력입니다. 다시 입력해 주세요.");
        } catch (BaseException e) {
            System.out.println(e.getMessage());
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
                break;
            } catch (IOException e) {
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
                    throw new InputNotCorrectException();
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
                matches = userPhoneNumber.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
                if (matches) {
                    break;
                } else
                    throw new InputNotCorrectException();
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
                matches = birthDate.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
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
                matches = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
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
                matches = password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,}$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("");
            }
        }

        try {
            if(gender.equals("1")) {
                clientService.updateClient(UpdateClientDto.builder().phoneNumber(userPhoneNumber).birthDate(birthDate).name(name).password(password).email(email).gender(Gender.MALE).build());
            }
            else if(gender.equals("2")) {
                clientService.updateClient(UpdateClientDto.builder().phoneNumber(userPhoneNumber).birthDate(birthDate).name(name).password(password).email(email).gender(Gender.FEMALE).build());
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
            System.out.print("입금할 계좌의 번호를 입력해 주세요 : ");
            System.out.print("[계좌 번호] : ");
            try {
                account = br.readLine();
                matches = account.matches("^[0-9]+$");
                if (!matches) {
                    throw new InputNotCorrectException();
                } else  {
                    break;
                }
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
        while(true) {
            System.out.println("원하시는 기능을 입력해 주세요");
            System.out.println("1. 입금\n2. 출금");
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
                        break;
                    default:
                        throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException | IOException e) {
                System.out.println("올바른 입력이 아닙니다. 다시 입력해 주세요. ");
            } catch (BaseException e) {
                throw new RuntimeException(e);
            }
            break;
          }
        }

    public void signup() { // 회원가입
        String email, password, name, gender, birthDate, userPhoneNumber = "";
        System.out.println("회원가입 메뉴에 진입합니다.");
        System.out.print("회원 가입에 필요한 정보를 입력해 주세요\n");

        while (true) {
            System.out.println("성함을 입력해 주세요");
            System.out.print("성함 : ");
            try {
                name = br.readLine();
                break;
            } catch (IOException e) {
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
                    throw new InputNotCorrectException();
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
                matches = userPhoneNumber.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$");
                if (matches) {
                    break;
                } else
                    throw new InputNotCorrectException();
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
                matches = birthDate.matches("^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
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
                matches = email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
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
                matches = password.matches("^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,}$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
            } catch (BaseException e) {
                System.out.println(e.getMessage());
            } catch (IOException e) {
                System.out.println("");
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


    public void manageMyAccount() throws IOException {
        while (true) {
            System.out.println("[내 계좌 관리]");
            System.out.println("1. 계좌 등록\n2. 계좌 삭제\n3. 계좌 조회");
            System.out.print("입력 : ");
            try {
                switch (br.readLine()) {
                    case "1":
                        accountService.registerAccount(returnRegisterAccountDto());
                        break;
                    case "2":
                        System.out.println("삭제할 계좌의 ID를 입력해 주세요");
                        accountService.removeAccount(Integer.parseInt(br.readLine()));
                        System.out.println("삭제가 완료됐습니다.");
                        break;
                    case "3":
                        for(GetAccountDto getAccountDto : accountService.getAccountList(userId)) {
                            System.out.println(getAccountDto.toString());
                        }
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
                matches = number.matches("^[0-9]+$");
                if (matches) {
                    break;
                } else {
                    throw new InputNotCorrectException();
                }
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
