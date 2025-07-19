package Tests;



/*Validating
Data is valid - login success -test pass - logout
                        login failed- test failed
Data is invalid - login unsucessful - testfailed - logout
                                   login failed- test pass
 */

import BaseforTest.BaseTest;
import Pages.Homepage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import Utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginDDT extends BaseTest {

    @Test(dataProvider = "loginData", dataProviderClass = DataProviders.class, groups = "DDT")
// data provider from a different class
    public void loginTest_DDT(String email, String pass, String expCond) {
        try {

            logger.info("TC003LoginDDT  Started");
            System.out.println("Running test for: " + email + " | " + pass + " | Expected: " + expCond);

            //homepage

            Homepage homepage = new Homepage(driver);
            homepage.clickMyAccount();
            homepage.ClickLogin();

            logger.info("opening login page");
            //loginPage
            LoginPage lg = new LoginPage(driver);


            logger.info("adding valid login credentials");
            lg.setEmailField(email);
            lg.setPasswordField(pass);
            lg.clickLoginbtn();
            //My account
            MyAccountPage mc = new MyAccountPage(driver);
            boolean targetpage = mc.getMyAccount();
            System.out.println("Login result: " + (targetpage ? "Success" : "Failure"));

            if (expCond.equalsIgnoreCase("Valid")) {
                if (targetpage == true) {
                    mc.clickLogout();
                    Assert.assertTrue(true);
                } else {
                    Assert.assertTrue(false);
                }

            }
            if (expCond.equalsIgnoreCase("Invalid")) {
                if (targetpage == true) {
                    mc.clickLogout();
                    Assert.assertTrue(false);
                } else {
                    Assert.assertTrue(true);
                }
            }
        } catch (Exception e) {
            Assert.fail();
        }
        logger.info("Finished TC003LoginDDT");
    }
}
