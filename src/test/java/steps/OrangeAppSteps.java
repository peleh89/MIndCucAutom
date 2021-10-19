package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.OrangeAppEditMyTimeSheetPage;
import pages.OrangeAppLoginPage;
import pages.OrangeAppMyTimeSheetPage;
import pages.OrangeAppTimesheetPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;


public class OrangeAppSteps {
    WebDriver driver = Driver.getDriver();
    OrangeAppLoginPage orangeAppLoginPage = new OrangeAppLoginPage();
    OrangeAppTimesheetPage orangeAppTimesheetPage = new OrangeAppTimesheetPage();
    OrangeAppMyTimeSheetPage orangeAppMyTimeSheetPage = new OrangeAppMyTimeSheetPage();
    String actualProjectName;
    OrangeAppEditMyTimeSheetPage orangeAppEditMyTimeSheetPage = new OrangeAppEditMyTimeSheetPage();

    @Given("user navigate to orangeHRM application")
    public void user_navigate_to_orangeHRM_application() {
        driver.get(ConfigReader.getProperty("OrangeHRMAppURL"));

    }

    @When("user provides username {string} and password {string} and clicks on login button")
    public void userProvidesUsernameAndPasswordAndClicksOnLoginButton(String username, String password) {
        orangeAppLoginPage.userNameBox.sendKeys(username);
        orangeAppLoginPage.passwordBox.sendKeys(password);
        orangeAppLoginPage.loginButton.click();
    }

    @When("user clicks on Time module")
    public void user_clicks_on_Time_module() {
        orangeAppLoginPage.timeModule.click();

    }

    @When("user select employeeTimesheet from TimesSheets dropdown and click")
    public void user_select_employeeTimesheet_from_TimesSheets_dropdown_and_click() {
        BrowserUtils.hoverOver(orangeAppTimesheetPage.timeSheetsDropdown);
        orangeAppTimesheetPage.myTimeSheet.click();

    }
    @When("user clicks on edit button and add a row for testing")
    public void userClicksOnEditButtonAndAddARowForTesting() throws InterruptedException {
        orangeAppMyTimeSheetPage.editButton.click();
        orangeAppEditMyTimeSheetPage.deleteCheckBox.click();
        orangeAppEditMyTimeSheetPage.removeRowsButton.click();
        orangeAppEditMyTimeSheetPage.cancelButton.click();
        orangeAppMyTimeSheetPage.editButton.click();
        orangeAppEditMyTimeSheetPage.projectNameDropdown.sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        orangeAppEditMyTimeSheetPage.projectNameDropdown.sendKeys("Global Corp and Co - Global Software phase - 1");
        orangeAppEditMyTimeSheetPage.projectNameDropdown.sendKeys(Keys.ENTER);
        BrowserUtils.SelectByValue(orangeAppEditMyTimeSheetPage.activityNameDropdown,"5");
        orangeAppEditMyTimeSheetPage.timeBox1.sendKeys("6:00");
        orangeAppEditMyTimeSheetPage.timeBox2.sendKeys("5:00");
        orangeAppEditMyTimeSheetPage.timeBox3.sendKeys("4:30");
        orangeAppEditMyTimeSheetPage.timeBox4.sendKeys("8:00");
        orangeAppEditMyTimeSheetPage.saveButton.click();
        actualProjectName = orangeAppMyTimeSheetPage.projectName.getText();

    }

    @When("user clicks on edit button and update some of project name and click reset button")
    public void user_clicks_on_edit_button_and_update_some_of_project_name_and_click_reset_button() {
        orangeAppMyTimeSheetPage.editButton.click();
        orangeAppEditMyTimeSheetPage.projectNameBox.sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        orangeAppEditMyTimeSheetPage.projectNameBox.sendKeys("Project1");
        orangeAppEditMyTimeSheetPage.resetButton.click();

    }

    @Then("user validates that project name didn't update")
    public void user_validates_that_project_name_didn_t_update() {
        String updateProjectName = orangeAppEditMyTimeSheetPage.updateProjectName.getAttribute("value");
        Assert.assertEquals(actualProjectName,updateProjectName);

    }

    @When("user clicks on edit button and click on cancel button")
    public void user_clicks_on_edit_button_and_click_on_cancel_button() {
        orangeAppMyTimeSheetPage.editButton.click();
        orangeAppEditMyTimeSheetPage.cancelButton.click();

    }

    @Then("user validates that is on employeeTimesheet page")
    public void user_validates_that_is_on_employeeTimesheet_page() {
        String expectedSubTitleName = "Timesheet for Week";
        String actualSubTitleName = orangeAppMyTimeSheetPage.actualSubTitle.getText();
        Assert.assertEquals(actualSubTitleName,expectedSubTitleName);


    }


}
