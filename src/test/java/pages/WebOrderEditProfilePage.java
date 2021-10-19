package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class WebOrderEditProfilePage {
    public WebOrderEditProfilePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    public WebElement customerNameBox;

    @FindBy(id = "ctl00_MainContent_fmwOrder_UpdateButton")
    public WebElement updateButton;

}
