package seleniumconcepts;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    public WebDriver driver;
    public ChromeOptions options;

    @BeforeClass
    public void configureSelenium() {
        driver = new ChromeDriver();
        options = new ChromeOptions();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    // When a test fails, save a screenshot and the page HTML: target/screenshots/<Class>.<method>.png / .html
    @AfterMethod
    public void screenshotOnFailure(ITestResult result) {
        if (result.getStatus() != ITestResult.FAILURE || driver == null) {
            return;
        }
        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String name = result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName() + ".png";
            FileUtils.copyFile(src, new File(System.getProperty("user.dir"), "target/screenshots/" + name));
            FileUtils.writeStringToFile(new File(System.getProperty("user.dir"), "target/screenshots/" + name.replace(".png", ".html")), driver.getPageSource(), "UTF-8");
        } catch (IOException | RuntimeException e) {
            System.out.println("Could not save failure screenshot: " + e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
