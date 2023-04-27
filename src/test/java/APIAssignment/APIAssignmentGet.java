package APIAssignment;

import dataProvider.*;
import io.restassured.*;
import io.restassured.path.json.*;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

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
        logger.log(Level.INFO, "status code is " + statusCodeValue);

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
        productListURL = ConfigReader.getURL();
        Response response = RestAssured.given().when().get(productListURL);
        JsonPath jsonResponse = new JsonPath(response.asString());
        logger.info("my json response: " + jsonResponse.get("products"));
        var productsArray = jsonResponse.get("products");
        var idLength = jsonResponse.getInt("products.id.size()");
        for (int counter = 0; counter < productsArray.toString().length(); counter++) {
            String productName = jsonResponse.getString("products[" + counter + "].name");
            if (productName.contentEquals("Men Tshirt")) {
                Assert.assertEquals(productName, "Men Tshirt");
                Assert.assertEquals(jsonResponse.getString("products[" + counter + "].price"), "Rs. 400");
                Assert.assertEquals(jsonResponse.getString("products[" + counter + "].brand"), "H&M");
                Assert.assertEquals(jsonResponse.getString("products[" + counter + "].category.category"), "Tshirts");
                logger.log(Level.INFO, "Product name is  " + productName);
                break;
            }
        }
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