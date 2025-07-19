package Tests;

import BaseforTest.BaseTest;
import Pages.Homepage;
import Pages.LoginPage;
import Pages.MyAccountPage;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC002LoginPageTest extends BaseTest {

    private  final Logger logger = BaseTest.logger;

    @Test(groups = {"Sanity","Master"})
     void Loginpagetest() {

        logger.info("Starting TC002LoginPageTest");
        System.out.println("🔍 Inside Loginpagetest()");
        //only use try catch when using xmll file
        try {

            //homepage
            Homepage homepage = new Homepage(driver);
            homepage.clickMyAccount();
            homepage.ClickLogin();

            logger.info("opening login page");
            //loginPage
            LoginPage lg = new LoginPage(driver);

            logger.info("adding valid login credentials");
            lg.setEmailField(properties.getProperty("email"));
            lg.setPasswordField(properties.getProperty("password"));
            lg.clickLoginbtn();

            MyAccountPage mc = new MyAccountPage(driver);
            boolean targetpage = mc.getMyAccount();

            Assert.assertTrue(targetpage);

            logger.info("Finished TC002LoginPageTest");
        } catch (Exception e) {
                 }
    }

}
