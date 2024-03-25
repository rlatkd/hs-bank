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
 │      │      │      ├──── 📄 RegisterAccountDto.java
 │      │      │      └──── 📄 GetAccountDto.java
 │      │      ├──── 📁 inquiry
 │      │      │      ├──── 📄 EditInquiryDto.java
 │      │      │      ├──── 📄 GetInquiryDto.java
 │      │      │      ├──── 📄 GetInquiryListDto.java
 │      │      │      └──── 📄 RegisterInquiryDto.java
 │      │      ├──── 📁 transaction
 │      │      │      └──── 📁 request
 │      │      │             └──── 📄 GetTransactionDto.java
 │      │      └──── 📁 user
 │      │             ├──── 📁 adimin
 │      │             │      └──── 📄 RegisterAdminDto.java
 │      │             ├──── 📁 client
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
 │      │      ├──── 📁 inquiry
 │      │      │      ├──── 📄 InquiryListEmptyException.java
 │      │      │      └──── 📄 InquiryNotFoundException.java
 │      │      ├──── 📁 transaction
 │      │      │      ├──── 📄 NotTransferException.java
 │      │      │      ├──── 📄 TransactionListEmptyException.java
 │      │      │      └──── 📄 TransactionNotFoundException.java
 │      │      ├──── 📁 user
 │      │      │      ├──── 📁 admin
 │      │      │      │      ├──── 📄 AdminExistException.java
 │      │      │      │      └──── 📄 AdminNotFoundException.java 
 │      │      │      ├──── 📁 client
 │      │      │      │      ├──── 📄 ClientExistException.java
 │      │      │      │      └──── 📄 ClientNotFoundException.java 
 │      │      │      ├──── 📄 UserExistException.java
 │      │      │      └──── 📄 UserNotFoundException.java
 │      │      ├──── 📄 BaseException.java
 │      │      └──── 📄 DataAccessException.java
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
 │      │      └──── 📄 DateUtils.java 
 │      ├──── 📁 view
 │      │      ├──── 📄 AdminView.java
 │      │      ├──── 📄 ClientView.java
 │      │      ├──── 📄 InitialView.java
 │      │      ├──── 📄 LoginView.java
 │      │      └──── 📄 View.java
 │      └──── 📄 Application.java
 │──── 📁 Referenced Libraries
 │      └──── 📄 lombok.jar
 │──── 📄 .classpath
 │──── 📄 .gitignore
 │──── 📄 .project
 └──── 📄 README.md
```