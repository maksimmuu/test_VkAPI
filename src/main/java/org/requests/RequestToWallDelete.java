package org.requests;

import io.restassured.response.Response;

public class RequestToWallDelete extends BaseRequest {
    private static final String URL_WALL_DELETE = getUrlForRequest("wall.delete");

    public static Response deletePostByPostId(int postId) {
        return extractResponseWithLogs(getRequest(URL_WALL_DELETE + "&post_id=" + postId));
    }
}
