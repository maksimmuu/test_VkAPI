package org.requests;

import io.restassured.response.Response;

public class RequestToWallEdit extends BaseRequest {
    private static final String URL_WALL_EDIT = getUrlForRequest("wall.edit");

    public static Response editPostByPostId(int postId, String message, int idOwner, int idPhoto) {
        return extractResponseWithLogs(postRequest(String.format(URL_WALL_EDIT + "&post_id=%d&message=%s&attachments=photo%d_%d", postId, message, idOwner, idPhoto)));
    }
}
