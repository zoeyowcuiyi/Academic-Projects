package com.example.assignment2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlphanumericChecker {
    public boolean isAlphanumeric(String str) {
        String regex = "^(?=.*[a-zA-Z])[A-Za-z0-9 ]+$";

        Pattern p = Pattern.compile(regex);

        Matcher m = p.matcher(str);

        return m.matches();
    }
}
