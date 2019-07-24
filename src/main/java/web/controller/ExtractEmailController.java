package main.java.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("email")
public class ExtractEmailController {

    private static Logger logger = LoggerFactory.getLogger(ExtractEmailController.class.getName());

    public String numberPhonePattern = "\\+?[1-9]{1,2}?[0-9]{3}?[0-9]{8}";
    public String emailPattern = "\\w+\\.?\\w+@\\w+\\.\\w{2,3}";

    public ExtractEmailController() {
        logger.info("Extract email controller created...");
    }

    @RequestMapping(value = "analyze", method = RequestMethod.GET)
    @ResponseBody
    public String analyze(String str) {

        logger.info("Analyze method invoked...");

        String result = "";

        Pattern pattern = Pattern.compile(numberPhonePattern);
        Matcher matcher = pattern.matcher(str);
        result += whileMatcherFind(str, matcher);

        pattern = Pattern.compile(emailPattern);
        matcher = pattern.matcher(str);
        result += whileMatcherFind(str, matcher);

        return result;
    }

    private String whileMatcherFind(String input, Matcher matcher) {

        String result = "";

        while (matcher.find()) {

            int start = matcher.start();
            int end = matcher.end();

            result += "{\n\t" + input.substring(start, end) + "\t{\n\n\t\tstart:\t" + start +
                    "\n\t\tend:\t" + end + "\n}\n";
        }
        return result;
    }
}
