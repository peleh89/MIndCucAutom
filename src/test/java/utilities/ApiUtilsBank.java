package utilities;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ApiUtilsBank {

    /*
    .getAccessToken(); -> this method will return token as String value
     */
    private static String getAccessToken(){
        Response response = given().baseUri(ConfigReader.getProperty("ApiBankBaseURL"))
                .accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body("{ \"password\": \"MindtekStudent\",\n" +
                        "    \"username\": \"Mindtek\"}")
                .when().post("authenticate");
              response.then().log().all();
         String token = response.jsonPath().getString("jwt");
         return token;
    }
    /*
    .setHeaders();  ->  this method will set headers to api call and will return Map of headers
     */

    public static Map<String,Object> setHeaders(){
        Map<String, Object> headers = new HashMap<>();
        headers.put("Accept","application/json");
        headers.put("Content-Type","application/json");
        headers.put("Authorization","Bearer "+getAccessToken());
        return headers;

    }

    public  static Response getCall (String endpoint){
        Response response = given().baseUri(ConfigReader.getProperty("ApiBankBaseURL"))
                .and().headers(setHeaders())
                .when().get(endpoint);
        response.then().log().all();
        return  response;
    }

    public static Response postCall(String endpoint, Object body){
        Response response = given().baseUri(ConfigReader.getProperty("ApiBankBaseURL"))
                .and().headers(setHeaders())
                .when().body(body)
                .and().post(endpoint);
        response.then().log().all();
        return response;
    }

    public static Response putCall(String endpoint, Object body){
        Response response = given().baseUri(ConfigReader.getProperty("ApiBankBaseURL"))
                .and().headers(setHeaders())
                .when().body(body)
                .and().put(endpoint);
        response.then().log().all();
        return response;
    }

    public  static Response deleteCall(String endpoint){
        Response response = given().baseUri(ConfigReader.getProperty("ApiBankBaseURL"))
                .and().headers(setHeaders())
                .when().delete(endpoint);
        response.then().log().all();
        return response;
    }

}
