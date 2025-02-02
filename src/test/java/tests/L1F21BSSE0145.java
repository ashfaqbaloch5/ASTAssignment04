package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.APIPage;

public class L1F21BSSE0145 {
    private APIPage apiPage;

    @BeforeClass

    public void setup() {
        apiPage = new APIPage();
    }

    @Test(priority = 1)
    public void testcreate() {
        Response response = apiPage.createUser(2, "Ahsan", "Software testing");

        System.out.println("create user response :"+ response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201, "error msg");
        Assert.assertEquals(response.jsonPath().getString("name"), "Ahsan");
        Assert.assertEquals(response.jsonPath().getString("job"), "software testing");
        Assert.assertEquals(response.jsonPath().getString("id"), "0145");
    }

    @Test(priority = 2)
    public void updatetest() {
        Response response = apiPage.createUser(2, "Ahsan Raza", "senario");

        System.out.println("update user response :"+ response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Ahsan Raza");
        Assert.assertEquals(response.jsonPath().getString("job"), "senario");

    }
}

