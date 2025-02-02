package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class Test1
{
//    @Test
//    public void addandFetchpet()
//    {
//        //Base URL
//        RestAssured.baseURI=  "https://petstore.swagger.io/v2";
//
//        //step1 Add New Pet
//        JSONObject requestParams= new JSONObject();
//        requestParams.put("id",12345);
//        requestParams.put("name","Tommy");
//        requestParams.put("status","available");
//
//        Response postResponse =RestAssured.given()
//                .header("Content-Type","application/json")
//                .body(requestParams.toString())
//                .when()
//                .post("/pet");
//        postResponse.then().statusCode(200);
//
//        //step 2 fetch pet
//        Response getResponse =RestAssured.given()
//                .queryParam("status","available")
//                .when()
//                .get("/pet/findByStatus");
//        getResponse.then().statusCode(200);
//
//        //step 3 validate added pet exists
//        getResponse.then().body("find { it.id == 12345 }.name", equalTo("Tommy"));
//    }
    int bkid;
    String Token;

    @Test
    public void pingAPITest()
    {
        Response pingTestRespone=RestAssured.get("https://restful-booker.herokuapp.com/ping");
        Assert.assertEquals(pingTestRespone.getStatusCode(),201,"wrong status code");
        pingTestRespone.print();

        RestAssured
                .given()
                  .baseUri("https://restful-booker.herokuapp.com")
                .when()
                    .get("/ping")
                .then()
                .statusCode(201)
                .extract().response().print();

    }
    @Test
    public void CreateTokenTest()
    {
        JSONObject userInfo =new JSONObject();
        userInfo.put("username","admin");
        userInfo.put("password","password123");

        Token =RestAssured
                .given()
                   .baseUri("https://restful-booker.herokuapp.com")
                   .header("Content-Type","application/json")
                   .body(userInfo.toString())
                .when()
                    .post("/auth")
                .then()
                     .statusCode(200)
                .extract().body().jsonPath().get("token");
        System.out.println("Generated token is "+ Token);
    }

    @Test(priority = 1)
    public void CreateBookingTest() {
        JSONObject bookingInfo = new JSONObject();
        JSONObject bkdate = new JSONObject();
        bookingInfo.put("firstname", "uswah");
        bookingInfo.put("lastname", "alvi");
        bookingInfo.put("totalprice", 111);
        bookingInfo.put("depositpaid", true);

        bkdate.put("checkin", "2018-01-01");
        bkdate.put("checkout", "2019-01-01");
        // Insert the JSON object rather than a string literal
        bookingInfo.put("bookingdates", bkdate);

        bookingInfo.put("additionalneeds", "Breakfast");
        System.out.println("Payload: " + bookingInfo.toString());

        int bkid = RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .body(bookingInfo.toString())
                .when()
                .post("/booking")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().get("bookingid");

        System.out.println("Booking ID: " + bkid);
    }


}
