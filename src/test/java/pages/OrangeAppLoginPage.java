package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeAppLoginPage {

    public  OrangeAppLoginPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "txtUsername")
    public WebElement userNameBox;

    @FindBy(id = "txtPassword")
    public WebElement passwordBox;

    @FindBy(id = "btnLogin")
    public WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"menu_time_viewTimeModule\"]/b")
    public WebElement timeModule;


}
