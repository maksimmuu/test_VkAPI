package org.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExUtils {
    public static String useRegExPattern(String stringToBeChecked, String regEx) {
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(stringToBeChecked);
        String resultString = null;

        while (matcher.find()) {
            resultString = matcher.group();
        }
        return resultString;
    }
}
