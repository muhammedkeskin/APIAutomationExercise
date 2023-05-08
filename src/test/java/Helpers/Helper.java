package Helpers;

import Utils.CommonMethods;
import Utils.ConfigurationReader;
import Utils.Constants;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Helper {

    public static String firstName =  CommonMethods.firstName();
    public static String lastName =  CommonMethods.lastName();
    public static String companyName =  CommonMethods.companyName();

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

    public static Response verifyLoginFunctionNegative(){

        Response response = RestAssured.given().log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("password", ConfigurationReader.get("password"))
                .when()
                .post(Constants.LOGIN)
                .then()
                .extract().response();

        return response;
    }

    public static Response verifyLoginFunctionWithDeleteMethod(){

        Response response = RestAssured.given().log().all()
                .contentType("application/x-www-form-urlencoded")
                .formParam("email", ConfigurationReader.get("email"))
                .formParam("password", ConfigurationReader.get("password"))
                .when()
                .delete(Constants.LOGIN)
                .then()
                .extract().response();

        return response;
    }

    public static Response createUser() {
        Response response = RestAssured.given()
                .contentType("application/x-www-form-urlencoded")
                .formParam("name", "name1")
                .formParam("email", ConfigurationReader.get("email"))
                .formParam("password", ConfigurationReader.get("password"))
                .formParam("title", "Mr")
                .formParam("birth_date",1)
                .formParam("birth_month", 1)
                .formParam("birth_year", 2000)
                .formParam("firstname", firstName)
                .formParam("lastname", lastName)
                .formParam("company", companyName)
                .formParam("address1", "address1")
                .formParam("address2", "address2")
                .formParam("country", "country")
                .formParam("zipcode", 1234)
                .formParam("state", "state")
                .formParam("city", "city")
                .formParam("mobile_number", 1234567890)
                .when()
                .post(Constants.CREATEACCOUNT)
                .then()
                .extract().response();

        return response;
    }

    public static Response getDetailsOfUser() {
        Response response = RestAssured.given().log().all()
                .queryParam("email", ConfigurationReader.get("email"))
                .when()
                .get(Constants.DETAIL)
                .then()
                .extract().response();

        return response;
    }

    public static Response deleteAccount() {
        Response response = RestAssured.given().log().all()
                .queryParam("email", ConfigurationReader.get("email"))
                .queryParam("password", ConfigurationReader.get("password") )
                .when()
                .delete(Constants.DELETEACCOUNT)
                .then()
                .extract().response();

        return response;
    }

}
