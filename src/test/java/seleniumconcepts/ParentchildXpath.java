package seleniumconcepts;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ParentchildXpath extends BaseTest {

    @Test
    public void parentChildXpath() {

        // qaclickacademy.com/interview.php no longer exists; the practice page has the same kind of nested markup
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // parent -> child
        driver.findElement(By.xpath("//div[@id='checkbox-example']/fieldset/label[1]/input")).click();

        // sibling -> sibling: from the 1st label to the 2nd label after it
        driver.findElement(By.xpath("//div[@id='checkbox-example']/fieldset/label[1]/following-sibling::label[2]/input")).click();

        // child -> parent
        String label = driver.findElement(By.xpath("//input[@id='checkBoxOption3']/parent::label")).getText();
        System.out.println(label);
        Assert.assertEquals(label.trim(), "Option3");

    }
}
