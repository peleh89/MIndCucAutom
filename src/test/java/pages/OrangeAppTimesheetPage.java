package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeAppTimesheetPage {

    public OrangeAppTimesheetPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);

    }
    @FindBy(id = "menu_time_Timesheets")
    public WebElement timeSheetsDropdown;

    @FindBy(id = "menu_time_viewMyTimesheet")
    public WebElement myTimeSheet;



}
