package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;

public class BrowserUtils {

    /*
    Method that will accept dropdown WebElement
    and value of that dropdown and will select
    that value which is provided in parameter
    .selectByValue(dropdownElement, "1") -> void no RETURN ONLY ACTION
     */

    public static void SelectByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);

    }
     /*
    Method that will accept dropdown WebElement
    and value of that dropdown and will select
    that value which is provided in parameter
    .selectByValue(dropdownElement, "1") -> void no RETURN ONLY ACTION
     */

    public static void SelectByText(WebElement element, String text) {
        Select select = new Select(element);
        select.selectByVisibleText(text);

    }
    /*
    this method will take a screenshot of browser
     */

    public static void takeScreenshot(String name) throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "\\src\\test\\resources\\screenshots\\" + name+System.currentTimeMillis() + ".png";
        File file = new File(path);
        FileUtils.copyFile(screenshot, file);
    }

    /*
    this method will wait till element clickable
     */

    public static WebElement waitElementToBeClickable(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
        return element1;

    }
    public static WebElement waitElementToBeVisible(WebElement element) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;

    }

    /*
    scroll page
     */

    public static void scroll(int pixels){
        WebDriver driver =Driver.getDriver();
        JavascriptExecutor js =((JavascriptExecutor) driver);
        js.executeScript("window.scrollBy(0,"+pixels+")");
    }

    /*
    method will hover over in browser
     */

    public static void hoverOver(WebElement element){
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }
}