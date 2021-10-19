package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiTest5 {
    public static void main(String[] args) {

        Response response = given().baseUri("https://mindtek-restapi.herokuapp.com")
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{ \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"}")
                .when().post("/authenticate");

        String token = response.body().jsonPath().getString("jwt");
        System.out.println(token);

        Map<String,Object> header = new HashMap<>();
        header.put("Authorization","Bearer "+token);
        Response postResponse = given().baseUri("https://mindtek-restapi.herokuapp.com")
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(header)
                .and().body("{\n" +
                        "    \"accountType\": \"Saving\",\n" +
                        "    \"balance\": 1515\n" +
                        "\n" +
                        "}")
                .when().post("/api/account");
        postResponse.then().log().all();


    }

}
