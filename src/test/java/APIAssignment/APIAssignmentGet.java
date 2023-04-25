package APIAssignment;

import dataProvider.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

import io.restassured.specification.*;

import java.util.logging.*;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APIAssignmentGet {
    private String productListURL = "";
    private Logger logger = Logger.getLogger(APIAssignmentGet.class.getName());

    @Test(priority = 1)
    public void validateStatusCode() {
        productListURL = ConfigReader.getURL();
        Response responseVariable = get(productListURL);
        var statusCodeValue = responseVariable.getStatusCode();
        Assert.assertEquals(200, statusCodeValue);
        var statusLine = responseVariable.getStatusLine();
        logger.log(Level.INFO, "get status line " + statusLine);
        Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
    }

    @Test(priority = 2)
    public void validateList() {
        productListURL = ConfigReader.getURL();
        var getALLProductList = given().when().get(productListURL).then().log().all().toString();
        logger.log(Level.INFO, "List return by API is " + getALLProductList);

    }

    @Test(priority = 3)
    public void validateContent() {
        productListURL = ConfigReader.getURL();
        RestAssured.baseURI = productListURL;
        RequestSpecification specification = RestAssured.given();
        Response response = specification.request(Method.GET);
        ResponseBody body = response.body();
        var bodyContent = body.asString();
        var productName = response.jsonPath().get("products[3].name").toString();
        var productPrice = response.jsonPath().get("products[3].price").toString();
        var productBrand = response.jsonPath().get("products[3].brand").toString();
        Assert.assertEquals(productName, "Stylish Dress");
        Assert.assertEquals(productPrice, "Rs. 1500");
        Assert.assertEquals(productBrand, "Madame");
        logger.log(Level.INFO, "Product name,price,brand is present in list " + productName + productPrice + productBrand);
    }

    @Test(priority = 4)
    public void validateLength() {
        productListURL = ConfigReader.getURL();
        var response = given()
                .when().get(productListURL).then().extract().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        Assert.assertEquals(34, idLength);
        logger.log(Level.INFO, "Length is " + idLength);
    }
}