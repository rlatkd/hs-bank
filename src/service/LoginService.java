package service;

import dto.user.LoginDto;
import exception.DataAccessException;
import exception.IncorrectCredentialsException;

public interface LoginService {
    int login(LoginDto loginDto) throws DataAccessException, IncorrectCredentialsException ;
}
