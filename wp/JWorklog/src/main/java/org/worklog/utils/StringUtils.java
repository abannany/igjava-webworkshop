package org.worklog.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains String related utility functions
 */
public class StringUtils {

    /**
     * Gets the substring for a given regular expression
     *
     * @param regex the regular expression
     * @param text the text
     * 
     * @return the substring
     */
    public static String getSubstring(String regex, String text) {
        Pattern pattern = Pattern.compile(".*?(" + regex + ").*?");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find())
        {
            return matcher.group(1);
        }
        return null;
    }
}
