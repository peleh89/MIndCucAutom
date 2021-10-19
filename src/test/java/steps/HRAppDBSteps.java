package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utilities.JDBCUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HRAppDBSteps {

    @Given("New employee was created")
    public void new_employee_was_created() {
        System.out.println("New employee Neena Kochar was created");

    }

    @When("User accesses the Database")
    public void user_accesses_the_Database() {
        JDBCUtils.establishConnection();
    }

    @Then("User validates new employee")
    public void user_validates_new_employee() throws SQLException {
        List<Map<String,Object>> data = JDBCUtils.runQuery("SELECT * FROM employees WHERE employee_id=101");
        String firstName = data.get(0).get("first_name").toString();
        System.out.println(firstName);
        Assert.assertEquals(firstName,"Neena");

        JDBCUtils.closeConnection();

    }
}
