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

    public static Response updateSpartanInfo(int spartanId, JSONObject payload) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .and().body(payload)
                .when()
                .put(Constants.SPARTANSURL+"/{id}")
                .then()
                .extract().response();

        return response;
    }

    public static Response partialUpdateSpartanInfo(int spartanId, JSONObject payload) {
        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .and().pathParam("id", spartanId)
                .and().body(payload)
                .when()
                .patch(Constants.SPARTANSURL+"/{id}")
                .then()
                .extract().response();

        return response;
    }

    public static Response getSpartansByQuery(String nC, String g) {
        Response response = RestAssured.given().log().all()
                .and().queryParam("nameContains",nC)
                .queryParam("gender", g)
                .when()
                .get(Constants.SPARTANSEARCHURL)
                .then()
                .extract().response();

        return response;
    }

    public static Response deleteSpartan(int id) {
        Response response = RestAssured.given()
                .baseUri(Constants.SPARTANSURL)
                .and()
                .pathParam("path", id)
                .when()
                .delete("/{path}")
                .then()
                .extract().response();

        return  response;
    }
}
