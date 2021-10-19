package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeAppMyTimeSheetPage {

    public OrangeAppMyTimeSheetPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "columnName")
    public WebElement projectName;

    @FindBy(id = "btnEdit")
    public WebElement editButton;

    @FindBy(xpath = "//h3[contains(text(),'Timesheet for Week')]")
    public WebElement actualSubTitle;
}
