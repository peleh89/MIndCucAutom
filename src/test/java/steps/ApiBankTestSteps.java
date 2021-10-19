package steps;

import bankpojos.Account;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utilities.ApiUtilsBank;

import java.util.Map;

public class ApiBankTestSteps {
    Response response;
    Map<String, Object> accountData;
    String accountId;
    Map<String, Object> updatedData;
    @Given("user creates account with api call with data")
    public void user_creates_account_with_api_call_with_data(DataTable dataTable) {
        accountData = dataTable.asMap(String.class,Object.class);
        String endpoint = "/api/account";
        Account account = new Account();
        account.setAccountType(accountData.get("accountType").toString());
        account.setBalance(Double.valueOf(accountData.get("balance").toString()));
        response =ApiUtilsBank.postCall(endpoint,account);
        accountId=response.body().jsonPath().getString("id");



    }

    @Then("user validate {int} status code")
    public void user_validate_status_code(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusCode());

    }

    @Then("user validate that account is created with api get call")
    public void user_validate_that_account_is_created_with_api_get_call() {
        String endpoint  = "/api/accounts/"+accountId;
        response=ApiUtilsBank.getCall(endpoint);
        Account responseBody = response.body().as(Account.class);
        Assert.assertEquals(accountData.get("accountType").toString(),responseBody.getAccountType());
        Assert.assertEquals(Double.valueOf(accountData.get("balance").toString()),responseBody.getBalance());

    }

    @When("user updates account with api put call with data")
    public void user_updates_account_with_api_put_call_with_data(DataTable dataTable) {
        String endpoint = "/api/accounts/"+accountId;
        Account account = new Account();
        updatedData=dataTable.asMap(String.class,Object.class);
        account.setAccountType(updatedData.get("accountType").toString());
        account.setBalance(Double.valueOf(updatedData.get("balance").toString()));
        account.setId(Integer.valueOf(accountId));
        response = ApiUtilsBank.putCall(endpoint,account);






    }

    @Then("user validates that account is updated with api get call")
    public void user_validates_that_account_is_updated_with_api_get_call() {
        String endpoint = "/api/accounts/"+accountId;
        response=ApiUtilsBank.getCall(endpoint);
        Account responseAccount = response.body().as(Account.class);

        Assert.assertEquals(updatedData.get("accountType").toString(),responseAccount.getAccountType());
        Assert.assertEquals(Double.valueOf(updatedData.get("balance").toString()),responseAccount.getBalance());
    }

    @When("user deletes account with api call")
    public void user_deletes_account_with_api_call() {
        String endpoint = "/api/accounts/"+accountId;
        response = ApiUtilsBank.deleteCall(endpoint);

    }

    @Then("user validates that account deleted with api call")
    public void user_validates_that_account_deleted_with_api_call() {
        String endpoint = "/api/accounts/"+accountId;
        response= ApiUtilsBank.getCall(endpoint);
        response.then().statusCode(404);

    }

}
