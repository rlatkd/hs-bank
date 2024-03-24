import dto.account.request.RegisterAccountDto;
import dto.user.RegisterUserDto;
import exception.*;
import service.AccountService;
import service.ClientService;
import service.TransactionService;
import view.InitialView;
import entity.Admin;
import entity.Client;

import javax.security.auth.login.AccountNotFoundException;

public class Application {
    public static void main(String[] args) throws DataLoadingException, DataSavingException, ExistingUserException, AccountAlreadyExistsException, ClientNotFoundException, EmptyAccountListException, AccountNotFoundException, BalanceInsufficientException, EmptyTransactionListException {
        //new InitialView().display();
        ClientService clientService = ClientService.getInstance();
        RegisterUserDto registerUserDto = RegisterUserDto.builder().build();

        clientService.registerClient(registerUserDto);

        AccountService accountService = AccountService.getInstance();

        RegisterAccountDto registerAccountDto1 = RegisterAccountDto.builder()
                .ownerId(1)
                .number("111-111")
                .bankName("신한")
                .build();

        RegisterAccountDto registerAccountDto2 = RegisterAccountDto.builder()
                .ownerId(1)
                .number("222-222")
                .bankName("국민")
                .build();

        accountService.registerAccount(registerAccountDto1);
        accountService.registerAccount(registerAccountDto2);
        System.out.println("accounlist");
        System.out.println(accountService.getAccountList(1));


        System.out.println("deposit");
        accountService.deposit(1, 10000);
        System.out.println(accountService.getAccountList(1));

        System.out.println("transfer");
        accountService.transfer(1,"222-222",5000);
        System.out.println(accountService.getAccountList(1));

        TransactionService transactionService = TransactionService.getInstance();
        System.out.println(transactionService.getTransactionList(1));
        System.out.println(transactionService.getTransactionList(2));

    }
}
