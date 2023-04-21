package APIAssignment;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import io.restassured.specification.*;
import org.junit.*;

import java.lang.invoke.*;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;


public class APIAssignmentGet {
    @Test
    public  void getAllProductList() {
        var productListURL="https://automationexercise.com/api/productsList";
        Response responseVariable = get(productListURL);
        System.out.println("Status code is " + responseVariable.getStatusCode());
        System.out.println("Response body is " + responseVariable.getBody().asString());
        System.out.println("Content type is " + responseVariable.getHeader("content-type"));

        // validate status code
        var statusCodeValue = responseVariable.getStatusCode();
        given().get("https://automationexercise.com/api/productsList").then().statusCode(200);
        Assert.assertEquals(200, statusCodeValue);

//validate content of list
        RestAssured.baseURI=productListURL;
        RequestSpecification specification=RestAssured.given();
        Response response=specification.request(Method.GET);
        ResponseBody body= response.body();
        var bodyContent=body.asString();
        Assert.assertEquals(bodyContent.contains("id"),true);
        System.out.println("All Product List present in my response");

        //Validate List returned by API
        var getALLProductList = given().when().get(productListURL).then().log().all().toString();
        System.out.println(getALLProductList);

       // Validate Length
        var productList = JSON. parse(responseVariable.toString())
        System.out.println("ProductList"+productList);
       /* for (var i = 0; i < productList.; i++) {
            var ctr=0;
            for (attr in obj[i]) ctr++;
            alert('array['+i+']: ' +ctr);
         Var resultCount = $.result.length()*/

        }

    }

