package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class OrangeAppEditMyTimeSheetPage {
    public OrangeAppEditMyTimeSheetPage(){
        WebDriver driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "initialRows_0_projectName")
    public WebElement projectNameBox;

    @FindBy(id = "btnReset")
    public WebElement resetButton;

    @FindBy(id = "initialRows_0_projectName")
    public WebElement updateProjectName;

    @FindBy(id = "btnBack")
    public WebElement cancelButton;

    @FindBy(id = "initialRows_0_projectName")
    public WebElement projectNameDropdown;

    @FindBy(id = "initialRows_0_projectActivityName")
    public WebElement activityNameDropdown;

    @FindBy(name = "initialRows[0][0]")
    public WebElement timeBox1;

    @FindBy(name = "initialRows[0][1]")
    public WebElement timeBox2;

    @FindBy(name = "initialRows[0][2]")
    public WebElement timeBox3;

    @FindBy(name = "initialRows[0][3]")
    public WebElement timeBox4;

    @FindBy(id = "submitSave")
    public WebElement saveButton;

    @FindBy(id = "initialRows_0_toDelete")
    public WebElement deleteCheckBox;

    @FindBy(id = "submitRemoveRows")
    public WebElement removeRowsButton;

}
