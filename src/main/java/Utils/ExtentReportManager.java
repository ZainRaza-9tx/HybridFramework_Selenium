package Utils;

import BaseforTest.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {
    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timeStamp + ".html";
        sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/" + repName);

        sparkReporter.config().setDocumentTitle("OpenCart Automation");
        sparkReporter.config().setReportName("OpenCart Functional Testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Application", "OpenCart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("User", System.getProperty("user.name"));

        // Optional null-safe parameter handling
        try {
            extent.setSystemInfo("OS", context.getCurrentXmlTest().getParameter("os"));
        } catch (Exception e) {
            extent.setSystemInfo("OS", "Not Provided");
        }

        try {
            extent.setSystemInfo("Browser", context.getCurrentXmlTest().getParameter("browser"));
        } catch (Exception e) {
            extent.setSystemInfo("Browser", "Not Provided");
        }

        List<String> groups = context.getCurrentXmlTest().getIncludedGroups();
        if (!groups.isEmpty()) {
            extent.setSystemInfo("Groups", groups.toString());
        }
    }

    public void onTestStart(ITestResult result) {}

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, "Test Passed: " + result.getName());
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, "Test Failed: " + result.getName());
        test.log(Status.INFO, "Cause: " + result.getThrowable());

        // Null-safe fix for grouped suite execution
        if (BaseTest.driver != null) {
            String imgPath = BaseTest.screenshot(result.getName());
            if (imgPath != null) {
                test.addScreenCaptureFromPath(imgPath);
            }
        } else {
            System.out.println("🟡 WebDriver was null during failure — screenshot skipped.");
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, "Test Skipped: " + result.getName());
        test.log(Status.INFO, "Reason: " + result.getThrowable());
    }

    public void onFinish(ITestContext context) {
        extent.flush();

        try {
            Desktop.getDesktop().browse(new File(System.getProperty("user.dir") + "/Reports/" + repName).toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
