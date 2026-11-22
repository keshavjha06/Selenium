package seleniumconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionsDemo extends BaseTest {

    @Test
    public void actionDemo() {
        driver.get("https://www.amazon.com/");
        // Amazon sometimes shows a "Continue shopping" interstitial to automated browsers
        if (!driver.findElements(By.xpath("//button[contains(.,'Continue shopping')]")).isEmpty()) {
            driver.findElement(By.xpath("//button[contains(.,'Continue shopping')]")).click();
        }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        if (driver.findElements(By.id("nav-link-accountList")).isEmpty()) {
            driver.navigate().refresh();
        }
        WebElement move = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[id='nav-link-accountList']")));
        Actions a = new Actions(driver);
        a.moveToElement(driver.findElement(By.id("twotabsearchtextbox"))).click().keyDown(Keys.SHIFT).sendKeys("hello").keyUp(Keys.SHIFT).doubleClick().perform();
        //moves to specific element
        a.moveToElement(move).contextClick().build().perform();
        //Drag and drop
    }

}
