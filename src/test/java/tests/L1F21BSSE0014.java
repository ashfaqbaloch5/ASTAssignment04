package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.APIPage;

    public class L1F21BSSE0014 {
    private APIPage apiPage;

    @BeforeClass
    public void setup() {
        apiPage = new APIPage();
    }

    @Test(priority = 1)
    public void testCreateUser() {
        Response response = apiPage.createUser(2, "Ashfaq", "QA Engineer");

        // Print response for debugging
        System.out.println("Create User Response: " + response.getBody().asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.jsonPath().getString("name"), "Ashfaq");
        Assert.assertEquals(response.jsonPath().getString("job"), "QA Engineer");
        Assert.assertNotNull(response.jsonPath().getString("id"));
    }

    @Test(priority = 2)
    public void testUpdateUser() {
        Response response = apiPage.updateUser(2, "Ashfaq Updated", "Senior QA Engineer");

        // Print response for debugging
        System.out.println("Update User Response: " + response.getBody().asString());

        // Assertions
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getString("name"), "Ashfaq Updated");
        Assert.assertEquals(response.jsonPath().getString("job"), "Senior QA Engineer");
    }
}
