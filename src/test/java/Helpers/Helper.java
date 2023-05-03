package Helpers;

import Utils.ConfigurationReader;
import Utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Helper {

    public static Response getAllProductsList() {

        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .get(Constants.PRODUCTLIST)
                .then()
                .extract().response();

        return response;
    }

    public static Response POSTToAllProductsList() {

        Response response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .when()
                .post(Constants.PRODUCTLIST)
                .then()
                .extract().response();

        return response;
    }

    public static Response ResponseGetAllBrandsList() {

        Response response = RestAssured.given().log().all()
                .when()
                .get(Constants.BRANDLIST)
                .then()
                .extract().response();

        return response;
    }

    public static Response PUTToAllBrandsList(){

        return RestAssured.given().log().all()
                .when()
                .put(Constants.BRANDLIST)
                .then()
                .extract().response();
    }

    public static Response POSTToSearchProduct(){

        Response response = RestAssured.given().log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("search_product","top")
                .when()
                .post(Constants.SEARCHPRODUCT)
                .then()
                .extract().response();

        return response;
    }

    public static Response SearchProductNegative(){

        Response response = RestAssured.given().log().all()
                .contentType("application/x-www-form-urlencoded")
                .when()
                .post(Constants.SEARCHPRODUCT)
                .then()
                .extract().response();

        return response;
    }

    public static Response verifyLoginFunctionPositive(){

        Response response = RestAssured.given().log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigurationReader.get("email"))
                .formParam("password", ConfigurationReader.get("password"))
                .when()
                .post(Constants.LOGIN)
                .then()
                .extract().response();

        return response;
    }


}
