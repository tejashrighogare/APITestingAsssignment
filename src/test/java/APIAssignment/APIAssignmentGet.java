package APIAssignment;

import dataProvider.*;
import io.restassured.*;
import io.restassured.path.json.*;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

import java.util.logging.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.log4j.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class APIAssignmentGet {
    private String productListURL = "";
    private Logger logger = Logger.getLogger(APIAssignmentGet.class.getName());

    @Test(priority = 1)
    public void validateStatusCode() {
        productListURL = ConfigReader.getURL("baseURL");
        Response responseVariable = get(productListURL);
        var statusCodeValue = responseVariable.getStatusCode();
        Assert.assertEquals(200, statusCodeValue);
        var statusLine = responseVariable.getStatusLine();
        Assert.assertEquals("HTTP/1.1 200 OK", statusLine);
        logger.info("status code is " + statusCodeValue);
        logger.info("status line is " + statusLine);
    }

    @Test(priority = 2)
    public void validateList() {
        productListURL = ConfigReader.getURL("baseURL");
        var getALLProductList = given().when().get(productListURL).then().log().all().toString();
        logger.info("List return by API is" + getALLProductList);
    }

    @Test(priority = 3)
    public void validateContent() {
        productListURL = ConfigReader.getURL("baseURL");
        Response response = RestAssured.given().when().get(productListURL);
        JsonPath jsonResponse = new JsonPath(response.asString());
        logger.info("my json response: " + jsonResponse.get("products"));
        var productsArray = jsonResponse.get("products");
        var idLength = jsonResponse.getInt("products.id.size()");
        var productName = ConfigReader.getURL("productName");
        var price = ConfigReader.getURL("price");
        var brand = ConfigReader.getURL("brand");
        var category = ConfigReader.getURL("category");
        var currentProductName = "";
        for (var counter = 0; counter < productsArray.toString().length(); counter++) {
            currentProductName = jsonResponse.getString("products[" + counter + "].name");
            if (currentProductName.contentEquals("Men Tshirt")) {
                Assert.assertEquals(currentProductName, productName);
                Assert.assertEquals(jsonResponse.getString("products[" + counter + "].price"), price);
                Assert.assertEquals(jsonResponse.getString("products[" + counter + "].brand"), brand);
                Assert.assertEquals(jsonResponse.getString("products[" + counter + "].category.category"), category);
                break;
            }
        }
    }

    @Test(priority = 4)
    public void validateLength() {
        var productListURL = ConfigReader.getURL("baseURL");
        var response = given()
                .when().get(productListURL).then().extract().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        Assert.assertEquals(34, idLength);
        logger.info("Length is " + idLength);
    }
}