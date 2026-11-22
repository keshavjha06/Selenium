package seleniumconcepts;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class XpathDemo extends BaseTest {

    @Test
    public void xpathDemo() {
        driver.get("https://www.facebook.com");
        //driver.findElement(By.xpath("//*[@type='text']")).sendKeys("keshav");
        //driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("keshav");
        // driver.findElement(By.xpath("//*[@value='1']")).click();
        //driver.findElement(By.cssSelector("input[type='text']")).sendKeys("myowncss");
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys("myowncss");
        driver.findElement(By.cssSelector("[aria-label='Log in']")).click();


    }

}
