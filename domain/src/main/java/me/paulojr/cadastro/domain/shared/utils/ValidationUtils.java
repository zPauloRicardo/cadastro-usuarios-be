package me.paulojr.cadastro.domain.shared.utils;


import org.apache.commons.validator.routines.EmailValidator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private ValidationUtils() {
    }



    public static boolean isValidMobilePhoneNumber(String phone) {
        phone = phone.replaceAll("[^0-9]", "");
        return phone.length() == 11;
    }

    public static boolean isValidPhoneNumber(String phone) {
        phone = phone.replaceAll("[^0-9]", "");
        return phone.length() == 10;
    }

    public static boolean isValidEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
