package seleniumconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class CSSselectors extends BaseTest {

    @Test
    public void selectors() {
        //xpath=//tagname[@attribute='value']
        //css= tagname[attribute='value'] .tagname is optional in css
        //* means any tag
        driver.get("https://login.salesforce.com/?login=1");
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys("keshav");
        driver.findElement(By.xpath("//input[@id='Login']")).click();
        System.out.println(new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[id='error']"))).getText());
        //driver.findElement(By.cssSelector("#Login")).click();
    }

}
