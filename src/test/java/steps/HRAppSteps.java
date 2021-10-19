package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppCreateEmployeePage;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;
import java.util.Map;

public class HRAppSteps {

    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppHomePage hrAppHomePage = new HRAppHomePage();
    HRAppCreateEmployeePage hrAppCreateEmployeePage = new HRAppCreateEmployeePage();
    Map<String,Object> data;
     int numberOfEmployees;

    @Given("user navigates to HR Application")
    public void user_navigates_to_HR_Application() {
        driver.get(ConfigReader.getProperty("HRAppURL"));

    }

    @When("user logs in with username {string} password {string}")
    public void user_logs_in_with_username_password(String username, String password)  {
        hrAppLoginPage.usernameBox.sendKeys(username);
        hrAppLoginPage.passwordBox.sendKeys(password);
        hrAppLoginPage.loginButton.click();

    }

    @When("user clicks on Create New Employee button")
    public void user_clicks_on_Create_New_Employee_button() throws InterruptedException {
        Thread.sleep(3000);
        numberOfEmployees =driver.findElements(By.xpath("//tbody")).size();
        hrAppHomePage.createNewEmployeeButton.click();

    }

    @When("user creates employee with data")
    public void user_creates_employee_with_data(DataTable dataTable) {

        data =  dataTable.asMap(String.class,Object.class);
        if(data.containsKey("First name"))
            hrAppCreateEmployeePage.firstName.sendKeys(data.get("First name").toString());

        if(data.containsKey("Last name"))
        hrAppCreateEmployeePage.lastName.sendKeys(data.get("Last name").toString());

        BrowserUtils.SelectByText(hrAppCreateEmployeePage.departmentDropdown,data.get("Department").toString());
        BrowserUtils.SelectByText(hrAppCreateEmployeePage.jobTitleDropdown,data.get("Job Title").toString());
        hrAppCreateEmployeePage.salaryBox.clear();
        hrAppCreateEmployeePage.salaryBox.sendKeys(data.get("Salary").toString());
        hrAppCreateEmployeePage.saveButton.click();

    }

    @Then("user validates that employee is in list of employees")
    public void user_validates_that_employee_is_in_list_of_employees() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().refresh();
        numberOfEmployees++;
        List<WebElement> newEmployeeRow = driver.findElements(By.xpath("//tbody["+numberOfEmployees+"]//td"));

        Assert.assertEquals(data.get("First name").toString(),newEmployeeRow.get(1).getText());
        Assert.assertEquals(data.get("Last name").toString(),newEmployeeRow.get(2).getText());
        Assert.assertEquals(data.get("Department").toString(),newEmployeeRow.get(3).getText());

    }

    @Then("user validates error message in HR App {string}")
    public void userValidatesErrorMessageInHRApp(String errorMessage) {
    }
}
