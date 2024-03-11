package org.requests;

import io.restassured.response.Response;

public class RequestToWallPost extends BaseRequest {
    private static final String URL_WALL_POST = getUrlForRequest("wall.post");

    public static Response postTextOnWall(String message) {
        return extractResponseWithLogs(postRequest(URL_WALL_POST + "&message=" + message));
    }
}
