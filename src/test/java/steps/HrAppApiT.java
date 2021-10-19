package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import pages.HRAppHomePage;
import pojos.Department;
import pojos.Employee;
import pojos.Job;
import utilities.ApiUtils;
import utilities.BrowserUtils;
import utilities.JDBCUtils;
import utilities.Pages;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class HrAppApiT  {
    List<Map<String,Object>> uiData = new ArrayList<>();
    Response response;
    List<Map<String, Object>> dbData = new ArrayList<>();

    HRAppHomePage hrAppHomePage = new HRAppHomePage();

    String employeeId;

    Map<String, Object> employeeData;
    Map<String,Object> employeeUpdatedData;

    @When("user selects {string} department")
    public void user_selects_department(String department) {
        BrowserUtils.SelectByText(hrAppHomePage.departmentDropdown,department);

    }

    @When("user stores ui data from selected department")
    public void user_stores_ui_data_from_selected_department() {
        for(int i = 0; i<hrAppHomePage.tableData.size(); i+=5) {
            Map<String, Object> map = new HashMap<>();
            map.put(hrAppHomePage.columns.get(0).getText(),hrAppHomePage.tableData.get(i).getText());
            map.put(hrAppHomePage.columns.get(1).getText(),hrAppHomePage.tableData.get(i+1).getText());
            map.put(hrAppHomePage.columns.get(2).getText(),hrAppHomePage.tableData.get(i+2).getText());
            map.put(hrAppHomePage.columns.get(3).getText(),hrAppHomePage.tableData.get(i+3).getText());
            uiData.add(map);
        }
        System.out.println(uiData);
    }

    @When("user sends get employees api call for {string} department")
    public void user_sends_get_employees_api_call_for_department(String departmentName) throws SQLException {
        JDBCUtils.establishConnection();
        List<Map<String,Object>> departmentsIds = JDBCUtils.runQuery("Select department_id\n"+
                "From departments\n"+
                "Where department_name='"+departmentName+"';");
        String depId=departmentsIds.get(0).get("department_id").toString();

         response = ApiUtils.getCall("api/departments/"+depId+"0/employees");
    }

    @Then("user validates {int} status code")
    public void user_validates_status_code(int statusCode) {
        Assert.assertEquals(statusCode,response.statusCode());

    }

    @Then("user validates ui data matches with api data with {string} department employees")
    public void user_validates_ui_data_matches_with_api_data_with_department_employees(String string) {

        for(int i = 0; i<uiData.size(); i++) {
            //Api data
            String apiFirstName = response.jsonPath().getString("["+i+"].firstName");
            String apiLastName = response.jsonPath().getString("["+i+"].lastName");
            String apiEmployeeId = response.jsonPath().getString("["+i+"].employeeId");
            String apiDepartmentName = response.jsonPath().getString("["+i+"].department.departmentName");
            //Ui data
            String uiFirstName = uiData.get(i).get("First Name").toString();
            String uiLastName = uiData.get(i).get("Last Name").toString();
            String uiEmployeeId = uiData.get(i).get("Employee ID").toString();
            String uiDepartmentName = uiData.get(i).get("Department Name").toString();

            Assert.assertEquals(apiFirstName, uiFirstName);
            Assert.assertEquals(apiLastName, uiLastName);
            Assert.assertEquals(apiEmployeeId, uiEmployeeId);
            Assert.assertEquals(apiDepartmentName, uiDepartmentName);
        }

    }

    @When("user gets employees from DB who works in {string} department")
    public void user_gets_employees_from_DB_who_works_in_department(String department) throws SQLException {
        JDBCUtils.establishConnection();
        dbData = JDBCUtils.runQuery("select e.employee_id, e.first_name, e.last_name, d.department_name\n" +
                "from employees e join departments d on\n" +
                "e.department_id=d.department_id \n" +
                "where d.department_name='"+department+"';");

    }

    @Then("user validates ui data matches with DB data")
    public void user_validates_ui_data_matches_with_DB_data() {
        for (int i = 0; i < uiData.size(); i++) {
            //DB data
            String dbFirstName = dbData.get(i).get("first_name").toString();
            String dbLastName = dbData.get(i).get("last_name").toString();
            String dbEmployeeId = dbData.get(i).get("employee_id").toString();
            String dbDepartmentName = dbData.get(i).get("department_name").toString();
            //Ui data
            String uiFirstName = uiData.get(i).get("First Name").toString();
            String uiLastName = uiData.get(i).get("Last Name").toString();
            String uiEmployeeId = uiData.get(i).get("Employee ID").toString();
            String uiDepartmentName = uiData.get(i).get("Department Name").toString();

            Assert.assertEquals(dbFirstName, uiFirstName);
            Assert.assertEquals(dbLastName, uiLastName);
            Assert.assertEquals(dbEmployeeId, uiEmployeeId);
            Assert.assertEquals(dbDepartmentName, uiDepartmentName);

        }
    }
    @Given("user creates employee with api post call with data")
    public void user_creates_employee_with_api_post_call_with_data(DataTable dataTable) {
        String endpoint = "/api/employees";
        Employee employeeBody = new Employee();
        employeeData = dataTable.asMap(String.class,Object.class);
        employeeBody.setFirstName(employeeData.get("First Name").toString());
        employeeBody.setLastName(employeeData.get("Last Name").toString());
        Department department = new Department();
        department.setDepartmentName("IT");
        employeeBody.setDepartment(department);
        Job job = new Job();
        job.setTitle("SDET");
        employeeBody.setJob(job);
        response = ApiUtils.postCall(endpoint,employeeBody);
        employeeId = response.jsonPath().getString("employeeId");




    }

    @Then("user validates employee is created with api get call")
    public void user_validates_employee_is_created_with_api_get_call() {
        String endpoint = "/api/employees/"+employeeId;
        response = ApiUtils.getCall(endpoint);
        Assert.assertEquals(employeeData.get("First Name"),response.jsonPath().getString("firstName"));
        Assert.assertEquals(employeeData.get("Last Name"),response.jsonPath().getString("lastName"));
    }

    @When("user updates employee data")
    public void user_updates_employee_data(DataTable dataTable) {
        String endpoint = "/api/employees/"+employeeId;
        Employee employeeBody = new Employee();
        employeeUpdatedData = dataTable.asMap(String.class,Object.class);
        employeeBody.setFirstName(employeeUpdatedData.get("First Name").toString());
        employeeBody.setLastName(employeeUpdatedData.get("Last Name").toString());
        response = ApiUtils.putCall(endpoint,employeeBody);

    }

    @When("user validates employee info is updated with api get call")
    public void user_validates_employee_info_is_updated_with_api_get_call() {
        String endpoint = "/api/employees/"+employeeId;
        response = ApiUtils.getCall(endpoint);

        Assert.assertEquals(employeeUpdatedData.get("First Name"),response.jsonPath().getString("firstName"));
        Assert.assertEquals(employeeUpdatedData.get("Last Name"),response.jsonPath().getString("lastName"));


    }

    @When("user deletes employee with api delete call")
    public void user_deletes_employee_with_api_delete_call() {

        String endpoint = "/api/employees/"+employeeId;
        response = ApiUtils.deleteCall(endpoint);

    }

    @Then("user validates employee is deleted with api get call")
    public void user_validates_employee_is_deleted_with_api_get_call() {
       String endpoint = "/api/employees/"+employeeId;
       response = ApiUtils.getCall(endpoint);

    }

}
