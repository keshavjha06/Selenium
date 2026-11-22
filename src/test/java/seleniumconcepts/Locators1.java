package seleniumconcepts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class Locators1 extends BaseTest {

    @Test
    public void locators() {
        driver.get("https://www.facebook.com");
        //driver.findElement(By.className("inputtext")).sendKeys("hello");
        //driver.findElement(By.id("email")).sendKeys("Keshav");
        //driver.findElement(By.name("pass")).sendKeys("123456");
        //driver.findElement(By.linkText("Forgotten password?")).click();
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("keshav");
        driver.findElement(By.xpath("//input[@name='pass']")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@aria-label='Log in']")).click();//* means any tag


    }

}
