package APIAssignment;

import dataProvider.*;
import io.restassured.*;
import io.restassured.path.json.*;
import io.restassured.response.*;

import static io.restassured.RestAssured.*;

import java.util.*;

import org.slf4j.*;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

public class APIAssignmentGet {

    private String productListURL = "";
    private Logger logger = LoggerFactory.getLogger(APIAssignmentGet.class);

    @Test(priority = 1)
    public void validateStatusCode() {
        productListURL = ConfigReader.getPropertyValue("baseURL");
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
        productListURL = ConfigReader.getPropertyValue("baseURL");
        var getALLProductList = given().when().get(productListURL).then().log().all().toString();
        logger.info("List return by API is" + getALLProductList);
    }

    @Test(priority = 3)
    public void validateContent() {
        productListURL = ConfigReader.getPropertyValue("baseURL");
        Response response = RestAssured.given().when().get(productListURL);
        JsonPath jsonResponse = new JsonPath(response.asString());
        logger.info("my json response: " + jsonResponse.get("products"));
        List<Product> productsArray = jsonResponse.get("products");
        List<Product> getProductData = new ArrayList<>();
        var productId = ConfigReader.getPropertyValue("productId");
        var productName = ConfigReader.getPropertyValue("productName");
        var productPrice = ConfigReader.getPropertyValue("price");
        var productBand = ConfigReader.getPropertyValue("brand");
        Product objectProduct = new Product(productId, productName, productPrice, productBand);
        getProductData.add(objectProduct);
        for (int index = 0; index < productsArray.size(); index++) {
            String id = jsonResponse.getString("products[" + index + "].id");
            String name = jsonResponse.getString("products[" + index + "].name");
            String price = jsonResponse.getString("products[" + index + "].price");
            String brand = jsonResponse.getString("products[" + index + "].brand");
            getProductData.forEach(displayList -> {
                if (name.equals(displayList.productName)) {
                    logger.info(displayList.productId);
                    logger.info(displayList.productName);
                    logger.info(displayList.productBrand);
                    logger.info(displayList.productPrice);
                    Assert.assertEquals(displayList.productId, id);
                    Assert.assertEquals(displayList.productName, name);
                    Assert.assertEquals(displayList.productPrice, price);
                    Assert.assertEquals(displayList.productBrand, brand);
                }
            });
        }
    }

    @Test(priority = 4)
    public void validateLength() {
        var productListURL = ConfigReader.getPropertyValue("baseURL");
        var response = given()
                .when().get(productListURL).then().extract().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        Assert.assertEquals(34, idLength);
        logger.info("Length is " + idLength);
    }
}