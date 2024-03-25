package exception.user.client;

import exception.user.UserNotFoundException;

public class ClientNotFoundException extends UserNotFoundException {
    public ClientNotFoundException() {
        super("존재하지 않는 고객입니다.");
    }

}
