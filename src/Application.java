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
    public static void main(String[] args) {
        new InitialView().display();
    }
}
