package BaseforTest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseTest {

    public static Properties properties;
    public static Logger logger = LogManager.getLogger(BaseTest.class);
    public static WebDriver driver;
@Parameters("browser")
    @BeforeClass(groups = {"Master","DDT","Sanity","Regression"})
    public void BaseClass(@Optional("chrome") String br) throws IOException {
        FileReader file = new FileReader("/Users/wahyatmy/IdeaProjects/HybridFramework_Selenium/src/test/resources/config.properties");
        properties=new Properties();
        properties.load(file);

        switch (br.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriver();
                logger.info("Starting Chrome Browser");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                logger.info("Starting Firefox Browser");
                break;
            case "edge":
                driver = new EdgeDriver();
                logger.info("Starting Edge Browser");
                break;
            case "safari":
                driver = new SafariDriver();
                logger.info("Starting Safari Browser");
                break;
            default:
                throw new IllegalArgumentException("Invalid browser: " + br);
        }

        driver.get(properties.getProperty("URL"));
        driver.manage().window().maximize();
    }

    @AfterClass(groups = {"Master","DDT","Sanity","Regression"})
    public void teardown(){
        if(driver!=null)
        {
            driver.close();
            driver.quit();
        }
    }

    public static String screenshot(String tname){
       try {

           TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
           File Filetype = takesScreenshot.getScreenshotAs(OutputType.FILE);

           String timestamp = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());
           String targetpath = System.getProperty("user.dir") + "/Screenshot/" + tname + "_" + timestamp + ".png";

           File targetfile = new File(targetpath);
           targetfile.getParentFile().mkdirs();
           Filetype.renameTo(targetfile);

           return targetpath;
       } catch (Exception e) {
           e.printStackTrace();
           return null;
       }
    }
}
