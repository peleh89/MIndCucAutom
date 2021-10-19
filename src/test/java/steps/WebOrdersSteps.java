package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.WebOrderEditProfilePage;
import pages.WebOrderHomePage;
import pages.WebOrdersLoginPage;
import pages.WebOrdersOrderPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.Driver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class WebOrdersSteps {

    WebDriver driver = Driver.getDriver();
    WebOrdersLoginPage webOrdersLoginPage = new WebOrdersLoginPage();
    WebOrderHomePage webOrderHomePage =new WebOrderHomePage();
    WebOrderEditProfilePage webOrderEditProfilePage = new WebOrderEditProfilePage();
    WebOrdersOrderPage webOrdersOrderPage = new WebOrdersOrderPage();
    int numberOfRows;
    List<Map<String, Object>> data;
    String expectedCardName;

    @Given("user navigate to weborders application")
    public void user_navigate_to_weborders_application() {
        driver.get(ConfigReader.getProperty("WebOrdersURL"));

    }

    @When("user provides username {string} and password {string} and clicks on login")
    public void user_provides_username_and_password_and_clicks_on_login(String username, String password) {
        webOrdersLoginPage.username.sendKeys(username);
        webOrdersLoginPage.password.sendKeys(password);
        webOrdersLoginPage.loginButton.click();

    }

    @Then("user validates application is logged in")
    public void user_validates_application_is_logged_in() {
     String actualTitle = driver.getTitle();
     String expectedTitle = "Web Orders";
        Assert.assertEquals(expectedTitle,actualTitle);
        driver.quit();

    }

    @Then("user validates error message {string}")
    public void user_validates_error_message(String errorMessage) {
        String actualErrorMessage = webOrdersLoginPage.errorMessage.getText();
        Assert.assertEquals(errorMessage,actualErrorMessage);
        driver.quit();

    }


    @And("user clicks on Order module")
    public void userClicksOnOrderModule() {
        webOrderHomePage.orderModule.click();
        
    }

    @And("user selects {string} product with {int} quantity")
    public void userSelectsProductWithQuantity(String product, int quantity) {
        BrowserUtils.SelectByValue(webOrdersOrderPage.productDropdown,product);
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(quantity+""+Keys.ENTER);
        
    }

    @Then("user validates total is calculated for quantity {int}")
    public void userValidatesTotalIsCalculatedForQuantity(int quantity) {
        String pricePerUnit = webOrdersOrderPage.pricePerUnit.getAttribute("value");

        String discountAmount = webOrdersOrderPage.discountBox.getAttribute("value");
        int discountAmountInt = Integer.parseInt(discountAmount);
        int expectedTotal = 0;
        if (discountAmountInt==0){
            expectedTotal = quantity*Integer.parseInt(pricePerUnit);
        }else {
            expectedTotal = quantity*Integer.parseInt(pricePerUnit);
            expectedTotal = expectedTotal-expectedTotal*discountAmountInt/100;

        }



        String actualTotalStr = webOrdersOrderPage.total.getAttribute("value");
        int actualTotal =Integer.parseInt(actualTotalStr);

        Assert.assertEquals(expectedTotal,actualTotal);


    }
    @When("user creates order with data")
    public void user_creates_order_with_data(DataTable dataTable) {
       data = dataTable.asMaps(String.class, Object.class);
 /*
        System.out.println(data.get(0).get("name"));
        System.out.println(data.get(2).get("quantity"));//      System.out.println(data.get(3).get("expire date"));
        System.out.println(data.get(0).get("order"));
 */
        BrowserUtils.SelectByValue(webOrdersOrderPage.productDropdown, data.get(0).get("order").toString());
        webOrdersOrderPage.quantityBox.sendKeys(Keys.BACK_SPACE);
        webOrdersOrderPage.quantityBox.sendKeys(data.get(0).get("quantity").toString());
        webOrdersOrderPage.name.sendKeys(data.get(0).get("name").toString());
        webOrdersOrderPage.address.sendKeys(data.get(0).get("address").toString());
        webOrdersOrderPage.city.sendKeys(data.get(0).get("city").toString());
        webOrdersOrderPage.state.sendKeys(data.get(0).get("state").toString());
        webOrdersOrderPage.zip.sendKeys(data.get(0).get("zip").toString());
        webOrdersOrderPage.visaCheckBox.click();
        expectedCardName = webOrdersOrderPage.visaCheckBox.getAttribute("value");
        webOrdersOrderPage.cardNumber.sendKeys(data.get(0).get("cc").toString());
        webOrdersOrderPage.expireDate.sendKeys(data.get(0).get("expire date").toString());
        webOrdersOrderPage.processButton.click();
    }


    @Then("user validates success message {string}")
    public void userValidatesSuccessMessage(String expectedMessage) {
        String actualSuccessMessage = webOrdersOrderPage.successMessage.getText();
        Assert.assertEquals(expectedMessage,actualSuccessMessage);

    }

    @And("user validates order added to List Of Orders")
    public void userValidatesOrderAddedToListOfOrders() {
        webOrderHomePage.viewAllOrdersModule.click();
        int numberOfRowsAfterOrderCreation = webOrderHomePage.numberOfRows.size();
        Assert.assertEquals(numberOfRowsAfterOrderCreation-numberOfRows,1);

        String actualName = webOrderHomePage.firstRowName.getText();
        Assert.assertEquals(data.get(0).get("name").toString(),actualName);

        String actualProduct = webOrderHomePage.firstRowProduct.getText();
        Assert.assertEquals(data.get(0).get("order").toString(),actualProduct);

        String actualQuantity = webOrderHomePage.firstRowQuantity.getText();
        Assert.assertEquals(data.get(0).get("quantity").toString(),actualQuantity);

        String actualStreet = webOrderHomePage.firstRowStreet.getText();
        Assert.assertEquals(data.get(0).get("address").toString(),actualStreet);

        String actualCity = webOrderHomePage.firstRowCity.getText();
        Assert.assertEquals(data.get(0).get("city").toString(),actualCity);

        String actualState = webOrderHomePage.firstRowState.getText();
        Assert.assertEquals(data.get(0).get("state").toString(),actualState);

        String actualZip = webOrderHomePage.firstRowZip.getText();
        Assert.assertEquals(data.get(0).get("zip").toString(),actualZip);

        String actualCardNumber = webOrderHomePage.firstRowCardNumber.getText();
        Assert.assertEquals(data.get(0).get("cc").toString(),actualCardNumber);

        Assert.assertEquals(expectedCardName,webOrderHomePage.firstRowCardName.getText());

        String actualExpirationDate = webOrderHomePage.firstRowCardExpiration.getText();
        Assert.assertEquals(data.get(0).get("expire date").toString(),actualExpirationDate);

        String actualDate = webOrderHomePage.firstRowDate.getText();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date expectedDate = new Date();
        Assert.assertEquals(formatter.format(expectedDate),actualDate);



    }

    @And("user counts number of orders in table")
    public void userCountsNumberOfOrdersInTable() {
        numberOfRows = webOrderHomePage.numberOfRows.size();

    }
}
