package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTest {

    public static void main(String[] args) {

        /*
        do GET call with emp_id=100
        /api/employees/100
         */

        Response response =
                given().baseUri("https://devenv-apihr-arslan.herokuapp.com")  //base URL
                .and().accept(ContentType.JSON)  // Header(Accept)
                .when().get("/api/employees/100");  // call endpoint

        response.then().log().all();

        System.out.println(response.statusCode());
        System.out.println(response.body().asString());
        System.out.println(response.body().as(HashMap.class));

        Map<String, Object> responseData = response.body().as(HashMap.class);

        System.out.println(responseData.get("employeeId"));

        /*
        Post employee:
        Request: BaseURL+End point+Headers + Json bOdy
        Given BaseURL
        And Accept-> application json
        And Content Type -> application json
        When Json Body
        And Send Post call

        Response:
        status code should be -> 201
         */



        Response postResponse1 = given().baseUri("https://devenv-apihr-arslan.herokuapp.com")  //base URL
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        "\t\"firstName\" : \"Igor\",\n" +
                        "\t\"lastName\" : \"Peleh\"\n" +
                        "}")
                .and().post("/api/employees");
        System.out.println(postResponse1.statusCode());
        System.out.println(postResponse1.body().asString());
        String empId = postResponse1.jsonPath().getString("employeeId");

        /*
        getPutCall
         */

        Response putResponse = given().baseUri("https://devenv-apihr-arslan.herokuapp.com")
                .and().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .when().body("{\n" +
                        "\t\"firstName\" : \"Igor\",\n" +
                        "\t\"lastName\" : \"Pel\"\n" +
                        "}")
                .and().put("/api/employees/"+empId);
        System.out.println(putResponse.statusCode());
        System.out.println(putResponse.body().asString());


        /*
        delete call
         */


        Response deleteResponse = given().baseUri("https://devenv-apihr-arslan.herokuapp.com")
                .when().delete("/api/employees/"+empId);
        System.out.println(deleteResponse.statusCode());



    }
}
