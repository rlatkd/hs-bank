# 효성에프엠에스 풀스택 개발 전문가 양성과정 1기

## 1차 미니 프로젝트

## 클래스 다이어그램

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/%ED%81%B4%EB%9E%98%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.jpg">

<details>
<summary>View</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/View.png">

</details>

<details>
<summary>Service</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Service.png">

</details>

<details>
<summary>Repository</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Repository.png">

</details>

<details>
<summary>Entity</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Entity.png">

</details>

<details>
<summary>Dto</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Dto.png">

</details>

<details>
<summary>Exception</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Exception.png">

</details>

<details>
<summary>Enum</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Enum.png">

</details>

<details>
<summary>Utils</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/Utils.png">

</details>

## 서비스 개요

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
<summary>코드 정의</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/code_convention/%EC%BD%94%EB%93%9C%20%EC%A0%95%EC%9D%98%EC%84%9C%201.png">


<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/code_convention/%EC%BD%94%EB%93%9C%20%EC%A0%95%EC%9D%98%EC%84%9C%202.png">

</details>


<details>
<summary>요구사항 명세</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/requirements/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EB%AA%85%EC%84%B8%EC%84%9C.png">


<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/requirements/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EC%A0%95%EC%9D%98%EC%84%9C.png">

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

<summary>플로우 차트</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/sequence_diagram/%EC%8B%9C%ED%80%80%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png">

</details>

<details>
<summary>기술 요서</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/tech/%EA%B8%B0%EC%88%A0%20%EC%9A%94%EC%84%9C.png">

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
<summary>단위 테스트 시나리오</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/test_scenario/%EB%8B%A8%EC%9C%84%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4.png">

</details>

## 협업

<details>
<summary>회의</summary>



</details>

## 트러블 슈팅

<details>
<summary>이체 시 잔액 반영 오류 </summary>

### 문제 상황

TransactionService에서 이체 기능을 제공하는 transfer 함수를 개발하고 테스트하는 과정에서 문제가 발생했다.

```java
    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount) throws BaseException {
        Account withdrawAccount = accountRepository.get(withdrawAccountId);
        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.get(depositAccountNumber);
        if(depositAccount == null) throw new DepositAccountNotFoundException();
        if(!isActiveAccount(depositAccount)) throw new DepositAccountDeactivateException();

        Transaction transaction = Transaction.builder().
                date(DateTimeGenerator.getDateTimeNow()).
                type(TransactionType.TRANSFER).
                amount(amount).
                withdrawAccountId(withdrawAccountId).
                depositAccountId(depositAccount.getId()).
                status(TransactionStatus.COMPLETE).
                build();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        depositAccount.setBalance(depositAccount.getBalance() + amount);
        accountRepository.update();

        transactionRepository.add(transaction);
    }
```

transfer 함수의 코드이다. AccountRepository의 get 함수를 사용해서 출금 계좌와 입금 계좌를 가져온다. 

일단 출금 계좌와 입금 계좌가 이체 가능한 상태인지 확인한다. 

만약 이체 가능한 상태라면 출금 계좌와 입금 계좌의 잔액을 수정하고, 거래 내역을 추가한다.

이 함수를 사용해서 이체를 진행해보자.

```bash
GetAccountDto{id=1, bankName='신한', number='110466796544', ownerName='이우성', balance=10000, registeredAt='2024-03-26 11:29:29', status='활성화'}

GetAccountDto{id=2, bankName='카카오뱅크', number='45832813', ownerName='이우성', balance=0, registeredAt='2024-03-26 11:30:07', status='활성화'}
```

현재 계좌 목록이다. 

1번 계좌에서 2번 계좌로 5000원 이체를 진행할 것이다.

```bash
GetAccountDto{id=1, bankName='신한', number='110466796544', ownerName='이우성', balance=10000, registeredAt='2024-03-26 11:29:29', status='활성화'}

GetAccountDto{id=2, bankName='카카오뱅크', number='45832813', ownerName='이우성', balance=5000, registeredAt='2024-03-26 11:30:07', status='활성화'}
```

이체를 진행한 후 결과이다. 

잔액을 보면 2번 계좌에 5000원이 입금되긴 했지만, 1번 계좌에서 5000원이 출금이 되지 않았다.

### 원인

```java
    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount) throws BaseException {
        Account withdrawAccount = accountRepository.get(withdrawAccountId);
//        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
//        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
//        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.get(depositAccountNumber);
//        if(depositAccount == null) throw new DepositAccountNotFoundException();
//        if(!isActiveAccount(depositAccount)) throw new DepositAccountDeactivateException();

//        Transaction transaction = Transaction.builder().
//                date(DateTimeGenerator.getDateTimeNow()).
//                type(TransactionType.TRANSFER).
//                amount(amount).
//                withdrawAccountId(withdrawAccountId).
//                depositAccountId(depositAccount.getId()).
//                status(TransactionStatus.COMPLETE).
//                build();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        depositAccount.setBalance(depositAccount.getBalance() + amount);
        accountRepository.update();

//        transactionRepository.add(transaction);
    }

```

transfer 함수 코드를 다시 보자. 

문제가 되는 부분을 제외하고 모두 주석 처리했다.

여기서 AccountRepository의 함수는 get(int id), get(String number), update()가 사용된다.

```java
    public final E get(int id) throws BaseException {
        load();
        for(Entity entity : entityList)
            if(entity.getId() == id) return (E)entity;
        return null;
    }
    
    public Account get(String number) throws BaseException {
        load();
        for(Account account : entityList)
            if(account.getNumber().equals(number)) return account;
        return null;
    }
    
    public final void update() throws BaseException {
        save();
    }
```

AccountRepository의 get(int id), get(String number), update() 코드이다. 

get(int id)와 update()는 AccountRepository의 추상 클래스 Repository의 함수이다.

get(int id)과 get(String number)은 제일 먼저 load()를 호출한다.

update()는 save()를 호출한다.

```java
    protected final void load() throws BaseException {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);

            Object object = null;
            while ((object = objectInputStream.readObject()) != null)
                entityList = (ArrayList<E>) object;
        } catch (EOFException e) {
            log(e);
        } catch (IOException | ClassNotFoundException e) {
            log(e);
            throw new DataAccessException();
        } finally {
            try {
                if(objectInputStream != null) objectInputStream.close();
                if(bufferedInputStream != null) bufferedInputStream.close();
                if(fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                log(e);
                throw new DataAccessException();
            }
        }
    }
```

Repository의 load() 코드이다. 파일의 내용을 ArrayList<E>로 변환해서 멤버 변수에 담는 역할을 한다. 

다시 말해 현재 파일의 내용을 Repository의 entityList에 최신화하는 것이다.

```java
    protected final void save() throws BaseException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(entityList);
        } catch (IOException e) {
            log(e);
            throw new DataAccessException();
        } finally {
            try {
                objectOutputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                log(e);
                throw new DataAccessException();
            }
        }
    }
```

Repository의 save() 코드이다. 

entityList의 현재 내용을 파일에 저장하는 역할을 한다.

```java
    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount) throws BaseException {
        Account withdrawAccount = accountRepository.get(withdrawAccountId);
//        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
//        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
//        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.get(depositAccountNumber);
//        if(depositAccount == null) throw new DepositAccountNotFoundException();
//        if(!isActiveAccount(depositAccount)) throw new DepositAccountDeactivateException();

//        Transaction transaction = Transaction.builder().
//                date(DateTimeGenerator.getDateTimeNow()).
//                type(TransactionType.TRANSFER).
//                amount(amount).
//                withdrawAccountId(withdrawAccountId).
//                depositAccountId(depositAccount.getId()).
//                status(TransactionStatus.COMPLETE).
//                build();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        depositAccount.setBalance(depositAccount.getBalance() + amount);
        accountRepository.update();

//        transactionRepository.add(transaction);
    }
```

다시 transfer 함수로 돌아오자. 

get(int id)와 get(String number)는 모두 load()를 호출한다.

그리고 각 함수는 load()를 호출한다.

그렇게 되면 AccountRepository의 entityList는 마지막으로 실행된 get(String number)에 의해 주소가 변경된다.

get(int id)를 호출했을 때는 AccountRepository의 entityList가 1번 주소를 참조하고 있었다면, 그 후 get(String number)를 호출했을 때는 entityList가 2번 주소를 참조하게 되는 것이다.

따라서 withdrawAccount는 1번 주소를 참조하고 있는 entityList에 있는 Account를 참조하게 되고, depositAccount는 2번 주소를 참조하고 있는 entityList에 있는 Account를 참조하게 된다.

때문에 withdrawAccount를 수정하고 update()를 호출해도, update()는 AccountReposiotry의 2번 주소를 참조하고 있는 entityList를 파일에 반영하기 때문에 withdrawAccount의 수정 사항은 반영이 안되는 것이다.

### 해결

transfer 함수 내에서 load()를 두 번 호출하여 생긴 문제이니, 한 번만 호출하도록 코드를 수정하면 된다.

```java
    public Account getWithoutLoad(String number){
        for(Account account : entityList)
            if(account.getNumber().equals(number)) return account;
        return null;
    }
```

AccountRepository에 load()를 호출하지 않는 getWithoutLoad(String number)를 만들었다.

```java
    public synchronized void transfer(int withdrawAccountId, String depositAccountNumber, long amount) throws BaseException {
        Account withdrawAccount = accountRepository.get(withdrawAccountId);
//        if(withdrawAccount == null) throw new WithdrawAccountNotFoundException();
//        if(!isActiveAccount(withdrawAccount)) throw new WithdrawAccountDeactivateException();
//        if(withdrawAccount.getBalance() < amount) throw new BalanceInsufficientException();

        Account depositAccount = accountRepository.getWithoutLoad(depositAccountNumber);
//        if(depositAccount == null) throw new DepositAccountNotFoundException();
//        if(!isActiveAccount(depositAccount)) throw new DepositAccountDeactivateException();

//        Transaction transaction = Transaction.builder().
//                date(DateTimeGenerator.getDateTimeNow()).
//                type(TransactionType.TRANSFER).
//                amount(amount).
//                withdrawAccountId(withdrawAccountId).
//                depositAccountId(depositAccount.getId()).
//                status(TransactionStatus.COMPLETE).
//                build();

        withdrawAccount.setBalance(withdrawAccount.getBalance() - amount);

        depositAccount.setBalance(depositAccount.getBalance() + amount);
        accountRepository.update();

//        transactionRepository.add(transaction);
    }
```

수정된 transfer 함수 코드이다.

get(int id)에 의해서 이미 load()가 실행됐으니, 그 다음부터는 load()가 실행되지 않도록 getWithoutLoad(String number)를 호출한다.

```java
GetAccountDto{id=1, bankName='신한', number='110466796544', ownerName='이우성', balance=5000, registeredAt='2024-03-26 11:29:29', status='활성화'}

GetAccountDto{id=2, bankName='카카오뱅크', number='45832813', ownerName='이우성', balance=5000, registeredAt='2024-03-26 11:30:07', status='활성화'}
```

수정된 코드를 테스트한 결과이다.

아까와 달리 1번 계좌에서 5000원이 출금되었다.

</details>

<details>
<summary>Repository 반복 로직</summary>

### 문제상황

```java
public abstract class Repository<E> {
    protected List<E> entityList;
    protected String path;

    protected Repository(String path) {
        this.entityList = new ArrayList<>();
        this.path = path;
    }

    protected final void load() throws BaseException {
        FileInputStream fileInputStream = null;
        BufferedInputStream bufferedInputStream = null;
        ObjectInputStream objectInputStream = null;
        try {
            fileInputStream = new FileInputStream(path);
            bufferedInputStream = new BufferedInputStream(fileInputStream);
            objectInputStream = new ObjectInputStream(bufferedInputStream);

            Object object = null;
            while ((object = objectInputStream.readObject()) != null)
                entityList = (ArrayList<E>) object;
        } catch (EOFException e) {
            log(e);
        } catch (IOException | ClassNotFoundException e) {
            log(e);
            throw new DataAccessException();
        } finally {
            try {
                if(objectInputStream != null) objectInputStream.close();
                if(bufferedInputStream != null) bufferedInputStream.close();
                if(fileInputStream != null) fileInputStream.close();
            } catch (IOException e) {
                log(e);
                throw new DataAccessException();
            }
        }
    }
    protected final void save() throws BaseException {
        FileOutputStream fileOutputStream = null;
        BufferedOutputStream bufferedOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(path);
            bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
            objectOutputStream = new ObjectOutputStream(bufferedOutputStream);

            objectOutputStream.writeObject(entityList);
        } catch (IOException e) {
            log(e);
            throw new DataAccessException();
        } finally {
            try {
                objectOutputStream.close();
                bufferedOutputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                log(e);
                throw new DataAccessException();
            }
        }
    }
    
    public final void update() throws BaseException {
        save();
    }
    
    protected final E getLastEntity() {
        return entityList.isEmpty() ? null : entityList.get(entityList.size() - 1);
    }

```

추상 클래스 Repository의 코드이다. 

load()는 파일의 내용을 entityList에 담는다. 

save()는 entityList를 파일에 저장한다.

update()는 save()를 호출한다.

getLastEntity()는 entityList에서 가장 마지막 주소에 있는 요소를 반환한다.

```java
public class ClientRepository extends Repository<Client> {
    private static ClientRepository clientRepository;
    private ClientRepository() {
        super(FilePathConstants.CLIENT_PATH);
    }

    public static ClientRepository getInstance(){
        if(clientRepository == null)
            clientRepository = new ClientRepository();
        return clientRepository;
    }

    public void add(Client client) throws BaseException {
        load();
        client.setId(getLastEntity() == null ? 1 : getLastEntity().getId() + 1);
        entityList.add(client);
        save();
    }
    public Account get(int id) throws BaseException {
        load();
        for(Client client : entityList)
            if(client.getId() == id) return client;
        return null;
    }
    public List<Account> getClientList() throws BaseException {
        load();
        return entityList;
    }
    public void remove(int id) throws BaseException {
        load();
        for(int i = 0; i < entityList.size(); i++){
            if(entityList.get(i).getId() == id) entityList.remove(i);
            break;
        }
        save();
    }
```

Repository의 자식인 AccountRepository이다.

add, get, getAccountList, remove 함수가 구현되어 있다.

```java
public class AccountRepository extends Repository<Account> {
    private static AccountRepository accountRepository;
    private AccountRepository() {
        super(FilePathConstants.ACCOUNT_PATH);
    }

    public static AccountRepository getInstance(){
        if(accountRepository == null)
            accountRepository = new AccountRepository();
        return accountRepository;

    }

    public void add(Account account) throws BaseException {
        load();
        account.setId(getLastEntity() == null ? 1 : getLastEntity().getId() + 1);
        entityList.add(account);
        save();
    }
    public Account get(int id) throws BaseException {
        load();
        for(Account account : entityList)
            if(account.getId() == id) return account;
        return null;
    }
    public List<Account> getAccountList() throws BaseException {
        load();
        return entityList;
    }
    public void remove(int id) throws BaseException {
        load();
        for(int i = 0; i < entityList.size(); i++){
            if(entityList.get(i).getId() == id) entityList.remove(i);
            break;
        }
        save();
    }
```

Repository의 자식인 AccountRepository이다.

add, get, getAccountList, remove 함수가 구현되어 있다.

ClientRepository와 AccountRepository의 함수가 반복되는 것을 볼 수 있다.

불필요한 반복은 유지보수를 어렵게 하니, 반복 로직을 공통화해야한다.

### 해결

```java
    public void add(Client client) throws BaseException {
        load();
        client.setId(getLastEntity() == null ? 1 : getLastEntity().getId() + 1);
        entityList.add(client);
        save();
    }
    public Account get(int id) throws BaseException {
        load();
        for(Client client : entityList)
            if(client.getId() == id) return client;
        return null;
    }
    public void remove(int id) throws BaseException {
        load();
        for(int i = 0; i < entityList.size(); i++){
            if(entityList.get(i).getId() == id) entityList.remove(i);
            break;
        }
        save();
    }
```

add, get, remove는 E의 id로 로직을 처리하는데, E가 id를 가지고 있는지는 Repository에서 알 수가 없다.

```java
@Getter
@SuperBuilder
public abstract class Entity implements Serializable {
    protected int id;
    public final void setId(int id){
        this.id = id;
    }
}
```

Client와 Account의 부모인 Entity는 id를 가지고 있다.

Repository에서 제네릭 E를 Entity에 상속시키면 E가 id를 갖고 있다는 것을 Repository가 알 수 있다.

```java
public abstract class Repository<E extends Entity> {
```

Repository로 이동해 제네릭 E를 Entity에 상속시켜 E가 id를 갖고 있다는 것을 명시한다.

이제 반복 로직을 공통화 해보자.

```java
public abstract class Repository<E extends Entity> {
    protected List<E> entityList;
    protected String path;

    protected Repository(String path) {
        this.entityList = new ArrayList<>();
        this.path = path;
    }

    protected final void load() throws BaseException {
				.....
    }
    protected final void save() throws BaseException {
        .....
    }
    public final void update() throws BaseException {
        save();
    }
    protected final E getLastEntity() {
        return entityList.isEmpty() ? null : entityList.get(entityList.size() - 1);
    }
    
    // 공통화 코드
    public final void add(E entity) throws BaseException {
        load();
        entity.setId(getLastEntity() == null ? 0 : getLastEntity().getId() + 1);
        entityList.add(entity);
        save();
    }
    public final E get(int id) throws BaseException {
        load();
        for(Entity entity : entityList)
            if(entity.getId() == id) return (E)entity;
        return null;
    }
    public final List<E> getEntityList() throws BaseException {
        load();
        return entityList;
    }
    public final void remove(int id) throws BaseException {
        load();
        for(int i = 0; i < entityList.size(); i++){
            if(entityList.get(i).getId() == id) entityList.remove(i);
            break;
        }
        save();
    }
}
```

제네릭과 상속을 활용해서 반복되는 로직을 Repository에 공통화한 코드이다.

</details>

<details>
<summary>Exception 상속</summary>

### 문제 상황

```java
    public synchronized void cancelTransaction(int id) throws 
            TransactionNotFoundException,
            NotTransferException,
            WithdrawAccountNotFoundException,
            WithdrawAccountDeactivateException,
            BalanceInsufficientException,
            DepositAccountNotFoundException,
            DepositAccountDeactivateException,
            DataAccessException {
```

cancelTransaction 함수는 이체를 취소하는 기능을 한다. 

해당 함수는 사용자 정의 예외를 총 8개 던지고 있다.

해당 함수를 사용하는 것은 고객에게 보여줄 화면을 출력하는 View 클래스다.

View 클래스는 해당 함수를 사용하려면 예외 처리를 8번해야한다.

하지만 현재 개발 중인 시스템은 콘솔 프로그램이기 때문에 예외가 발생해도 View는 경고 메시지를 출력하고 다시 전 화면을 출력하는 것이 예외 처리의 전부다.

즉 cancelTransaction 함수가 예외를 여러 개 던져도 View에서는 예외 처리가 공통된다는 것이다.

따라서 예외를 여러 개 던질 필요가 없다.

### 해결

```java
public abstract class BaseException extends Exception{

    public BaseException(){
        super("시스템에 오류가 발생했습니다. 다시 시도해주세요.");
    }

    public BaseException(String message)  {
        super(message);
    }
```

BaseException이라는 추상 클래스를 만들고 Exception을 상속시킨다.

시스템 내의 모든 사용자 예외는 BaseException을 상속받도록 처리한다.

```java
public synchronized void cancelTransaction(int id) throws BaseException {
```

cancelTransaction 함수 내에서 발생시키는 예외는 모두 BaseException의 자식이기 때문에 BaseException만 던지면 된다.

</details>

<details>
<summary>Exception 로깅</summary>

### 문제상황

e.printStackTrace()를 사용하면 예외 상세내용을 쉽게 확인할 수 있지만, 현재 개발 중인 시스템은 콘솔 프로그램이기 때문에 예외가 발생해도 예외의 상세내용을 출력할 수 없다.

때문에 예외가 발생하더라도 출력할 수 있는 내용은 사용자에게 보여줄 경고 메시지밖에 없다.

문제는 예외가 발생하여도 예외의 상세내용을 개발자도 볼 수 없다는 것이다.

따라서 예외의 상세내용은 별도로 파일에 기록해서 개발자가 열람할 수 있도록 하고, 사용자에게는 경고 메시지만 출력하도록 해야한다.

### 해결

```java
public abstract class BaseException extends Exception{

    public BaseException(){
        super("시스템에 오류가 발생했습니다. 다시 시도해주세요.");
    }

    public BaseException(String message) throws BaseException {
        super(message);
        log();
    }

    private void log() throws BaseException {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            printStackTrace(printWriter);

            fileWriter =  new FileWriter(FilePathConstants.LOG_PATH, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[" + DateTimeGenerator.getDateTimeNow() + "] " + stringWriter.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            log(e);
            throw new LogException();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
                stringWriter.close();
                printWriter.close();
            } catch (IOException e) {
                log(e);
                throw new LogException();
            }
        }
    }
}
```

개발자가 만든 예외는 모두 BaseException을 부모로 가진다. BaseException이 생성되면 log()를 호출한다.

log()는 예외 상세내용을 로그 파일에 기록한다.

만약 발생한 예외가 BaseException이라면 로그에 기록이 남겠지만, BaseException이 아니라면 기록이 남지 않는다.

BaseException이 아닌 예외가 발생할 수도 있기 때문에 예외를 받아 로그에 기록하는 함수가 필요하다.

```java
    public static void log(Exception exception) throws BaseException {
        StringWriter stringWriter = null;
        PrintWriter printWriter = null;

        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try{
            stringWriter = new StringWriter();
            printWriter = new PrintWriter(stringWriter);
            exception.printStackTrace(printWriter);

            fileWriter =  new FileWriter(FilePathConstants.LOG_PATH, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write("[" + DateTimeGenerator.getDateTimeNow() + "] " + stringWriter);
            bufferedWriter.newLine();
        } catch (IOException e) {
            throw new LogException();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
                stringWriter.close();
                printWriter.close();
            } catch (IOException e) {
                throw new LogException();
            }
        }
    }
```

log(Exception exception)는 파라미터로 받은 Exception의 상세내용을 로그 파일에 기록한다. 정적 함수이기 때문에 어디서든 호출할 수 있다.

```java
try {
    FileInputStream fis = new FileInputStream(path);
} catch (FileNotFoundException e) {
    log(e);
    throw new DataAccessException();
}
```

BaseException이 아닌 예외가 발생하면 catch문에서 log(Exception exception)을 호출한다.

</details>