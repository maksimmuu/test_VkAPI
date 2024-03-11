package org.requests;

import io.restassured.response.Response;

public class RequestToPhotosGetWallUploadServer extends BaseRequest {
    private static final String URL_PHOTOS_GET_UPLOAD_SERVER = getUrlForRequest("photos.getWallUploadServer");

    public static Response getUploadServer() {
        return extractResponseWithLogs(getRequest(URL_PHOTOS_GET_UPLOAD_SERVER));
    }
}
