package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.MyStoreHomePage;
import pages.MyStoreLoginPage;
import pages.MyStoreMyAccountPage;
import pages.MyStoreUpdatePage;
import utilities.ConfigReader;
import utilities.DataUtils;
import utilities.Driver;

public class MyStoreSteps {

   WebDriver driver = Driver.getDriver();
   MyStoreHomePage myStoreHomePage = new MyStoreHomePage();
   MyStoreLoginPage myStoreLoginPage = new MyStoreLoginPage();
   MyStoreMyAccountPage myStoreMyAccountPage = new MyStoreMyAccountPage();
   MyStoreUpdatePage myStoreUpdatePage = new MyStoreUpdatePage();
   String updatedAdr;

    @Given("user navigate to My Store App")
    public void user_navigate_to_My_Store_App() {
        driver.get(ConfigReader.getProperty("MyStoreURL"));
    }
    @When("user clicks on Sign In button")
    public void user_clicks_on_Sign_In_button() {
        myStoreHomePage.signInButton.click();
    }
    @When("user provides email address {string} and password {string} and click on sign in button")
    public void userProvidesEmailAddressAndPasswordAndClickOnSignInButton(String email, String password) {
        myStoreLoginPage.email.sendKeys(email);
        myStoreLoginPage.password.sendKeys(password);
        myStoreLoginPage.submitButton.click();
    }
    @When("user clicks on My Addresses button and update button")
    public void user_clicks_on_My_Addresses_button_and_update_button() {
        myStoreMyAccountPage.myAddressButton.click();
        myStoreMyAccountPage.updateButton.click();
    }
    @When("user provides new street name and number {string} and click on save button")
    public void user_provides_new_street_name_and_number_and_click_on_save_button(String updatedAddress) {
        int streetNumber = DataUtils.generateRandomNumber(999);
        updatedAdr=streetNumber+updatedAddress;
        myStoreUpdatePage.addressBox.sendKeys(Keys.CONTROL+"a"+Keys.BACK_SPACE);
        myStoreUpdatePage.addressBox.sendKeys(updatedAdr);
        System.out.println(updatedAdr);
        myStoreUpdatePage.saveButton.click();
    }
    @Then("user validates that address is updated to {string}")
    public void user_validates_that_address_is_updated_to(String string) {

    }
    @Then("user validates that address is updated")
    public void userValidatesThatAddressIsUpdated() {
        String actualAdr = myStoreMyAccountPage.actualAddress.getText();
        String expectedAddress = updatedAdr ;

        Assert.assertEquals(actualAdr,expectedAddress);
    }
}
