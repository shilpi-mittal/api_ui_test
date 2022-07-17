package api_tests.utils;

import io.restassured.response.Response;
import org.apache.http.HttpHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.restassured.RestAssured.given;

import constants.*;

// holds utils to help call APIs
public class RestUtils {
    private static final Logger LOG = LoggerFactory.getLogger(RestUtils.class);

    // returns base url
    public static String getBaseUrl() {
        return EnvConstants.PROTOCOL + EnvConstants.HOSTNAME;
    }

    // makes get call on given path, with CONTENT_TYPE header, and returns Response
    public static Response getRequest(String path) {
        Response response =
                given()
                        .header(HttpHeaders.CONTENT_TYPE, TestConstants.CONTENT_TYPE)
                        .param("postId", "2")
                        .when()
                        .get(path)
                        .then()
                        .extract().response();
        LOG.debug("Response received: " + response.asString());
        return response;
    }

    // makes get call on given path, with CONTENT_TYPE header and given query param key value pair, and returns Response
    public static Response getRequestWithParams(String path, String paramKey, String paramValue) {
        Response response =
                given()
                        .header(HttpHeaders.CONTENT_TYPE, TestConstants.CONTENT_TYPE)
                        .param(paramKey, paramValue)
                        .when()
                        .get(path)
                        .then()
                        .extract().response();
        LOG.debug("Response received: " + response.asString());
        return response;
    }

    // makes POST call on given path, with CONTENT_TYPE header and given body, and returns Response
    public static Response postRequest(String path, String requestBody) {
        Response response =
                given()
                        .header(HttpHeaders.CONTENT_TYPE, TestConstants.CONTENT_TYPE)
                        .and()
                        .body(requestBody)
                        .when()
                        .post(path)
                        .then()
                        .extract().response();
        LOG.debug("Response received: " + response.asString());
        return response;
    }
}
