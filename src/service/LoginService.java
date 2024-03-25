package service;

import dto.user.LoginDto;
import exception.DataAccessException;
import exception.UserNotFoundException;

public interface LoginService {
    int login(LoginDto loginDto) throws DataAccessException, UserNotFoundException;
}
