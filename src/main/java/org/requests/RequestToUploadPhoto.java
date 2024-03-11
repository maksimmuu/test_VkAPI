package org.requests;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.constants.ConfigConstants;
import org.constants.JsonConstants;

import java.io.File;

public class RequestToUploadPhoto extends BaseRequest {
    public static Response uploadPhoto(File photo, String urlUploadServer) {
        return extractResponseWithLogs(givenWithLogs()
                .contentType(ContentType.MULTIPART)
                .multiPart(JsonConstants.FIELD_PHOTO, photo, ConfigConstants.MULTIPART_FORMAT)
                .post(urlUploadServer));
    }
}
