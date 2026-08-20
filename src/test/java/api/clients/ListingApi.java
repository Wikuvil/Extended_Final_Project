package api.clients;

import api.constants.Api;
import api.models.ListingData;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import java.nio.charset.StandardCharsets;

import static api.constants.Api.*;
import static io.restassured.RestAssured.given;

public class ListingApi {

    static {
        RestAssured.baseURI = Api.BASE_URL;
    }

    public static ValidatableResponse createListingReturnValidatableResponse(ListingData listingData, String token) {
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();

        String bodyTemplate =
                "--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"name\"\r\n\r\n" + listingData.getName() + "\r\n" +
                        "--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"category\"\r\n\r\n" + listingData.getCategory() + "\r\n" +
                        "--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"condition\"\r\n\r\n" + listingData.getCondition() + "\r\n" +
                        "--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"city\"\r\n\r\n" + listingData.getCity() + "\r\n" +
                        "--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"description\"\r\n\r\n" + listingData.getDescription() + "\r\n" +
                        "--" + boundary + "\r\n" +
                        "Content-Disposition: form-data; name=\"price\"\r\n\r\n" + listingData.getPrice() + "\r\n" +
                        "--" + boundary + "--\r\n";

        return given()
                .header("Authorization", "Bearer " + token)
                .contentType("multipart/form-data; boundary=" + boundary)
                .body(bodyTemplate.getBytes(StandardCharsets.UTF_8))
                .post(API_CREATE_LISTING)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    public static void deleteListing(String token, String listingId) {
        given()
                .header("Authorization", "Bearer " + token)
                .delete(API_DELETE_LISTING + listingId);
    }

    public static String getListingIdByName(String listingName) {
        Object id = given()
                .queryParam("name", listingName)
                .get(API_SEARCH_LISTING)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .path("offers[0].id");
        return id != null ? id.toString() : null;
    }
}