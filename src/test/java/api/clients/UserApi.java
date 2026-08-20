package api.clients;

import api.constants.Api;
import api.models.CreateUserResponse;
import api.models.UserCreds;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static api.constants.Api.API_REGISTER;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.CoreMatchers.notNullValue;

public class UserApi {

    static { RestAssured.baseURI = Api.BASE_URL; }

    public static ValidatableResponse createUserReturnValidatableResponse(UserCreds userCreds) {
        return given()
                .contentType(JSON)
                .and()
                .body(userCreds)
                .when()
                .post(API_REGISTER)
                .then();
    }

    public static CreateUserResponse createUserReturnResponse(UserCreds userCreds) {
        return createUserReturnValidatableResponse(userCreds)
                .statusCode(HttpStatus.SC_CREATED)
                .body("access_token.access_token", notNullValue())
                .extract()
                .as(CreateUserResponse.class);
    }

    public static String createUserReturnToken(UserCreds userCreds) {
        return createUserReturnResponse(userCreds)
                .getAccessToken()
                .getAccessToken();
    }

    public static void createUser(UserCreds userCreds) {
        createUserReturnValidatableResponse(userCreds)
                .statusCode(HttpStatus.SC_CREATED)
                .body("access_token.access_token", notNullValue());
    }
}
