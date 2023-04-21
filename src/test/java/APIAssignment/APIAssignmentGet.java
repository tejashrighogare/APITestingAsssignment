package APIAssignment;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.path.json.*;
import io.restassured.response.*;
import static io.restassured.RestAssured.*;
import io.restassured.specification.*;
import org.junit.*;

public class APIAssignmentGet {
    @Test
    public void getAllProductList() {
        var productListURL = "https://automationexercise.com/api/productsList";
        Response responseVariable = get(productListURL);
        // validate status code
        var statusCodeValue = responseVariable.getStatusCode();
        Assert.assertEquals(200, statusCodeValue);
        System.out.println("status code is "+statusCodeValue);
        //validate content of list
        RestAssured.baseURI = productListURL;
        RequestSpecification specification = RestAssured.given();
        Response response = specification.request(Method.GET);
        ResponseBody body = response.body();
        var bodyContent = body.asString();
        Assert.assertEquals(bodyContent.contains("name"), true);
        System.out.println("name present in my response");
        //Validate List returned by API
        var getALLProductList = given().when().get(productListURL).then().log().all().toString();
        System.out.println(getALLProductList);
        // Validate Length
        JsonPath jsonResponse = new JsonPath(response.asString());
        var idLength = jsonResponse.getInt("products.id.size()");
        System.out.println("The length is : " + idLength);
    }
}