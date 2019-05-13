package baro.util;

/**
 * Created with IntelliJ IDEA
 * User: BaroDevelopment
 * Date: 13.05.2019 23:41
 */
public class Regional {

    private final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    private final String[] tens = {
            "",        // 0
            "",        // 1
            "twenty",  // 2
            "thirty",  // 3
            "forty",   // 4
            "fifty",   // 5
            "sixty",   // 6
            "seventy", // 7
            "eighty",  // 8
            "ninety"   // 9
    };

    public String toRegional(String message) {
        try {
            String result = "**";
            String[] words = message.split(" ");
            for (String word : words) {
                if (word.startsWith("<") && word.endsWith(">")) {
                    result += word + "";
                    continue;
                }
                for (int i = 0; i < word.length(); i++) {
                    if (Character.isLetter(word.charAt(i))) {
                        result += (":regional_indicator_" + word.charAt(i) + ":").toLowerCase();
                    } else if (Character.isDigit(word.charAt(i))) {
                        result += (":" + convert(Character.getNumericValue(word.charAt(i))) + ":").toLowerCase();
                    } else if (String.valueOf(word.charAt(i)).equals("?"))
                        result += ":question:";
                    else if (String.valueOf(word.charAt(i)).equals("!"))
                        result += ":exclamation:";
                    else
                        result += word.charAt(i);
                }
                result += "    ";
            }
            return result + "**";
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String convert(final int n) {
        if (n < 0) {
            return "minus " + convert(-n);
        }

        if (n < 20) {
            return units[n];
        }

        if (n < 100) {
            return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
        }

        if (n < 1000) {
            return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
        }

        if (n < 1000000) {
            return convert(n / 1000) + " thousand" + ((n % 1000 != 0) ? " " : "") + convert(n % 1000);
        }

        if (n < 1000000000) {
            return convert(n / 1000000) + " million" + ((n % 1000000 != 0) ? " " : "") + convert(n % 1000000);
        }

        return convert(n / 1000000000) + " billion" + ((n % 1000000000 != 0) ? " " : "") + convert(n % 1000000000);
    }

}
