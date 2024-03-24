package service;

import dto.user.LoginDto;
import exception.DataLoadingException;
import exception.IncorrectCredentialsException;

public interface LoginService {
    int login(LoginDto loginDto) throws DataLoadingException, IncorrectCredentialsException ;
}
