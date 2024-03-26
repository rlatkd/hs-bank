package service;

import dto.user.LoginDto;
import dto.user.RegisterUserDto;
import exception.BaseException;

public interface UserService {
    void register(RegisterUserDto registerUserDto) throws BaseException;
    int login(LoginDto loginDto) throws BaseException;
}
