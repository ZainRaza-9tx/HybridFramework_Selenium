package Pages;

import BaseForPO.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

    @FindBy(xpath = "//h2[text()='My Account']")
    WebElement MyAccount;
    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement Logout;
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public Boolean getMyAccount() {
        try {
            return MyAccount.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void clickLogout() {
        Logout.click();
    }


}
