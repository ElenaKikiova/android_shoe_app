package com.example.shoes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface Validation {

    public String nameValidator = "^[A-Za-z0-9-,. \\/]{2,20}$"; // letter, numbers, spaces, -/,. 2-20 symbols
    public String urlValidator = "^((https?|ftp|smtp):\\/\\/)?(www.)?[a-z0-9]+\\.[a-z]+(\\/[a-zA-Z0-9#]+\\/?)*$";
    public String priceValidator = "^\\d+(,?\\.?\\d{1,2})?"; // 1+ digit, followed by a comma or decimal point and 0-2 digits
    public String numberValidator = "^[0-9]*$"; // at least 0, and an integer

    public static boolean Validate(String text, String expression){
        final String regex = expression;
        final String string = text;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(string);

        if(matcher.find()){
            return true;
        }
        return false;
    }
}
