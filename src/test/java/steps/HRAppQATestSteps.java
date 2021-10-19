package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HRAppQATestSteps {

    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppHomePage hrAppHomePage = new HRAppHomePage();
    int employeeId;

    @Given("user navigates to HRApp")
    public void user_navigates_to_HRApp() {
        driver.get(ConfigReader.getProperty("HRAppQaURL"));

    }

    @When("user logs in with username “Mindtek” and password “MindtekStudent”")
    public void user_logs_in_with_username_Mindtek_and_password_MindtekStudent() {
        hrAppLoginPage.usernameBox.sendKeys(ConfigReader.getProperty("HRAppUsername"));
        hrAppLoginPage.passwordBox.sendKeys(ConfigReader.getProperty("HRAppPassword"));
        hrAppLoginPage.loginButton.click();
    }
    @When("user search for employee with employee id {int}")
    public void user_search_for_employee_with_employee_id(int employeeId) {
        hrAppHomePage.searchBox.sendKeys(String.valueOf(employeeId));
        hrAppHomePage.searchButton.click();
    }

    @Then("user validates that employee data in UI matches with Database data with employee id {int}")
    public void user_validates_that_employee_data_in_UI_matches_with_Database_data_with_employee_id(int employeeId){ //throws SQLException {
        //JDBCUtils.establishConnection();
       // List<Map<String,Object>> data = JDBCUtils.runQuery("SELECT * FROM employees WHERE employee_id="+employeeId);

        //List<WebElement> employeeRow = driver.findElements(By.xpath("//div/table/tbody[2]"));
        //System.out.println(employeeRow.get(1).getText());


    }
}
