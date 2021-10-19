package steps;

import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import pojos.Department;
import pojos.Employee;
import utilities.ApiUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Map;

public class HRAppAPIStep {
    Response response;
    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppHomePage hrAppHomePage = new HRAppHomePage();

    @Given("User sends create employee api post call with data")
    public void user_sends_create_employee_api_post_call_with_data(io.cucumber.datatable.DataTable dataTable) {

        Map<String,Object> data = dataTable.asMap(String.class,Object.class);

        String endpoint = "/api/employees";
        Employee requestBody = new Employee();
        Department department = new Department();
        requestBody.setFirstName(data.get("firstName").toString());
        requestBody.setLastName(data.get("lastName").toString());
        department.setDepartmentName(data.get("departmentName").toString());
        requestBody.setDepartment(department);

         response = ApiUtils.postCall(endpoint,requestBody);
    }

    @Then("user validates status code {int}")
    public void user_validates_status_code(int responseCode) {
        Assert.assertEquals(responseCode,response.getStatusCode());


    }

    @Then("user validates data populated in UI")
    public void user_validates_data_populated_in_UI(io.cucumber.datatable.DataTable dataTable) throws InterruptedException {
        driver.get(ConfigReader.getProperty("HRAppURL"));
        hrAppLoginPage.usernameBox.sendKeys(ConfigReader.getProperty("HRAppUsername"));
        hrAppLoginPage.passwordBox.sendKeys(ConfigReader.getProperty("HRAppPassword"));
        hrAppLoginPage.loginButton.click();
        Employee responseBody = response.body().as(Employee.class);
        String employeeId = responseBody.getEmployeeId().toString();
        System.out.println(employeeId);
        Thread.sleep(2000);
        hrAppHomePage.searchBox.sendKeys(employeeId);
        hrAppHomePage.searchButton.click();

        String actualFirstName = hrAppHomePage.firstName.getText();
        String actualLastName = hrAppHomePage.lastName.getText();
        String actualDepartmentName = hrAppHomePage.departmentName.getText();

        Map<String,Object> data = dataTable.asMap(String.class,Object.class);

        Assert.assertEquals(data.get("firstName").toString(),actualFirstName);
        Assert.assertEquals(data.get("lastName").toString(),actualLastName);
        Assert.assertEquals(data.get("departmentName").toString(),actualDepartmentName);



    }

    @Then("User validates employee data os persisted into DB")
    public void user_validates_employee_data_os_persisted_into_DB() {

    }

    @Then("User validates data with get employee api call")
    public void user_validates_data_with_get_employee_api_call(io.cucumber.datatable.DataTable dataTable) {

    }


}
