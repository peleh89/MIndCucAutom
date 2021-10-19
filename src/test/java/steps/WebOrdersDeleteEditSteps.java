package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.WebOrderEditProfilePage;
import pages.WebOrderHomePage;
import pages.WebOrdersLoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.List;

public class WebOrdersDeleteEditSteps {
    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrderHomePage webOrderHomePage = new WebOrderHomePage();
    WebOrderEditProfilePage webOrderEditProfilePage = new WebOrderEditProfilePage();

    @Given("user navigates to WebOrdersapplication")
    public void user_navigates_to_WebOrdersapplication() {

        driver.get(ConfigReader.getProperty("WebOrdersURL"));
    }

    @When("user logins in with username {string} and password {string}")
    public void user_logins_in_with_username_and_password(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();
    }

    @When("user selects any order from View All Orders")
    public void user_selects_any_order_from_View_All_Orders() {
        webOrderHomePage.selectionBox.click();
    }

    @When("user deletes selected order")
    public void user_deletes_selected_order() {
        webOrderHomePage.deleteButton.click();
    }

    @Then("user validates that order is deleted")
    public void user_validates_that_order_is_deleted() {
        List<WebElement> listOfOrder = webOrderHomePage.listBox;
        for (WebElement lists : listOfOrder) {
            System.out.println(lists.getText());
            Assert.assertFalse(lists.getText().contains("Paul Brown"));
        }

    }

    // Second scenario
    @When("user clicks on edit button and updates customer name")
    public void user_clicks_on_edit_button_and_updates_customer_name() throws InterruptedException {
        webOrderHomePage.editButton.click();
        webOrderEditProfilePage.customerNameBox.sendKeys(Keys.CONTROL, "a");
        webOrderEditProfilePage.customerNameBox.sendKeys(Keys.DELETE);
        String updatedName = "Jhon Doe";
        webOrderEditProfilePage.customerNameBox.sendKeys(updatedName);
        webOrderEditProfilePage.updateButton.click();

    }

    @Then("user validates that customer name is updated in View All Orders pge")
    public void user_validates_that_customer_name_is_updated_in_View_All_Orders_pge() {
        List<WebElement> listOfOrder = webOrderHomePage.listBox;
        for (WebElement lists : listOfOrder) {
            System.out.println(lists.getText());
            Assert.assertTrue(lists.getText().contains("Jhon Doe"));

        }
    }
}
