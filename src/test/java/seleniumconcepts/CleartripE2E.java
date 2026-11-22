package seleniumconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CleartripE2E extends BaseTest {

    @Test
    public void clearTripE2E() {
        driver.get("https://www.cleartrip.com/");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Cleartrip's React UI has generated class names, so use data-testid attributes and visible text

        // close the login popup if it appears
        if (!driver.findElements(By.cssSelector("[data-testid='closeIcon']")).isEmpty()) {
            driver.findElement(By.cssSelector("[data-testid='closeIcon']")).click();
        }

        // pick the first available departure date
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-testid='dateSelectOnward']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector(".DayPicker-Day:not(.DayPicker-Day--disabled):not(.DayPicker-Day--outside)"))).click();

        // travellers: 2 adults, 1 child (the "+" button is the 2nd button next to each label)
        driver.findElement(By.cssSelector("[data-testid='paxClassContainerId']")).click();
        driver.findElement(By.xpath("//p[text()='Adults']/../following-sibling::div/button[2]")).click();
        driver.findElement(By.xpath("//p[text()='Children']/../following-sibling::div/button[2]")).click();
        String travellers = driver.findElement(By.cssSelector("[data-testid='paxClassContainerId']")).getText();
        System.out.println(travellers);
        Assert.assertTrue(travellers.contains("2 Adults, 1 Child"), "Travellers were: " + travellers);
        driver.findElement(By.cssSelector("[data-testid='paxClassContainerId']")).click(); // close the panel

        // search without From/To -> validation error.
        // Cleartrip A/B tests this button: some sessions get "Explore", which opens a destination map
        // instead of a flight search, so the validation check only applies to the "Search Flights" variant.
        By searchButton = By.xpath("//h4[text()='Search Flights']");
        if (driver.findElements(searchButton).isEmpty()) {
            System.out.println("Cleartrip served the \"Explore\" variant; skipping the search validation check");
            return;
        }
        driver.findElement(searchButton).click();
        String error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'Enter departure and arrival')]"))).getText();
        System.out.println(error);
    }

}
