package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ApiUtilsBank;
import utilities.ConfigReader;

import static io.restassured.RestAssured.given;

public class BankApiSteps {

    Response response;

    @Given("User sends get customers api call with access token")
    public void user_sends_get_customers_api_call_with_access_token() {
         response = ApiUtilsBank.getCall("/api/customers");

    }

    @Then("User validates {int} status code")
    public void user_validates_status_code(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());



    }

    @Given("User sends get customers api call without access token")
    public void user_sends_get_customers_api_call_without_access_token() {

        response=given().baseUri(ConfigReader.getProperty("ApiBankBaseURL"))
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().get("/api/customers");

    }


}
