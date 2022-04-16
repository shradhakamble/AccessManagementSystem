package com.accessmanagementsystem.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneUtil {
    public static boolean checkIfPhoneNumber(String value) {
        Pattern pattern = Pattern.compile("^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$");
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}
