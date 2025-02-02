package pages;

import base.baseTest;
import io.restassured.response.Response;
import org.json.JSONObject;

public class APIPage extends baseTest {

    public Response createUser(int i, String name, String job) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("job", job);

        return request.body(requestBody.toString())
                .when()
                .post("/api/users");
    }

    public Response updateUser(int userId, String name, String job) {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("job", job);

        return request.body(requestBody.toString())
                .when()
                .put("/api/users/" + userId);
    }
}
