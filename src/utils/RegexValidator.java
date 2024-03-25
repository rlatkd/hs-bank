package utils;

import exception.BaseException;
import exception.regex.RegexNotValidException;

public class RegexValidator {
    private static final String PHONE_NUMBER_REGEX = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$";
    private static final String BIRTH_DATE_REGEX = "^(19|20)\\d{2}(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$";
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    private static final String PASSWORD_REGEX = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{6,}$";

    private static final String ACCOUNT_NUMBER_REGEX = "^[0-9]+$";
    public static void validateRegex(String input, String regex) throws BaseException {
        if(!input.matches(regex)) throw new RegexNotValidException();
    }
}
