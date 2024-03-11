package org.requests;

import io.restassured.response.Response;

public class RequestToWallCreateComment extends BaseRequest {
    private static final String URL_WALL_CREATE_COMMENT = getUrlForRequest("wall.createComment");

    public static Response createCommentToPost(int postId, String message) {
        return extractResponseWithLogs(postRequest(URL_WALL_CREATE_COMMENT + "&post_id=" + postId + "&message=" + message));
    }
}
