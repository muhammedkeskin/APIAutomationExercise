package Tests;

import Helpers.Helper;
import Utils.CommonMethods;
import Utils.ConfigurationReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class Tests {

    @Test
    public void verifyThatProductList() {
        Response response = Helper.getAllProductsList();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 200, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check first id", "Women", jsonPath.getString("products[0].category.usertype.usertype"));

        //System.out.println("jsonPath.getInt(\"responseCode\") = " + jsonPath.getInt("responseCode"));

        //System.out.println("response.getBody().asString() = " + response.getBody().asString());
        System.out.println("jsonPath.prettyPrint() = " + jsonPath.prettyPrint());

    }

    @Test
    public void VerifyThatProductListWithPostMethod() {
        Response response = Helper.POSTToAllProductsList();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("verify response code", 405, jsonPath.getInt("responseCode"));
        Assert.assertEquals("verify message", "This request method is not supported.", jsonPath.getString("message"));

    }

    @Test
    public void verifyThatBrandList() {
        Response response = Helper.ResponseGetAllBrandsList();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        int brandSize = jsonPath.getList("brands").size();

        Assert.assertEquals("check brands size", 34, brandSize);
        Assert.assertEquals("check last brand id", 43, jsonPath.getInt("brands[" + (brandSize - 1) + "].id"));
    }

    @Test
    public void verifyThatBrandListWithPutMethod() {
        Response response = Helper.PUTToAllBrandsList();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("verify response code", 405, jsonPath.getInt("responseCode"));
        Assert.assertEquals("verify error message", "This request method is not supported.", jsonPath.getString("message"));
    }

    @Test
    public void verifyThatSearchedProduct() {
        Response response = Helper.POSTToSearchProduct();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("verify response code", 200, jsonPath.getInt("responseCode"));

        Assert.assertEquals("verify first product id", 1, jsonPath.getInt("products["+0+"].id"));

        for (int i=0; i<jsonPath.getList("products").size(); i++) {
            if (!(jsonPath.getString("products["+i+"].name")).toLowerCase().contains("top")) {
                System.out.println((jsonPath.getString("products["+i+"].name")));
            }/*
            Assert.assertTrue("verify searched product name", (jsonPath.getString("products["+i+"].name")).toLowerCase().contains("top"));
            System.out.println(jsonPath.getString("products["+i+"].name"));
            */
        }
    }

    @Test
    public void SearchedProductNegative() {
        Response response = Helper.SearchProductNegative();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("verify response code", 400, jsonPath.getInt("responseCode"));

        Assert.assertEquals("check message", "Bad request, search_product parameter is missing in POST request.", jsonPath.getString("message"));
    }

    @Test
    public void LoginFunctionPositiveTest() {
        Response response = Helper.verifyLoginFunctionPositive();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 200, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check message", "User exists!", jsonPath.getString("message"));
    }

    @Test
    public void LoginFunctionNegativeTest() {
        Response response = Helper.verifyLoginFunctionNegative();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 400, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check message", "Bad request, email or password parameter is missing in POST request.", jsonPath.getString("message"));
    }

    @Test
    public void LoginFunctionTestWithDeleteMethod() {
        Response response = Helper.verifyLoginFunctionWithDeleteMethod();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 405, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check message", "This request method is not supported.", jsonPath.getString("message"));
    }

    @Test
    public void CreateUserPositiveTest() {
        Response response = Helper.createUser();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 201, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check message", "User created!", jsonPath.getString("message"));
    }

    @Test
    public void GetDetailsOfUserTest() {
        Response response = Helper.getDetailsOfUser();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 200, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check email", ConfigurationReader.get("email"), jsonPath.getString("user.email"));
        Assert.assertEquals("check first name", Helper.firstName, jsonPath.getInt("user.first_name"));
        Assert.assertEquals("check first name", Helper.lastName, jsonPath.getInt("user.last_name"));
        Assert.assertEquals("check first name", Helper.companyName, jsonPath.getInt("user.company"));
    }

    @Test
    public void deleteAccountTest() {
        Response response = Helper.deleteAccount();

        response.then().assertThat().statusCode(200);

        JsonPath jsonPath = response.jsonPath();

        Assert.assertEquals("check response code", 200, jsonPath.getInt("responseCode"));
        Assert.assertEquals("check message", "Account deleted!", jsonPath.getString("message"));
    }

}
