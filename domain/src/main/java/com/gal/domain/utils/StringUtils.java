package com.gal.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


    public static boolean lengthValid(String str, int minLength, int maxLength) {
        if(str.length() < minLength || str.length() > maxLength) {
            return false;
        }
        return true;
    }

    public static boolean includeLettersOnly(String str) {
        char [] strChars = str.toCharArray();

        for (char c : strChars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean digitsOnly(String str) {
        char [] strChars = str.toCharArray();
        for (char c : strChars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean emailIsValid(String str) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(str);
        return matcher.find();
    }



}
