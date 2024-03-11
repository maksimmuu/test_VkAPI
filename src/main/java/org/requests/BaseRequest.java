package org.requests;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.constants.ConfigConstants;

import static io.restassured.RestAssured.given;

public abstract class BaseRequest {
    protected static String getUrlForRequest(String sectionUrl) {
        return String.format("%s?access_token=%s&v=%s", sectionUrl, System.getenv(ConfigConstants.ENV_TOKEN), ConfigConstants.VERSION_API);
    }

    private static void installSpecification() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri(ConfigConstants.API_URL)
                .setContentType(ContentType.JSON)
                .build();
    }

    protected static RequestSpecification givenWithLogs() {
        installSpecification();
        return given().when().log().all();
    }

    protected static Response postRequest(String url) {
        return givenWithLogs().post(url);
    }

    protected static Response getRequest(String url) {
        return givenWithLogs().get(url);
    }

    protected static Response extractResponseWithLogs(Response response) {
        return response.then().log().all().extract().response();
    }
}
