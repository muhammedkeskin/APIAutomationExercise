package Helpers;

import Utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;

public class SpartanHelper {

    public static Response getAllSpartans() {
        Response response = RestAssured.given().log().all()
                .when()
                .get(Constants.SPARTANBASEURL)
                .then()
                .extract().response();

        return response;
    }

    public static Response createSpartan(JSONObject payload) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(Constants.SPARTANSURL)
                .then()
                .extract().response();

        return response;
    }

    public static Response getInfoOfOneSpartan(int spartanId) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .when()
                .get(Constants.SPARTANSURL+"/{id}")
                .then()
                .extract().response();

        return response;
    }



}
