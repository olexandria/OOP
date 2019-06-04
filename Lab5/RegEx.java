package com.dypko;

import java.util.regex.Pattern;

public class RegEx {

    public static boolean isValidName(String name) {
        Pattern patternName = Pattern.compile("\\D+");
        return patternName.matcher(name).find();
    }
    public static boolean isValidDateOfBirth(String dateOfBirth) {
        Pattern patternDateOfBirth = Pattern.compile("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([01]?\\d{0,3}?|20[01]\\d)$");
        return patternDateOfBirth.matcher(dateOfBirth).find();
    }
    public static boolean isValidDateOfEditing(String dateOfEditing) {
        Pattern patternDateOfEditing = Pattern.compile("^([0-2][0-9]||3[0-1])/(0[0-9]||1[0-2])/([01]?\\d{0,3}?|20[01]\\d)$");
        return patternDateOfEditing.matcher(dateOfEditing).find();
    }
}