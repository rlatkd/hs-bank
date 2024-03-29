<details>
<summary>디렉토리 구조</summary>

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

</details>

<details>
<summary>디렉토리 개요</summary>

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

</details>

<details>
<summary>디렉토리 상세 구조</summary>

```
📁 HS_BANK
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

</details>

<details>
<summary>요구사항 명세</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/requirements/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EB%AA%85%EC%84%B8%EC%84%9C.png">


<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/requirements/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EC%A0%95%EC%9D%98%EC%84%9C.png">

</details>


<details>
<summary>기술 요서</summary>

1. **BufferedReader**

자바에서 콘솔을 통해 사용자에게 입력받을 수 있는 방법은 Scanner 혹은 BufferedReader를 사용하는 것이다. Scanner는 1KB의 버퍼 사이즈를 갖고 있으며 데이터를 입력받는 즉시 전달한다. BufferedReader는 8KB의 버퍼 사이즈를 갖고 있으며 버퍼가 가득차거나 개행 문자가 나타나면 버퍼의 내용을 한번에 전달한다. Scanner는 입력받을 때마다 전달하기 때문에 한번에 읽어서 전달하는 BufferedReader보다 속도가 느리다. 

HS BANK 프로젝트는 콘솔 프로그램이므로 사용자의 입력을 빠르게 처리하는 것이 중요하다. 따라서 속도가 비교적 빠른 BufferedReader를 활용하여 프로그램의 성능을 향상하였다.

적용 : View.java

2. **추상 클래스 & 인터페이스**

공통되는 속성과 함수가 여러 곳에 퍼질수록 유지보수성이 저하되는 것은 당연하다. HS BANK 프로그램에는 유지보수성을 향상시키기 위해 추상클래스를 활용하여 공통되는 속성과 함수를 모두 공통화하였다. 또한 추상 메서드를 활용하여 클래스에 특정 함수를 구현하는 것을 강제하였다.

적용 : Repository.java, UserService.java

3. **사용자 정의 예외**

자바에서는 다양한 예외 클래스를 제공하지만, 때로는 개발자가 예외 클래스를 정의하여 사용자 예외 처리를 구현해야 할 때가 있다. 특히 HS BANK 프로그램은 금융 프로그램이기 때문에 예외의 의미를 정확하게 부여해야 한다. 때문에 사용자 예외 클래스를 정의하여 어떤 상황에 어떤 의미의 예외가 발생하는지 명확하게 하였다. 또한 사용자 예외를 화면 출력 클래스에서 처리하여 상황에 따라 어떤 화면을 출력할지 명시하였다. 이를 통해 유지보수성과 코드의 가독성을 크게 향상시켰다.

적용 : AccountNotFoundException.java

4. **템플릿 메서드 패턴**

템플릿 메서드 패턴은 여러 클래스에서 공통으로 사용하는 메서드를 템플릿화하여 상위 클래스에 정의하고, 하위 클래스마다 세부 동작을 다르게 구현하는 패턴이다. 

HS BANK 프로그램의 화면 출력 로직은 출력 시작, 출력, 출력 종료로 공통된다. 이러한 로직을 공통화하기 위해 화면 출력 클래스에 템플릿 메서드 패턴을 적용하였다.

적용 : View.java

5. **싱글톤 패턴**

싱글톤 패턴을 객체를 한번만 생성하여 재사용하는 패턴이다. 객체를 반복해서 생성하면 메모리 성능이 저하될 수 있다. 특히 프로그램의 사용자가 많은 경우 수많은 사용자의 요청을 처리하기 위해 객체를 반복하여 생성하면 메모리 누수 문제가 생길 수 있다. HS BANK 프로그램은 이러한 상황을 방지하고 메모리 효율을 높이기 위해 비즈니스 로직을 처리하는 클래스와 파일 IO 작업을 하는 클래스에 싱글톤 패턴을 적용하였다.

적용 : AccountService.java

6. **롬복 Getter & Builder**

롬복을 사용하여 Getter 함수를 자동 생성하였다. 또한 Builder를 사용하여 복잡한 객체 생성 코드를 단순화시키고 객체의 불변성을 유지시켰다. 이를 통해 코드의 유지보수성과 가독성을 향상시켰다.

적용 : Account.java

7. **제네릭 상속**

추상 클래스에 제네릭을 적용하여 공통 함수를 구현하는데 활용하였다. 특히 제네릭을 또 다른 추상 클래스에 상속시켜 제네릭의 타입을 명시하였다. 이를 통해 더 유연하게 공통 함수를 구현하도록 하였다.

적용 : Repository.java

8. **ArrayList**

데이터의 순서대로 아이디를 부여하는 HS BANK의 데이터 저장 로직에 적합한 자료구조인 ArrayList를 활용하였다.

적용 : Repository.java

9. **객체 직렬화**

파일에 저장해야하는 객체를 직렬화하여 파일에 저장할 수 있도록 하였다. 그리고 역직렬화를 통해 파일에 저장된 객체를 읽어올 수 있도록 하였다.

적용 : Entity.java

10. **FileStream, BufferedStream, ObjectStream**

파일에 객체를 직렬화하여 저장하기 위해 FileOutputStream, BufferedOutputStream, ObjectOutputStream을 사용하였다. 또한 파일에 저장된 객체를 역직렬화하기 위해 FileInputStream, BufferedInputStream, ObjectInputStream을 활용하였다. 특히 BufferedOutputStream와 BufferedInputStream를 활용하여 IO 성능을 향상시켰다.

적용 : Repository.java

11. **상수 & 열거형 상수**

여러 곳에서 반복 사용되는 데이터는 상수로 선언하였다. 그리고 카테고리가 분류되는 상수는 열거형 상수를 사용하여 유지보수성을 향상시켰다.

적용 : FilePathConstants, Gender.java

12. **정적 메서드**

여러 곳에서 반복 사용되는 함수는 정적 메서드로 선언하였다. 이를 통해 유지보수성을 향상시켰다.

적용 : DateTimeGenerator.java

13. **BufferedWriter를 통한 로깅**

상세 예외 내용을 파일에 기록하기 위해 FileWriter를 사용하였다. 특히 BufferedWriter을 사용해 IO 성능을 향상시켰다.

적용 : BaseException.java

14. **람다 & 스트림**

파일의 내용을 담는 객체 리스트를 사용자에게 출력할 내용을 담는 객체로 변환하기 위해 람다와 스트림을 활용하였다. 이를 통해 코드의 가독성과 유지보수성을 향상시켰다.

적용 : AccountService.java

15. **멀티 스레드**

HS BANK의 금융 상식 퀴즈 게임을 구현하기 위해 멀티 스레드를 사용하였다. 문제 출제와 카운트가 동시에 진행되게 하였다.

적용 : QuizView.java

16. **자바 Swing**

금융 상식 퀴즈 화면을 자바 Swing으로 구현하여 사용자의 편의성을 향상시켰다.

적용 : QuizView.java

</details>

<details>
<summary>유스케이스 다이어그램</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/usecase_diagram/%EC%9C%A0%EC%8A%A4%EC%BC%80%EC%9D%B4%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8%20%EA%B3%A0%EA%B0%9D.jpg">

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/usecase_diagram/%EC%9C%A0%EC%8A%A4%EC%BC%80%EC%9D%B4%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8%20%EA%B4%80%EB%A6%AC%EC%9E%90.jpg">

</details>

<details>
<summary>시퀀스 다이어그램</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/sequence_diagram/%EC%8B%9C%ED%80%80%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png">

</details>

<details>
<summary>단위 테스트 시나리오</summary>

<img src="">




<details>
<summary>트러블슈팅</summary>



</details>
