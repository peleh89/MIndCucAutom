package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class MyStoreMyAccountPage {

    public MyStoreMyAccountPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[contains(text(),'My addresses')]\n")
    public WebElement myAddressButton;

    @FindBy(xpath = "//*[@id=\"center_column\"]/div[1]/div/div/ul/li[9]/a[1]/span")
    public WebElement updateButton;

    //@FindBy(xpath = "//a[@class='btn btn-default button button-small'][2]")
   // public WebElement updateButton;

    @FindBy(xpath = "//span[@class='address_address1']")
    public WebElement actualAddress;
}
