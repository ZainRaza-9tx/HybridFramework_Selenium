package Utils;

import org.apache.commons.lang3.RandomStringUtils;

public class GenRandomData {

    public static String randomname() {
        return RandomStringUtils.randomAlphabetic(5);
    }
    public static String randomemail(){
        return randomname()+"@Test.com";}

    public static String randomPhoneNumber() {
        return RandomStringUtils.randomNumeric(10);
    }

    public static String randomPassword() {
        return RandomStringUtils.randomAlphanumeric(6);
    }
}
