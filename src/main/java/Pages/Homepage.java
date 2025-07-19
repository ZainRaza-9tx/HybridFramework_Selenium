package Pages;

import BaseForPO.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends BasePage {

    private final By lnkMyAccount = By.xpath("//a[@title='My Account']");
    private final By lnkRegistration = By.xpath("//a[normalize-space()='Register']");
    private final By lnkLogin = By.xpath("//a[normalize-space()='Login']");


    public Homepage(WebDriver driver) {

        super(driver); // This sets the driver in BasePage
    }

    public void clickMyAccount() {
        driver.findElement(lnkMyAccount).click();
    }

    public void ClickRegister() {
        driver.findElement(lnkRegistration).click();
    }

    public void ClickLogin() {
        driver.findElement(lnkLogin).click();
    }

//    public void clickWait(By locator) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
//       WebElement item= wait.until(ExpectedConditions.elementToBeClickable(locator));
//    item.click();
//    }
}
