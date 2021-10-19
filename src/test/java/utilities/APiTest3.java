package utilities;

import io.restassured.response.Response;
import pojos.Department;
import pojos.Employee;
import pojos.Job;

public class APiTest3 {
    public static void main(String[] args) {

        /*
        create an employee
         */
        String endpoint = "/api/employees";

        Employee requestBody = new Employee();
        requestBody.setFirstName("Gary");
        requestBody.setLastName("Marly");
        Department department = new Department();
        department.setDepartmentName("IT");
        requestBody.setDepartment(department);
        Job job =new Job();
        job.setTitle("SDET");
        job.setSalary(10000.0);
        requestBody.setJob(job);
        Response response = ApiUtils.postCall(endpoint,requestBody);

        System.out.println(response.statusCode());

    }

}
