package seleniumconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class TableExercises extends BaseTest {

    @Test
    public void tableExercise() {

        int sum = 0;
        driver.get("http://www.cricbuzz.com/live-cricket-scorecard/18970/pak-vs-sl-2nd-t20i-pakistan-v-sri-lanka-in-uae-2017");

        // scope to the 1st innings scorecard: nearest ancestor of the first "Extras" row that holds batting rows
        WebElement table = driver.findElement(By.xpath("(//div[text()='Extras'])[1]/ancestor::div[.//div[contains(@class,'scorecard-bat-grid')]][1]"));
        // each batter is a row; the 1st row is the header (Batter, R, B, 4s, 6s, SR)
        List<WebElement> rows = table.findElements(By.cssSelector("div.scorecard-bat-grid"));

        for (int i = 1; i < rows.size(); i++) {
            // 2nd column of each row is runs
            String value = rows.get(i).findElement(By.xpath("./div[2]")).getAttribute("textContent").trim();
            int valueInteger = Integer.parseInt(value);
            sum = sum + valueInteger;
        }
        // System.out.println(sum);

        String Extras = table.findElement(By.xpath(".//div[text()='Extras']/following-sibling::div/span[1]")).getAttribute("textContent").trim();
        int extrasValue = Integer.parseInt(Extras);
        int TotalSumValue = sum + extrasValue;
        System.out.println(TotalSumValue);

        // Total looks like "124-9" (runs-wickets)
        String ActualTotal = table.findElement(By.xpath(".//div[text()='Total']/following-sibling::div/span[1]")).getAttribute("textContent").trim();
        int ActualTotalVAlue = Integer.parseInt(ActualTotal.split("-")[0]);
        if (ActualTotalVAlue == TotalSumValue) {
            System.out.println("Count Matches");
        } else {
            System.out.println("count fails");
        }
        Assert.assertEquals(TotalSumValue, ActualTotalVAlue);
    }

}
