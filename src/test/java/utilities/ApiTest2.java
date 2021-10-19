package utilities;

import io.restassured.response.Response;
import pojos.Employee;

public class ApiTest2 {
    public static void main(String[] args) {


        Response response2 = ApiUtils.getCall("/api/employees/100");
        //System.out.println(response2.body().asString());

        //response2.then().log().all();

        Employee responseBody = new Employee();

        responseBody = response2.body().as(Employee.class);
        System.out.println(responseBody.getLastName());
        System.out.println(responseBody.getJob().getSalary());
        System.out.println(responseBody.getDepartment().getLocation().getLocationCity());
        System.out.println(responseBody.getDepartment().getDepartmentName());
    }
}
