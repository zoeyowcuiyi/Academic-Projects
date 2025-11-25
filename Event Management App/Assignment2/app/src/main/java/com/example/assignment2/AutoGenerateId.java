package com.example.assignment2;

import java.util.Random;

public class AutoGenerateId {
    public static String EVENT_CHAR = "E";
    public static String CATEGORY_CHAR = "C";

    private final Random random = new Random();

    public String myAutoGenerateId(String firstChar, int numOfChar, int numOfInt) {

        String newId = firstChar;
        String randomStr;

        for (int i = 0; i < numOfChar; i++) {
            char randomChar = (char) ('A' + random.nextInt(26));
            randomStr = String.valueOf(randomChar);
            newId += randomStr;
        }

        newId += "-";

        for (int j = 0; j < numOfInt; j++) {
            int randomInt = random.nextInt(10);
            randomStr = String.valueOf(randomInt);
            newId += randomStr;
        }

        return newId;
    }
}
