package api.clients;

import api.constants.Api;
import api.models.ListingData;
import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;

import static api.constants.Api.*;
import static io.restassured.RestAssured.given;

public class ListingApi {

    static {
        RestAssured.baseURI = Api.BASE_URL;
    }

    public static ValidatableResponse createListingReturnValidatableResponse(ListingData listingData, String token) {
        String boundary = "----WebKitFormBoundary" + System.currentTimeMillis();
        return given()
                .header("Authorization", "Bearer " + token)
                .header("accept-language", "ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7")
                .header( "accept-encoding", "gzip, deflate, br, zstd")
                .header("accept", "application/json, text/plain, */*")
                .contentType("multipart/form-data; boundary=" + boundary)
                .multiPart(new MultiPartSpecBuilder(listingData.getName()).controlName("name").mimeType("text/plain") .charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder(listingData.getCategory()).controlName("category").mimeType("text/plain") .charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder(listingData.getCondition()).controlName("condition").mimeType("text/plain") .charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder(listingData.getCity()).controlName("city").mimeType("text/plain") .charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder(listingData.getDescription()).controlName("description").mimeType("text/plain") .charset("UTF-8").build())
                .multiPart(new MultiPartSpecBuilder(listingData.getPrice()).controlName("price").mimeType("text/plain") .charset("UTF-8").build())
                .post(API_CREATE_LISTING)
                .then()
                .log().all()
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