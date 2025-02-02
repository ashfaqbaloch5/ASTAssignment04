package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class L1F21S6_API {
    int bkID;
    String Token;
    @Test
    public void pingAPITest(){

        Response pingTestResp = RestAssured.get("https://restful-booker.herokuapp.com/ping");
        Assert.assertEquals(pingTestResp.getStatusCode(),201,"wrong status code");
        pingTestResp.print();

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
    public void CreateTokenTest() {
        JSONObject userInfo = new JSONObject();
        userInfo.put("username", "admin");
        userInfo.put("password", "password123");

         Token = RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .header("Content-Type", "application/json")
                    .body(userInfo.toString())
                .when()
                    .post("/auth")
                .then()
                     .statusCode(200)

                        .extract().body().jsonPath().get("token");
    System.out.println("Generated token is "+ Token);
    }

    @Test(priority = 1)
    public void CreateBookingTest(){
        JSONObject bookingInfo=new JSONObject();
        JSONObject bkDate =new JSONObject();
        bookingInfo.put("firstname","uswah");
        bookingInfo.put("lastname","alvi");
        bookingInfo.put("totalprice",111);
        bookingInfo.put("depositpaid",true);
            bkDate.put("checkin" , "2018-01-01");
            bkDate.put("checkout" , "2019-01-01");
            bookingInfo.put("bookingdates",bkDate);
        bookingInfo.put("additionalneeds" , "Breakfast");

        bkID=RestAssured
                .given()
                   .baseUri("https://restful-booker.herokuapp.com")
                   .header("Content-Type", "application/json")
                   .body(bookingInfo.toString())

                .when()
                .post("/booking")
                .then()
                .extract().body().jsonPath().get("bookingid");
                //.extract().response().prettyPrint();

           System.out.println("Bokking ID : "+bkID);
    }
     @Test(priority = 2)
    public void updateBookingTest(){
        JSONObject upBookingInfo =new JSONObject();
        JSONObject upBkDate =new JSONObject();
        upBookingInfo.put("firstname","sarmad");
        upBookingInfo.put("lastname","ali");
        upBookingInfo.put("totalprice",111);
        upBookingInfo.put("depositpaid",true);
        upBkDate.put("checkin" , "2018-01-01");
        upBkDate.put("checkout" , "2019-01-01");
        upBookingInfo.put("bookingdates", upBkDate);
        upBookingInfo.put("additionalneeds" , "Breakfast");

        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                 .header("Accept", "application/json")
                 .header("Cookie" ,"token="+Token)
                //.auth().preemptive().basic("admin","password123")
                .body(upBookingInfo.toString())

                .when()
                .put("/booking/"+bkID)
                .then()
                .body("firstname",equalTo("sarmad"))
                //.extract().body().jsonPath().get("bookingid");
                .extract().response().prettyPrint();

        System.out.println("Bo0king ID : "+bkID);


    }


    @Test(priority = 3)
    public void deleteBookingTest(){
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com")
                .header("Content-Type", "application/json")
                .header("Cookie" ,"token="+Token)
                .when()
                .delete("/booking/" + bkID)
                .then()
                .statusCode(201);

        System.out.println("Booking deleted with ID: " + bkID);
    }
//    @Test(priority = 4)
//    public void getBookingDetailsTest(){
//        Response response = RestAssured
//                .given()
//                .baseUri("https://restful-booker.herokuapp.com")
//                .when()
//                .get("/booking/" + bkID)
//                .then()
//                .statusCode(200)
//                .extract().response();
//
//        response.prettyPrint();
//    }



}
