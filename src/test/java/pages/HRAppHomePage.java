package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class HRAppHomePage {

    public HRAppHomePage() {
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "department")
    public WebElement departmentDropdown;

    @FindBy(xpath = "//th")
    public List<WebElement> columns;

    @FindBy(xpath = "//td")
    public List<WebElement> tableData;

    @FindBy(xpath = "/html/body/app-root/header/nav/ul[1]/li[2]/a")
    public WebElement createNewEmployeeButton;

    @FindBy(xpath = "//div[@class='alert alert-warning']")
    public WebElement deleteMessage;

    @FindBy(xpath = "//input[@placeholder='Employee ID..']")
    public WebElement searchBox;

    @FindBy(id = "searchButton")
    public WebElement searchButton;

    @FindBy(xpath = "//td[2]")
    public WebElement firstName;

    @FindBy(xpath = "//td[3]")
    public WebElement lastName;

    @FindBy(xpath = "//td[4]")
    public WebElement departmentName;
}