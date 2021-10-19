package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.PizzaAppHomePage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.util.Map;

public class PizzaAppSteps {

    WebDriver driver = Driver.getDriver();
    PizzaAppHomePage pizzaAppHomePage = new PizzaAppHomePage();
    String cost;

    @Given("user navigate to pizza application")
    public void user_navigate_to_pizza_application() {
        driver.get(ConfigReader.getProperty("PizzaAppPath"));


    }

    @When("user creates pizza order with data")
    public void user_creates_pizza_order_with_data(DataTable dataTable) {
        Map<String,Object> data = dataTable.asMap(String.class,Object.class);

        for (Object listValues : data.values()){
            System.out.println(listValues);
        }
        BrowserUtils.SelectByValue(pizzaAppHomePage.pizza1Dropdown, data.get("Pizza").toString());
        BrowserUtils.SelectByValue(pizzaAppHomePage.toppings1,data.get("Toppings 1").toString());
        BrowserUtils.SelectByValue(pizzaAppHomePage.toppings2,data.get("Toppings 2").toString());
        pizzaAppHomePage.pizzaQty.sendKeys(data.get("Quantity").toString());
        pizzaAppHomePage.name.sendKeys(data.get("Name").toString());
        pizzaAppHomePage.email.sendKeys(data.get("Email").toString());
        pizzaAppHomePage.phone.sendKeys(data.get("Phone").toString());
        if (data.get("Payment Type").toString().equalsIgnoreCase("Cash on Pickup")){
            pizzaAppHomePage.cashPayment.click();
        }else if (data.get("Payment Type").toString().equalsIgnoreCase("Credit Card")){
            pizzaAppHomePage.ccPayment.click();
        }
        cost=pizzaAppHomePage.cost.getAttribute("value");
        pizzaAppHomePage.placeOrderButton.click();


    }


    @Then("user validates that order is created with success message {string} for {string} {string}")
    public void userValidatesThatOrderIsCreatedWithSuccessMessageFor(String success, String quantity, String pizza) {
        String expectedSuccessMessage = success + cost +" " + pizza;
        String actualMessage = pizzaAppHomePage.successMessage.getText();
        Assert.assertEquals(expectedSuccessMessage,actualMessage);
    }
}
