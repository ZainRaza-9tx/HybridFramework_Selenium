package Tests;

import BaseforTest.BaseTest;
import Pages.AccountRegistrationPage;
import Pages.Homepage;
import Utils.DataProviders;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;



public class RegistrationDDT extends BaseTest {

    private static final Logger logger = BaseTest.logger;

    @Test(groups = {"DDT"}, dataProvider = "RegistrationData", dataProviderClass = DataProviders.class)
    public void verify_account_registration(String fname, String lname, String email, String phoneNo, String password) {
        logger.info("TC_002_AccountRegistrationTest Starting ");

        try {

            Homepage homepage = new Homepage(driver);
            homepage.clickMyAccount();
            logger.info("Clicked on MyAccount  link");

            homepage.ClickRegister();
            logger.info("Clicked on Register link");

            AccountRegistrationPage registrationPage = new AccountRegistrationPage(driver);
            logger.info("providing cust details");

            registrationPage.setFirstName(fname);
            registrationPage.setLastName(lname);
            registrationPage.setEmail(email);
            registrationPage.setPhoneNumber(phoneNo);

            registrationPage.setPassword(password);
            registrationPage.setConfirmPassword(password);

            registrationPage.setCheckPolicy();
            registrationPage.setContinuebtn();

            logger.info("Validating expected message");
            String confmsg = registrationPage.Confirmationmsg();

            if (confmsg.equals("Your Account Has Been Created!")) {
                Assert.assertTrue(true);
            } else {
                logger.error("Test Failed");
                logger.debug("Debug Logs");
                Assert.assertTrue(false);
            }
            //  Assert.assertEquals(confmsg, "Your Account Has Been Created  !");


        } catch (Exception e) {
            logger.error("Test failed due to exception: " + e.getMessage(), e);
            Assert.fail("Exception occurred during test execution: " + e.getMessage());
        }
        logger.info("TC_002_AccountRegistrationTest Finished ");

    }

}

