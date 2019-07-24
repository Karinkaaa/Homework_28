package main.java.web.controller;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractEmailControllerTest {

    @Test
    public void analyze() {

        ExtractEmailController controller = new ExtractEmailController();
        String res = "";

        String numberPhone = "+380669512347 ff test@dataart.com gfdfhg fdg f +380997845123";
        String expected = "+380669512347 +380997845123 test@dataart.com ";

        Pattern pattern = Pattern.compile(controller.numberPhonePattern);
        Matcher matcher = pattern.matcher(numberPhone);
        res += whileMatcherFinds(numberPhone, matcher);

        pattern = Pattern.compile(controller.emailPattern);
        matcher = pattern.matcher(numberPhone);
        res += whileMatcherFinds(numberPhone, matcher);

        Assert.assertEquals(expected, res);
    }

    private String whileMatcherFinds(String input, Matcher matcher) {

        String result = "";
        while (matcher.find())
            result += input.substring(matcher.start(), matcher.end()) + " ";

        return result;
    }
}