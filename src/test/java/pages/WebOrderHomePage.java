package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class WebOrderHomePage {

    public WebOrderHomePage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "ctl00_MainContent_orderGrid_ctl02_OrderSelector")
    public WebElement selectionBox;

    @FindBy(id = "ctl00_MainContent_btnDelete")
    public WebElement deleteButton;

    @FindBy(id = "ctl00_MainContent_orderGrid")
    public List<WebElement> listBox;

    @FindBy(xpath = "//*[@id=\"ctl00_MainContent_orderGrid\"]/tbody/tr[2]/td[13]/input")
    public WebElement editButton;

    @FindBy(xpath = "//a[@href=\"Process.aspx\"]")
    public WebElement orderModule;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr")
    public List<WebElement> numberOfRows;

    @FindBy(xpath = "//a[@href='Default.aspx']")
    public WebElement viewAllOrdersModule;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[2]")
    public WebElement firstRowName;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[3]")
    public WebElement firstRowProduct;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[4]")
    public WebElement firstRowQuantity;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[5]")
    public WebElement firstRowDate;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[6]")
    public WebElement firstRowStreet;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[7]")
    public WebElement firstRowCity;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[8]")
    public WebElement firstRowState;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[9]")
    public WebElement firstRowZip;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[10]")
    public WebElement firstRowCardName;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[11]")
    public WebElement firstRowCardNumber;

    @FindBy(xpath = "//table[@id='ctl00_MainContent_orderGrid']//tr[2]//td[12]")
    public WebElement firstRowCardExpiration;
}
