package org.requests;

import io.restassured.response.Response;

public class RequestToLikesGetList extends BaseRequest {
    private static final String URL_LIKES_GET_LIST = getUrlForRequest("likes.getList");

    public static Response getListPostsLikes(int postId) {
        return extractResponseWithLogs(getRequest(URL_LIKES_GET_LIST + "&type=post" + "&item_id=" + postId));
    }
}
