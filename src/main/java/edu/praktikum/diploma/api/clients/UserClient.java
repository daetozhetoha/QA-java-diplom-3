package edu.praktikum.diploma.api.clients;

import edu.praktikum.diploma.api.models.User;
import edu.praktikum.diploma.api.models.UserCreds;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String API_AUTH_REGISTER = "api/auth/register";
    private static final String API_AUTH_LOGIN = "api/auth/login";
    private static final String API_AUTH_USER = "api/auth/user";

    public Response create(User user) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post(API_AUTH_REGISTER);
    }

    public Response login(UserCreds userCreds) {
        return given()
                .header("Content-type", "application/json")
                .and()
                .body(userCreds)
                .when()
                .post(API_AUTH_LOGIN);
    }

    public Response delete(String token) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", token)
                .when()
                .delete(API_AUTH_USER );
    }
}
