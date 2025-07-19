package Pages;

import BaseForPO.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement EmailField;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement PasswordField;
    @FindBy(xpath = "//input[@value='Login']")

    WebElement Loginbtn;
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void setEmailField(String email) {
        EmailField.sendKeys(email);
    }

    public void setPasswordField(String password) {
        PasswordField.sendKeys(password);
    }

    public void clickLoginbtn() {
        Loginbtn.click();
    }


}
