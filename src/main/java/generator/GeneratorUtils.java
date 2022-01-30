package generator;

import java.util.concurrent.ThreadLocalRandom;


public class GeneratorUtils {
    private static final String DIGITS = "0123456789";
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = UPPER.toLowerCase();
    private static final String ALL_ALPHA_NUM = UPPER + LOWER + DIGITS;

    public static String getRandomString(int length) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            result.append(ALL_ALPHA_NUM.charAt(getRandomInt(ALL_ALPHA_NUM.length())));
        }
        return result.toString();
    }

    public static String getRandomString() {
        return getRandomString(getRandomInt(24));
    }

    public static int getRandomInt() {
        return ThreadLocalRandom.current().nextInt();
    }

    public static int getRandomInt(int max) {
        return ThreadLocalRandom.current().nextInt(max);
    }

    public static int getRandomInt(int begin, int end) {
        return ThreadLocalRandom.current().nextInt(begin, end + 1);
    }
}
