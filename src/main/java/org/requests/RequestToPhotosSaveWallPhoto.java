package org.requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.constants.JsonConstants;

public class RequestToPhotosSaveWallPhoto extends BaseRequest {
    private static final String URL_PHOTOS_SAVE_WALL_PHOTO = getUrlForRequest("photos.saveWallPhoto");

    public static Response savePhotoOnServer(String photo, int server, String hash) {
        return extractResponseWithLogs(givenWithLogs()
                .contentType(ContentType.MULTIPART)
                .multiPart(JsonConstants.FIELD_PHOTO, photo)
                .multiPart(JsonConstants.FIELD_SERVER, server)
                .multiPart(JsonConstants.FIELD_HASH, hash)
                .post(URL_PHOTOS_SAVE_WALL_PHOTO));
    }
}
