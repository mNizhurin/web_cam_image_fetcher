package main;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: mnijurin
 * Date: 09/30/12
 * Time: 19:37
 */
public class Regex {
    public static List<String> regexIt(String string, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(string);
        List<String> result = new ArrayList<String>();
        if (matcher.matches()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                result.add(matcher.group(i));
            }
            return result;
        }
        return null;
    }

    public static String getOneValue(String string, String regex) throws ParseException {
        List<String> values = Regex.regexIt(string, regex);
        if (values != null && values.size() > 0) {
            return values.get(0);
        } else throw new ParseException("Can not find value from regex \"" + regex + "\" in string \"" + string + "\"", 0);
    }
}
