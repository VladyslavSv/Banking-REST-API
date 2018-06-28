package unit.tests.services.Validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsernameValidator {

    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private static Pattern pattern = Pattern.compile(USERNAME_PATTERN);
    private static Matcher matcher;

    public static boolean validate(final String username) {
        matcher = pattern.matcher(username);
        return matcher.matches();

    }
}