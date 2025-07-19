package Pages;

import BaseForPO.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement FirstName;
    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement LastName;
    @FindBy(xpath = "//input[@id='input-email']")
    WebElement Email;
    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement PhoneNumber;
    @FindBy(xpath = "//input[@id='input-password']")
    WebElement Password;
    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement ConfirmPassword;
    @FindBy(xpath = "//input[@name='agree']")
    WebElement checkPolicy;
    @FindBy(xpath = "//input[@value='Continue']")
    WebElement Continuebtn;
    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement msgConfirmation;
    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void setFirstName(String firstName) {
        FirstName.sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        LastName.sendKeys(lastName);
    }

    public void setEmail(String email) {
        Email.sendKeys(email);
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber.sendKeys(phoneNumber);
    }

    public void setPassword(String password) {
        Password.sendKeys(password);
    }

    public void setConfirmPassword(String password) {
        ConfirmPassword.sendKeys(password);
    }

    public void setCheckPolicy() {
        checkPolicy.click();
    }

    public void setContinuebtn() {
        Continuebtn.click();
    }

    public String Confirmationmsg() {
        try {
            return (msgConfirmation.getText());
        } catch (Exception e) {
            return (e.getMessage());
        }
    }
}
