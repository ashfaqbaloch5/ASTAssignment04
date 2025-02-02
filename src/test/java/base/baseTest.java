package base;

import config.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class baseTest {
    protected RequestSpecification request;

    public baseTest() {
        RestAssured.baseURI = ConfigReader.getProperty("baseURL");
        request = RestAssured.given()
                .header("Content-Type", "application/json");
    }
}
