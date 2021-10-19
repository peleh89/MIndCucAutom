package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MyStoreUpdatePage {

    public MyStoreUpdatePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }
    @FindBy(id = "address1")
    public WebElement addressBox;

    @FindBy(id = "submitAddress")
    public WebElement saveButton;

}
