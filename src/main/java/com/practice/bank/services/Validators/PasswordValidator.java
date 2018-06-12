package com.practice.bank.services.Validators;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator{

    private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z]).{6,20})";

    private static Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
    private static Matcher matcher;

    public static boolean validate(final String password)
    {
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
