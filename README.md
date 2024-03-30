# 효성에프엠에스 풀스택 개발 전문가 양성과정 1기

## 1차 미니 프로젝트

## 0. 목차

- [Overview](#1-Overview)
- [Intro](#2-Intro)
- [Directory](#3-Directory)
- [TechStack](#4-TechStack)
- [Convention](#5-Convention)
- [Requirements](#6-Requirements)
- [UseCaseDiagram](#7-UseCaseDiagram)
- [SequenceDiagram](#8-SequenceDiagram)
- [FlowChart](9-FlowChart)
- [ClassDiagram](#10-ClassDiagram)
- [UnitTest](#11-UnitTest)
- [Collaboration](#12-Collaboraion)
- [TroubleShooting](#13-TroubleShooting)

## 1. Overview

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/class_diagram/%ED%81%B4%EB%9E%98%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.jpg">

## 2. Intro

- 프로젝트 이름: HS Bank
- 프로젝트 목적:
  - 금융 서비스의 대표적인 예시인 인터넷 뱅킹을 Java로 console과 In-memory를 이용해 간단하게 구현
  - I/O 및 Stream을 이용하여 고객을 위한 서비스 개발 및 개선 
- 프로젝트 기간: 2024.03.25. - 2024.03.27. (03.21. 시작)
- 언어: Java (JDK v17.0.10 & Eclipse v4.31.0)
- 멤버: 김상훈, 안수현, 이우성

## 3. Directory

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


## 4. TechStack

<details>
<summary>기술 요약</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/tech/%EA%B8%B0%EC%88%A0%20%EC%9A%94%EC%84%9C.png">

</details>

<details>
<summary>기술 상세</summary>

**1. BufferedReader**

자바에서 콘솔을 통해 사용자에게 입력받을 수 있는 방법은 Scanner 혹은 BufferedReader를 사용하는 것이다. Scanner는 1KB의 버퍼 사이즈를 갖고 있으며 데이터를 입력받는 즉시 전달한다. BufferedReader는 8KB의 버퍼 사이즈를 갖고 있으며 버퍼가 가득차거나 개행 문자가 나타나면 버퍼의 내용을 한번에 전달한다. Scanner는 입력받을 때마다 전달하기 때문에 한번에 읽어서 전달하는 BufferedReader보다 속도가 느리다. 

HS BANK 프로젝트는 콘솔 프로그램이므로 사용자의 입력을 빠르게 처리하는 것이 중요하다. 따라서 속도가 비교적 빠른 BufferedReader를 활용하여 프로그램의 성능을 향상하였다.

적용 : View.java

**2. 추상 클래스 & 인터페이스**

공통되는 속성과 함수가 여러 곳에 퍼질수록 유지보수성이 저하되는 것은 당연하다. HS BANK 프로그램에는 유지보수성을 향상시키기 위해 추상클래스를 활용하여 공통되는 속성과 함수를 모두 공통화하였다. 또한 추상 메서드를 활용하여 클래스에 특정 함수를 구현하는 것을 강제하였다.

적용 : Repository.java, UserService.java

**3. 사용자 정의 예외**

자바에서는 다양한 예외 클래스를 제공하지만, 때로는 개발자가 예외 클래스를 정의하여 사용자 예외 처리를 구현해야 할 때가 있다. 특히 HS BANK 프로그램은 금융 프로그램이기 때문에 예외의 의미를 정확하게 부여해야 한다. 때문에 사용자 예외 클래스를 정의하여 어떤 상황에 어떤 의미의 예외가 발생하는지 명확하게 하였다. 또한 사용자 예외를 화면 출력 클래스에서 처리하여 상황에 따라 어떤 화면을 출력할지 명시하였다. 이를 통해 유지보수성과 코드의 가독성을 크게 향상시켰다.

적용 : AccountNotFoundException.java

**4. 템플릿 메서드 패턴**

템플릿 메서드 패턴은 여러 클래스에서 공통으로 사용하는 메서드를 템플릿화하여 상위 클래스에 정의하고, 하위 클래스마다 세부 동작을 다르게 구현하는 패턴이다. 

HS BANK 프로그램의 화면 출력 로직은 출력 시작, 출력, 출력 종료로 공통된다. 이러한 로직을 공통화하기 위해 화면 출력 클래스에 템플릿 메서드 패턴을 적용하였다.

적용 : View.java

**5. 싱글톤 패턴**

싱글톤 패턴을 객체를 한번만 생성하여 재사용하는 패턴이다. 객체를 반복해서 생성하면 메모리 성능이 저하될 수 있다. 특히 프로그램의 사용자가 많은 경우 수많은 사용자의 요청을 처리하기 위해 객체를 반복하여 생성하면 메모리 누수 문제가 생길 수 있다. HS BANK 프로그램은 이러한 상황을 방지하고 메모리 효율을 높이기 위해 비즈니스 로직을 처리하는 클래스와 파일 IO 작업을 하는 클래스에 싱글톤 패턴을 적용하였다.

적용 : AccountService.java

**6. 롬복 Getter & Builder**

롬복을 사용하여 Getter 함수를 자동 생성하였다. 또한 Builder를 사용하여 복잡한 객체 생성 코드를 단순화시키고 객체의 불변성을 유지시켰다. 이를 통해 코드의 유지보수성과 가독성을 향상시켰다.

적용 : Account.java

**7. 제네릭 상속**

추상 클래스에 제네릭을 적용하여 공통 함수를 구현하는데 활용하였다. 특히 제네릭을 또 다른 추상 클래스에 상속시켜 제네릭의 타입을 명시하였다. 이를 통해 더 유연하게 공통 함수를 구현하도록 하였다.

적용 : Repository.java

**8. ArrayList**

데이터의 순서대로 아이디를 부여하는 HS BANK의 데이터 저장 로직에 적합한 자료구조인 ArrayList를 활용하였다.

적용 : Repository.java

**9. 객체 직렬화**

파일에 저장해야하는 객체를 직렬화하여 파일에 저장할 수 있도록 하였다. 그리고 역직렬화를 통해 파일에 저장된 객체를 읽어올 수 있도록 하였다.

적용 : Entity.java

**10. FileStream, BufferedStream, ObjectStream**

파일에 객체를 직렬화하여 저장하기 위해 FileOutputStream, BufferedOutputStream, ObjectOutputStream을 사용하였다. 또한 파일에 저장된 객체를 역직렬화하기 위해 FileInputStream, BufferedInputStream, ObjectInputStream을 활용하였다. 특히 BufferedOutputStream와 BufferedInputStream를 활용하여 IO 성능을 향상시켰다.

적용 : Repository.java

**11. 상수 & 열거형 상수**

여러 곳에서 반복 사용되는 데이터는 상수로 선언하였다. 그리고 카테고리가 분류되는 상수는 열거형 상수를 사용하여 유지보수성을 향상시켰다.

적용 : FilePathConstants, Gender.java

**12. 정적 메서드**

여러 곳에서 반복 사용되는 함수는 정적 메서드로 선언하였다. 이를 통해 유지보수성을 향상시켰다.

적용 : DateTimeGenerator.java

**13. BufferedWriter를 통한 로깅**

상세 예외 내용을 파일에 기록하기 위해 FileWriter를 사용하였다. 특히 BufferedWriter을 사용해 IO 성능을 향상시켰다.

적용 : BaseException.java

1**4. 람다 & 스트림**

파일의 내용을 담는 객체 리스트를 사용자에게 출력할 내용을 담는 객체로 변환하기 위해 람다와 스트림을 활용하였다. 이를 통해 코드의 가독성과 유지보수성을 향상시켰다.

적용 : AccountService.java

**15. 멀티 스레드**

HS BANK의 금융 상식 퀴즈 게임을 구현하기 위해 멀티 스레드를 사용하였다. 문제 출제와 카운트가 동시에 진행되게 하였다.

적용 : QuizView.java

**16. 자바 Swing**

금융 상식 퀴즈 화면을 자바 Swing으로 구현하여 사용자의 편의성을 향상시켰다.

적용 : QuizView.java

</details>

## 5. Convention

<details>
<summary>코드 정의</summary>

# 제목 없음

```
1. 개요
	1.1. 목적
	소프트웨어를 개발하는 모든 과정에 들어가는 비용 중 80%가 유지보수에 쓰여진다.
	소프트웨어의 직접 개발한 개발자가 그 소프트웨어의 유지보수를 담당하는 경우는 거의 볼 수
	없는 경우다. 규칙을 정하고 코드를 작성하게 되면 가독성이 높아져 다른 개발자가 소스
	코드를 처음 보더라도 더 빠르고 확실하게 이해할 수 있게 된다. 따라서 규칙을 정하고 지킴은
	개발자 간 반드시 지켜야 할 약속임을 항상 인지해야 한다.

2. 공통 법칙
	2.1. 작업 디렉토리
		2.1.1. 프로젝트, 패키지, 모듈, 클래스 형태로 형성한다.
	2.2 소스 파일
		2.2.1. 각 자바 소스 파일은 하나의 public class 혹은 public interface 혹은 public enum 을 
		포함한다.
		2.2.2. 하나의 Method 혹은 Class 에서 사용될 객체의 선언 및 생성은 상단에 위치시킨다.
3. 명명법 
3.1. 공통 규칙 
3.1.1. 이름만으로도 기능을 알 수 있도록 최대한 상세하게 명명한다. 
3.1.2. 대/소문자가 구분되며 길이에 제한이 없다. 
3.1.3. 예약어를 사용해서는 안 된다. 
3.1.4. 숫자로 시작해서는 안 된다. 
3.1.5. 특수문자는 ‘_’ 혹은 ‘$’ 만 허용한다. 
3.1.6. 파스칼 표기법(PascalCase)과 카멜 표기법(camelCase)을 사용한다. 
3.1.7. 반의어는 반드시 대응하는 개념으로 사용해야 한다. 
3.2. 프로젝트명 
3.2.1. 대/소문자 구분 없이 시작 가능하다.  
3.2.2. 대문자 사용을 권장한다. 
3.3. 패키지명 
3.3.1. 대/소문자를 모두 허용하지만 클래스명과 쉽게 구분하기 위해서 소문자로 하는 것을 
원칙으로 한다. 
3.3.2. 표준 패턴을 따른다. 
3.3.3. 가급적 한 단어 사용을 권장한다. 
3.4. 클래스명 
3.4.1. 파스칼 표기법을 사용한다. 
3.4.2. 명사로 시작한다. 
3.4.2.1. 단, DTO 클래스는 동사를 맨 앞에 붙힌다. 
3.4.3. DTO 클래스는 Dto로 끝나게 구성한다. 
3.4.4. Entity 클래스는 하나의 명사로 구성한다. 
3.4.5. Repository 클래스는 해당 Entity와 Repository를 붙혀서 구성한다. 
3.4.6. Service 클래스는 Entity와 Service를 붙혀서 구성한다. 
3.4.7. View 클래스는 View로 끝나게 구성한다. 
3.5. 인터페이스명 
3.5.1. 특별한 접두사나 접미사를 사용하지 않고 파스칼 표기법을 사용한다. 
3.5.2. 형용사를 사용한다.  
3.5.2.1. 단, 유저와 로그인은 명사로 시작한다. 
3.6. 메서드명 
카멜 표기법을 사용하며 동사로 시작한다. 
3.6.1. 속성에 접근하는 메서드명의 접두사는 get, set을 사용한다.  
3.6.2. 데이터를 조회하는 메서드명의 접두사는 find를 사용한다. 
3.6.3. 데이터를 입력하는 메서드명의 접두사는 Input을 사용한다. 
3.6.4. 데이터를 추가하는 메서드명의 접두사는 register를 사용한다. 
3.6.5. 데이터를 변경하는 메서드명의 접두사는 modify를 사용한다. 
3.6.6. 데이터를 삭제하는 메서드명의 접두사는 remove를 사용한다. 
3.6.7. 데이터를 초기화하는 메서드명의 접두사는 initialize를 사용한다. 
3.6.8. 반환 값의 타입이 boolean인 메서드명의 접두사는 is를 사용한다. 
3.6.9. 데이터를 불러오는 메서드명의 접두사는 load를 사용한다. 
3.6.10. 데이터가 있는지 확인하는 메서드명의 접두사는 has를 사용한다. 
3.6.11. 새로운 객체를 만든 뒤 해당 객체를 리턴해주는 메서드명의 접두사는 create를 사용한다.  
3.6.12. 해당 객체를 다른 형태의 객체로 변환해주는 메서드명의 접두사는 to를 사용한다. 
3.6.13. 해당 객체가 복수인지 단수인지 구분하는 메서드명의 접미사는 s를 사용한다. 
3.6.14. B를 기준으로 A를 하겠다는 메서드명의 전치사는 By를 사용한다. 
3.7. 변수명 
3.7.1. 소문자로 시작한다. 
3.7.2. 카멜 표기법을 사용한다. 
3.8. 상수명 
3.8.1. 전부 대문자로 표기한다. 
3.8.2. 스네이크 표기법(SNAKE_CASE)를 사용한다. 
3.9. 필드명 
3.9.1. 변수, 모든 인스턴스 등의 첫 번째 글자는 소문자로 하고, 이후 각 단어의 시작 문자는 
대문자로 한다. 
3.9.2. 클래스 변수의 이름은 타입의 이름과 동일하게 지정한다. 
3.9.3. select, count 등은 입력값, 제어문 혹은 반복문의 임시 변수명으로 사용할 수 있다. 
4. 주석문 
4.1. 공통 주석문 
4.1.1. 클래스의 주석문은 사용하지 않는다. 
4.2. 멤버 필드 주석문 
4.2.1. 멤버 필드 주석문은 라인 단위 ‘//’ 주석으로 한다. 
4.3. 멤버 메서드 주석문 
4.3.1. 멤버 메서드의 주석문은 필요에 따라 작성한다. 
4.4. 기타 주석문 
4.4.1. 코드 작성 중 설명이 필요한 부분의 경우 라인 단위 주석을 통해 기입한다. 
5. 기타 스타일 
5.1. 들여쓰기 
5.1.1. 들여쓰기는 이클립스 프로그램 내 자동 정렬 기능(ctrl+shift+F)을 사용한다. 
5.1.2. ‘{‘ 기호는 메서드 이름과 같은 줄에 위치하고, ‘}’ 기호는 다른 줄에 위치하며 해당 
줄에는 주석을 제외한 어떤 코드도 위치할 수 없다. 
5.2. 선언 
5.2.1. 패키지 / 임포트 
필요에 따라 package 문이 나타날 수 있다. package 문 이후 한 줄을 띄우고 다음으로 
import 문이 위치하도록 한다. import의 경우 한 줄에 하나의 import만 명시한다. 
5.2.2. 클래스 / 인터페이스 
import 문 이후 class 혹은 interface 선언문을 명시한다. class 혹은 interface 선언문의 
구성은 다음과 같은 순서로 나타낸다. 
5.2.2.1. class/interface 주석 
5.2.2.2. class/interface 선언문 
5.2.2.3. class/interface에 속하며 멤버 필드(private, protected, public 순으로 선언) 
5.2.2.4. class 객체 생성자 
5.2.2.5. class method 주석 
5.2.2.6. class method 객체에서 사용될 메서드 
5.3. 공백 
조건문, 제어문의 키워드(if, while, switch, for, return 등)와의 관계 
5.3.1. 시작 시 ‘(‘, ‘{‘의 괄호 앞에 한 칸의 빈칸을 포함하여 작성한다. 
5.3.2. 종료 시 ‘)’, ‘}’의 괄호 뒤에는 주석을 제외한 어떠한 코드도 작성하지 않는다.  
5.4. 제어문 및 반복문 
5.4.1. if 
if-else statement에서 else는 같은 줄에 위치한다. 
5.4.2. for 
for 문에서 ‘{‘는 같은 줄에, ‘}’는 새로운 줄에 위치한다. 
5.4.3. while 
while 문에서 ‘{‘는 같은 줄에, ‘}’는 새로운 줄에 위치한다. 
5.4.4. switch 
switch 문에서 실행 코드와 ‘break;’는 새로운 줄에 위치한다. 
5.4.5. try / catch / finally 
try-catch-finally 문에서 ‘{‘는 같은 줄에, ‘}’는 새로운 줄에 위치한다. 
6. 출력 형식 
6.1. 선택지 출력 형식 
6.1.1. 프로그램 실행 시 출력되는 선택지는 ‘[1] 선택지1  [2] 선택지2’의 양식을 따른다. 
6.2. 리스트 출력 형식 
6.2.1. 프로그램 실행 시 출력되는 리스트는 출력 값을 정해진 커스텀 형식을 사용한다.
```


1. 개요

1.1. 목적

소프트웨어를 개발하는 모든 과정에 들어가는 비용 중 80%가 유지보수에 쓰여진다. 
소프트웨어의 직접 개발한 개발자가 그 소프트웨어의 유지보수를 담당하는 경우는 거의 볼 수 
없는 경우다. 규칙을 정하고 코드를 작성하게 되면 가독성이 높아져 다른 개발자가 소스 
코드를 처음 보더라도 더 빠르고 확실하게 이해할 수 있게 된다. 따라서 규칙을 정하고 지킴은 
개발자 간 반드시 지켜야 할 약속임을 항상 인지해야 한다.

2. 공통 법칙

2.1. 작업 디렉토리

2.1.1. 프로젝트, 패키지, 모듈, 클래스 형태로 형성한다. 
2.2 소스 파일 
2.2.1. 각 자바 소스 파일은 하나의 public class 혹은 public interface 혹은 public enum 을 
포함한다. 
2.2.2. 하나의 Method 혹은 Class 에서 사용될 객체의 선언 및 생성은 상단에 위치시킨다. 
3. 명명법 
3.1. 공통 규칙 
3.1.1. 이름만으로도 기능을 알 수 있도록 최대한 상세하게 명명한다. 
3.1.2. 대/소문자가 구분되며 길이에 제한이 없다. 
3.1.3. 예약어를 사용해서는 안 된다. 
3.1.4. 숫자로 시작해서는 안 된다. 
3.1.5. 특수문자는 ‘_’ 혹은 ‘$’ 만 허용한다. 
3.1.6. 파스칼 표기법(PascalCase)과 카멜 표기법(camelCase)을 사용한다. 
3.1.7. 반의어는 반드시 대응하는 개념으로 사용해야 한다. 
3.2. 프로젝트명 
3.2.1. 대/소문자 구분 없이 시작 가능하다.  
3.2.2. 대문자 사용을 권장한다. 
3.3. 패키지명 
3.3.1. 대/소문자를 모두 허용하지만 클래스명과 쉽게 구분하기 위해서 소문자로 하는 것을 
원칙으로 한다. 
3.3.2. 표준 패턴을 따른다. 
3.3.3. 가급적 한 단어 사용을 권장한다. 
3.4. 클래스명 
3.4.1. 파스칼 표기법을 사용한다. 
3.4.2. 명사로 시작한다. 
3.4.2.1. 단, DTO 클래스는 동사를 맨 앞에 붙힌다. 
3.4.3. DTO 클래스는 Dto로 끝나게 구성한다. 
3.4.4. Entity 클래스는 하나의 명사로 구성한다. 
3.4.5. Repository 클래스는 해당 Entity와 Repository를 붙혀서 구성한다. 
3.4.6. Service 클래스는 Entity와 Service를 붙혀서 구성한다. 
3.4.7. View 클래스는 View로 끝나게 구성한다. 
3.5. 인터페이스명 
3.5.1. 특별한 접두사나 접미사를 사용하지 않고 파스칼 표기법을 사용한다. 
3.5.2. 형용사를 사용한다.  
3.5.2.1. 단, 유저와 로그인은 명사로 시작한다. 
3.6. 메서드명 
카멜 표기법을 사용하며 동사로 시작한다. 
3.6.1. 속성에 접근하는 메서드명의 접두사는 get, set을 사용한다.  
3.6.2. 데이터를 조회하는 메서드명의 접두사는 find를 사용한다. 
3.6.3. 데이터를 입력하는 메서드명의 접두사는 Input을 사용한다. 
3.6.4. 데이터를 추가하는 메서드명의 접두사는 register를 사용한다. 
3.6.5. 데이터를 변경하는 메서드명의 접두사는 modify를 사용한다. 
3.6.6. 데이터를 삭제하는 메서드명의 접두사는 remove를 사용한다. 
3.6.7. 데이터를 초기화하는 메서드명의 접두사는 initialize를 사용한다. 
3.6.8. 반환 값의 타입이 boolean인 메서드명의 접두사는 is를 사용한다. 
3.6.9. 데이터를 불러오는 메서드명의 접두사는 load를 사용한다. 
3.6.10. 데이터가 있는지 확인하는 메서드명의 접두사는 has를 사용한다. 
3.6.11. 새로운 객체를 만든 뒤 해당 객체를 리턴해주는 메서드명의 접두사는 create를 사용한다.  
3.6.12. 해당 객체를 다른 형태의 객체로 변환해주는 메서드명의 접두사는 to를 사용한다. 
3.6.13. 해당 객체가 복수인지 단수인지 구분하는 메서드명의 접미사는 s를 사용한다. 
3.6.14. B를 기준으로 A를 하겠다는 메서드명의 전치사는 By를 사용한다. 
3.7. 변수명 
3.7.1. 소문자로 시작한다. 
3.7.2. 카멜 표기법을 사용한다. 
3.8. 상수명 
3.8.1. 전부 대문자로 표기한다. 
3.8.2. 스네이크 표기법(SNAKE_CASE)를 사용한다. 
3.9. 필드명 
3.9.1. 변수, 모든 인스턴스 등의 첫 번째 글자는 소문자로 하고, 이후 각 단어의 시작 문자는 
대문자로 한다. 
3.9.2. 클래스 변수의 이름은 타입의 이름과 동일하게 지정한다. 
3.9.3. select, count 등은 입력값, 제어문 혹은 반복문의 임시 변수명으로 사용할 수 있다. 
4. 주석문 
4.1. 공통 주석문 
4.1.1. 클래스의 주석문은 사용하지 않는다. 
4.2. 멤버 필드 주석문 
4.2.1. 멤버 필드 주석문은 라인 단위 ‘//’ 주석으로 한다. 
4.3. 멤버 메서드 주석문 
4.3.1. 멤버 메서드의 주석문은 필요에 따라 작성한다. 
4.4. 기타 주석문 
4.4.1. 코드 작성 중 설명이 필요한 부분의 경우 라인 단위 주석을 통해 기입한다. 
5. 기타 스타일 
5.1. 들여쓰기 
5.1.1. 들여쓰기는 이클립스 프로그램 내 자동 정렬 기능(ctrl+shift+F)을 사용한다. 
5.1.2. ‘{‘ 기호는 메서드 이름과 같은 줄에 위치하고, ‘}’ 기호는 다른 줄에 위치하며 해당 
줄에는 주석을 제외한 어떤 코드도 위치할 수 없다. 
5.2. 선언 
5.2.1. 패키지 / 임포트 
필요에 따라 package 문이 나타날 수 있다. package 문 이후 한 줄을 띄우고 다음으로 
import 문이 위치하도록 한다. import의 경우 한 줄에 하나의 import만 명시한다. 
5.2.2. 클래스 / 인터페이스 
import 문 이후 class 혹은 interface 선언문을 명시한다. class 혹은 interface 선언문의 
구성은 다음과 같은 순서로 나타낸다. 
5.2.2.1. class/interface 주석 
5.2.2.2. class/interface 선언문 
5.2.2.3. class/interface에 속하며 멤버 필드(private, protected, public 순으로 선언) 
5.2.2.4. class 객체 생성자 
5.2.2.5. class method 주석 
5.2.2.6. class method 객체에서 사용될 메서드 
5.3. 공백 
조건문, 제어문의 키워드(if, while, switch, for, return 등)와의 관계 
5.3.1. 시작 시 ‘(‘, ‘{‘의 괄호 앞에 한 칸의 빈칸을 포함하여 작성한다. 
5.3.2. 종료 시 ‘)’, ‘}’의 괄호 뒤에는 주석을 제외한 어떠한 코드도 작성하지 않는다.  
5.4. 제어문 및 반복문 
5.4.1. if 
if-else statement에서 else는 같은 줄에 위치한다. 
5.4.2. for 
for 문에서 ‘{‘는 같은 줄에, ‘}’는 새로운 줄에 위치한다. 
5.4.3. while 
while 문에서 ‘{‘는 같은 줄에, ‘}’는 새로운 줄에 위치한다. 
5.4.4. switch 
switch 문에서 실행 코드와 ‘break;’는 새로운 줄에 위치한다. 
5.4.5. try / catch / finally 
try-catch-finally 문에서 ‘{‘는 같은 줄에, ‘}’는 새로운 줄에 위치한다. 
6. 출력 형식 
6.1. 선택지 출력 형식 
6.1.1. 프로그램 실행 시 출력되는 선택지는 ‘[1] 선택지1  [2] 선택지2’의 양식을 따른다. 
6.2. 리스트 출력 형식 
6.2.1. 프로그램 실행 시 출력되는 리스트는 출력 값을 정해진 커스텀 형식을 사용한다.


<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/code_convention/%EC%BD%94%EB%93%9C%20%EC%A0%95%EC%9D%98%EC%84%9C%201.png">


<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/code_convention/%EC%BD%94%EB%93%9C%20%EC%A0%95%EC%9D%98%EC%84%9C%202.png">

</details>

## 6. Requirements

<details>
<summary>요구사항 정의</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/requirements/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EC%A0%95%EC%9D%98%EC%84%9C.png">

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/requirements/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD%20%EB%AA%85%EC%84%B8%EC%84%9C.png">

</details>

## 7. UseCaseDiagram

<details>
<summary>유스케이스 다이어그램</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/usecase_diagram/%EC%9C%A0%EC%8A%A4%EC%BC%80%EC%9D%B4%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8%20%EA%B3%A0%EA%B0%9D.jpg">

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/usecase_diagram/%EC%9C%A0%EC%8A%A4%EC%BC%80%EC%9D%B4%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8%20%EA%B4%80%EB%A6%AC%EC%9E%90.jpg">

</details>

## 8. SequenceDiagram

<details>
<summary>시퀀스 다이어그램</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/sequence_diagram/%EC%8B%9C%ED%80%80%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png">

</details>

## 9. FlowChart

<details>
<summary>플로우 차트</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/sequence_diagram/%EC%8B%9C%ED%80%80%EC%8A%A4%20%EB%8B%A4%EC%9D%B4%EC%96%B4%EA%B7%B8%EB%9E%A8.png">

</details>

## 10. ClassDiagram

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

## 11. UnitTest

<details>
<summary>단위 테스트 시나리오</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/test_scenario/%EB%8B%A8%EC%9C%84%20%ED%85%8C%EC%8A%A4%ED%8A%B8%20%EC%8B%9C%EB%82%98%EB%A6%AC%EC%98%A4.png">

</details>

## 12. Collaboration

<details>
<summary>회의</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/collaboration/meeting.png">

</details>

<details>
<summary>Slack</summary>

<img src="https://github.com/rlatkd/hs-bank/blob/main/assets/collaboration/slack.png">

</details>

## 13. TroubleShooting

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