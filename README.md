## 📁 디렉터리 구조

```
📁 internet-banking
 ├──── 📁 .github
 │      ├──── 📁 ISSUE_TEMPLATE
 │      │      ├──── 📄 ✅-feature-request.md
 │      │      └──── 📄 🐞-hotfix-report.md
 │      └──── 📄 PULL_REQUEST_TEMPLATE
 ├──── 📁 bin
 ├──── 📁 src
 │      ├──── 📁 dto
 │      │      ├──── 📁 account
 │      │      │      ├──── 📄 GetAccountDto.java
 │      │      │      ├──── 📄 RegisterAccountDto.java
 │      │      │      └──── 📄 RemoveAccount.java
 │      │      ├──── 📁 inquiry
 │      │      │      ├──── 📄 EditInquiryDto.java
 │      │      │      ├──── 📄 GetInquiryDto.java
 │      │      │      ├──── 📄 GetInquiryListDto.java
 │      │      │      ├──── 📄 RegisterInquiryDto.java
 │      │      │      └──── 📄 RemoveInquiry.java
 │      │      ├──── 📁 transaction
 │      │      │      ├──── 📄 Deposit.java
 │      │      │      ├──── 📄 GetTransactionDto.java
 │      │      │      ├──── 📄 TransferDto.java
 │      │      │      └──── 📄 WithdrawDto.java
 │      │      └──── 📁 user
 │      │             ├──── 📁 adimin
 │      │             │      ├──── 📄 RegisterAdminDto.java
 │      │             │      └──── 📄 RegisterMainAdminDto.java
 │      │             ├──── 📁 client
 │      │             │      ├──── 📄 AddPointDto.java
 │      │             │      ├──── 📄 GetClientDto.java
 │      │             │      ├──── 📄 GetCurrentClientDto.java
 │      │             │      ├──── 📄 RegisterClientDto.java
 │      │             │      └──── 📄 UpdateClientDto.java
 │      │             ├──── 📄 LoginDto.java
 │      │             └──── 📄 RegisterUserDto.java
 │      ├──── 📁 entity
 │      │      ├──── 📄 Account.java
 │      │      ├──── 📄 Admin.java
 │      │      ├──── 📄 Client.java
 │      │      ├──── 📄 Entity.java
 │      │      ├──── 📄 Inquiry.java
 │      │      ├──── 📄 Transaction.java
 │      │      └──── 📄 User.java
 │      ├──── 📁 enumeration
 │      │      ├──── 📁 admin
 │      │      │      └──── 📄 AdminType.java
 │      │      ├──── 📁 client
 │      │      │      └──── 📄 Gender.java
 │      │      ├──── 📁 inquiry
 │      │      │      ├──── 📄 InquiryCategory.java
 │      │      │      └──── 📄 InquiryStatus.java
 │      │      ├──── 📁 quiz
 │      │      │      └──── 📄 Quiz.java 
 │      │      ├──── 📁 transaction
 │      │      │      ├──── 📄 TransactionStatus.java
 │      │      │      └──── 📄 TransactionType.java
 │      │      └──── 📄 ActivationStatus.java
 │      ├──── 📁 exception
 │      │      ├──── 📁 account
 │      │      │      ├──── 📁 deposit
 │      │      │      │      ├──── 📄 DepositAccountDeactivateException.java
 │      │      │      │      └──── 📄 DepositAccountNotFoundException.java 
 │      │      │      ├──── 📁 withdraw
 │      │      │      │      ├──── 📄 WithdrawAccountDeactivateException.java
 │      │      │      │      └──── 📄 WithdrawAccountNotFoundException.java 
 │      │      │      ├──── 📄 AccountDeactivateException.java
 │      │      │      ├──── 📄 AccountExistException.java
 │      │      │      ├──── 📄 AccountListEmptyException.java
 │      │      │      ├──── 📄 AccountNotFoundException.java
 │      │      │      └──── 📄 BalanceInsufficientException.java
 │      │      ├──── 📁 authentication
 │      │      │      └──── 📄 AuthFailureException.java 
 │      │      ├──── 📁 inquiry
 │      │      │      ├──── 📄 InquiryListEmptyException.java
 │      │      │      └──── 📄 InquiryNotFoundException.java
 │      │      ├──── 📁 regex
 │      │      │      └──── 📄 RegexNotValidException.java
 │      │      ├──── 📁 transaction
 │      │      │      ├──── 📄 NotTransferException.java
 │      │      │      ├──── 📄 TransactionListEmptyException.java
 │      │      │      └──── 📄 TransactionNotFoundException.java
 │      │      ├──── 📁 user
 │      │      │      ├──── 📁 admin
 │      │      │      │      ├──── 📄 AdminDeactivateException.java
 │      │      │      │      ├──── 📄 AdminExistException.java
 │      │      │      │      └──── 📄 AdminNotFoundException.java 
 │      │      │      ├──── 📁 client
 │      │      │      │      ├──── 📄 ClientDeactivateException.java
 │      │      │      │      ├──── 📄 ClientExistException.java
 │      │      │      │      └──── 📄 ClientNotFoundException.java 
 │      │      ├──── 📄 BaseException.java
 │      │      ├──── 📄 DataAccessException.java
 │      │      └──── 📄 LogException.java
 │      ├──── 📁 repository
 │      │      ├──── 📄 AccountRepository.java
 │      │      ├──── 📄 AdminRepository.java
 │      │      ├──── 📄 ClientRepository.java
 │      │      ├──── 📄 InquiryRepository.java
 │      │      ├──── 📄 Repository.java 
 │      │      └──── 📄 TransactionRepository.java
 │      ├──── 📁 service
 │      │      ├──── 📄 AccountService.java
 │      │      ├──── 📄 AdminService.java
 │      │      ├──── 📄 ClientService.java
 │      │      ├──── 📄 InquiryService.java
 │      │      ├──── 📄 TransactionService.java
 │      │      └──── 📄 UserService.java
 │      ├──── 📁 utils
 │      │      ├──── 📄 CaptchaAuthentication.java
 │      │      ├──── 📄 DateTimeGenerator.java
 │      │      ├──── 📄 FilePathConstants.java
 │      │      └──── 📄 RegexValidator.java
 │      ├──── 📁 view
 │      │      ├──── 📄 AdminView.java
 │      │      ├──── 📄 ClientView.java
 │      │      ├──── 📄 InitialView.java
 │      │      ├──── 📄 LoginView.java
 │      │      ├──── 📄 QuizView.java
 │      │      └──── 📄 View.java
 │      └──── 📄 Application.java
 │──── 📁 Referenced Libraries
 │      └──── 📄 lombok.jar
 │──── 📄 .classpath
 │──── 📄 .gitignore
 │──── 📄 .project
 └──── 📄 README.md
```

```
📁 src
 ├──── 📁 dto
 │      ├──── 📁 account
 │      ├──── 📁 inquiry
 │      ├──── 📁 transaction
 │      └──── 📁 user
 │             ├──── 📁 adimin
 │             └──── 📁 client
 ├──── 📁 entity
 ├──── 📁 enumeration
 │      ├──── 📁 admin
 │      ├──── 📁 client
 │      ├──── 📁 inquiry
 │      ├──── 📁 quiz
 │      └──── 📁 transaction
 ├──── 📁 exception
 │      ├──── 📁 account
 │      │      ├──── 📁 deposit
 │      │      └──── 📁 withdraw
 │      ├──── 📁 authentication
 │      ├──── 📁 inquiry
 │      ├──── 📁 regex
 │      ├──── 📁 transaction
 │      └──── 📁 user
 │             ├──── 📁 admin
 │             └──── 📁 client
 ├──── 📁 repository
 ├──── 📁 service
 ├──── 📁 utils
 ├──── 📁 view
 └──── 📄 Application.java
```

- dto: 사용자에게 입력받는 데이터 혹은 출력하는 데이터를 담는 객체 
  - account: 계좌 조회, 계좌 등록
  - inquiry: 문의 조회, 문의 리스트 조회, 문의 등록, 문의 수정
  - transaction: 거래 내역 조횐
  - user: 관리자 조회, 고객 조회, 현재 로그인한 고객 조회, 고객 등록, 고객 정보 수정

- entity: 파일의 데이터를 담는 객체
  - 사용자(고객, 관리자), 계좌, 거래 내역, 문의

- enumeration: 객체의 속성으로 사용되는 열거형 상수
  - admin: 관리자 타입
  - client: 성별
  - inquiry: 문의 카테고리, 문의 처리 상태
  - transaction: 거래 상태, 거래 종류

- exception: 시스템에서 사용되는 사용자 정의 예외
  - account: 계좌 조회 불가, 유효하지 않는 계좌, ...
  - authentication: 인증 실패
  - inquiry: 문의 조회 불가, 존재하지 않는 문의
  - regex: 정규표현식 유효성
  - transaction: 거래내역 조회 불가, 이체 불가, ...
  - user: 계정 비활성화 상태, 존재하지 않는 계정, ...
  
- repository: 파일의 데이터를 저장하고 불러오는 객체

- service: 비즈니스 로직을 처리하는 객체

- utils: 시스템에서 사용되는 부가기능 객체

- view: 사용자에게 데이터를 입력받고 출력하는 객체

- Application.java: 프로그램을 실행하는 객체
