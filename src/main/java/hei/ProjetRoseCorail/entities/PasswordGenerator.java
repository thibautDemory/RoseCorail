package hei.ProjetRoseCorail.entities;

import java.security.SecureRandom;

public class PasswordGenerator {

        private static SecureRandom random = new SecureRandom();

        /** different dictionaries used */
        private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
        private static final String NUMERIC = "0123456789";

        /**
         * Method will generate random string based on the parameters
         *
         * @param len
         *            the length of the random string
         * @return the random AlphaNumeric password
         */
        public static String generatePassword(int len) {
            String result = "";
            String dic = ALPHA + NUMERIC;
            for (int i = 0; i < len; i++) {
                int index = random.nextInt(dic.length());
                result += dic.charAt(index);
            }
            return result;
        }
}
