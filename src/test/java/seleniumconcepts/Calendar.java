package seleniumconcepts;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Calendar extends BaseTest {

    WebDriverWait wait;
    WebElement dateField;

    @Test
    public void calendar() {
        driver.get("https://www.path2usa.com/travel-companions");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // The date field opens a flatpickr calendar. Pick May 21
        dateField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("form-field-travel_comp_date")));

        // move forward month by month until May is shown
        By month = By.cssSelector(".flatpickr-current-month .cur-month");
        openCalendar();
        while (!driver.findElement(month).getText().contains("May")) {
            String shown = driver.findElement(month).getText();
            openCalendar();
            driver.findElement(By.cssSelector(".flatpickr-next-month")).click();
            // wait for the month change to finish before clicking again: clicking mid-animation
            // leaves the day grid out of step with the month label (flatpickr keeps 2 grids while sliding)
            wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(month, shown)));
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector(".flatpickr-days .dayContainer"), 1));
        }

        //Grab common attribute//Put into list and iterate. Each day has an aria-label like "May 21, 2027";
        //match on it rather than the bare number so greyed-out days of the next month are never picked
        openCalendar();
        List<WebElement> dates = driver.findElements(By.cssSelector(".flatpickr-day"));
        for (WebElement date : dates) {
            if (date.getAttribute("aria-label").startsWith("May 21,")) {
                date.click();
                break;
            }
        }
        String selected = dateField.getAttribute("value");
        System.out.println(selected);
        Assert.assertTrue(selected.startsWith("05/21/"), "Selected date was " + selected); // field format is MM/dd/yyyy
    }

    // Scripts on this page sometimes close the calendar while it loads, so reopen it whenever it is closed.
    // flatpickr remembers the month being viewed, so reopening doesn't lose our place.
    private void openCalendar() {
        if (!driver.findElements(By.cssSelector(".flatpickr-calendar.open")).isEmpty()) {
            return;
        }
        // the page uses smooth scrolling, so scroll instantly before clicking
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', behavior:'instant'});", dateField);
        wait.until(ExpectedConditions.elementToBeClickable(dateField)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".flatpickr-calendar.open")));
        // let the opening animation finish
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".flatpickr-next-month")));
    }

}

/*How to verify if links on click are opening a new page?

String clickl = Keys.chord(Keys.CONTROL,Keys.ENTER)
driver.findElements(By.tagname("a")).sendKeys(clickl)

How to switch to a new window opened in Selenium?
		
		for(String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		*/
