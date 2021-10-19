package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.HRAppCreateEmployeePage;
import pages.HRAppHomePage;
import pages.HRAppLoginPage;
import utilities.Driver;

import java.util.List;

public class HRAppSteps2 {
    WebDriver driver = Driver.getDriver();
    HRAppLoginPage hrAppLoginPage = new HRAppLoginPage();
    HRAppHomePage hrAppHomePage = new HRAppHomePage();
    HRAppCreateEmployeePage hrAppCreateEmployeePage = new HRAppCreateEmployeePage();
    int number;
    String employeeID;
    String newName;
    String updatedName;


    @When("user deletes created employee")
    public void user_deletes_created_employee() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().refresh();
        //number = driver.findElements(By.xpath("//tbody")).size();
        number = 2;
        System.out.println(number);
        employeeID = driver.findElement(By.xpath("//tbody["+number+"]//td[1]")).getText();
        System.out.println(employeeID);
        driver.findElement(By.xpath("//tbody["+number+"]//td[6]")).click();

    }

    @Then("user validates that employee is deleted")
    public void user_validates_that_employee_is_deleted() {
        String deleteMessage = hrAppHomePage.deleteMessage.getText();
        Assert.assertTrue(deleteMessage.contains(employeeID));

    }

    @When("user edit created employee first name")
    public void user_edit_created_employee_first_name() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().refresh();
        //number = driver.findElements(By.xpath("//tbody")).size();
        number = 2;
        System.out.println(number);
        driver.findElement(By.xpath("//tbody["+number+"]//td[5]/button")).click();
        hrAppCreateEmployeePage.firstName.sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        newName="Chicago";
        hrAppCreateEmployeePage.firstName.sendKeys(newName);
        hrAppCreateEmployeePage.saveButton.click();

    }

    @Then("user validates that employee first name is updated")
    public void user_validates_that_employee_first_name_is_updated() {
         updatedName = driver.findElement(By.xpath("//tbody["+number+"]//td[2]")).getText();
        Assert.assertEquals(newName,updatedName);

    }
}
