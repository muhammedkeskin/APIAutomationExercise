package Tests;

import Helpers.SpartanHelper;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanTests {

    private static int id = 0;

    @Order(1)
    @Test
    public void getAllSpartansTest() {
        Response response = SpartanHelper.getAllSpartans();

        response.then().assertThat().statusCode(200);
    }

    @Order(2)
    @Test
    public void createSpartanTest() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("gender","Male");
        requestBody.put("name","JohnDoe");
        requestBody.put("phone","01234567890");

        Response response = SpartanHelper.createSpartan(requestBody);

        response.then().assertThat().statusCode(201);

        JsonPath jsonPath = response.jsonPath();

        id = jsonPath.getInt("data.id");

        System.out.println("id = " + id);
    }

    @Order(3)
    @Test
    public void getInfoOfOneSpartan() {

        Response response = SpartanHelper.getInfoOfOneSpartan(id);

        response.then().assertThat().statusCode(200);
    }
}
