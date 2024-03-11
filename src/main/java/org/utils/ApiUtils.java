package org.utils;

import com.google.gson.Gson;
import org.constants.JsonConstants;
import org.constants.TestConstants;
import org.model.*;
import org.testng.Assert;
import io.restassured.response.Response;
import org.constants.ResponseAttribute;
import org.requests.RequestToLikesGetList;

import java.util.List;

public class ApiUtils {
    public static int getPostIdByResponse(Response response) {
        return returnObjectFromResponse(response, ResponseWallPost.class).getResponse().getPost_id();
    }

    public static int getCommentIdByResponse(Response response) {
        return returnObjectFromResponse(response, ResponseCreateComment.class).getResponse().getComment_id();
    }

    public static String getUrlUploadServerByResponse(Response response) {
        return returnObjectFromResponse(response, ResponsePhotosUploadServer.class).getResponse().getUpload_url();
    }

    public static SavedPhoto getSavedPhotoByResponse(Response response) {
        return returnObjectFromResponse(response, ResponseSavePhoto.class).getResponse().get(TestConstants.INDEX_0);
    }

    private static <T> T returnObjectFromResponse(Response response, Class<T> classOfT) {
        return response.as(classOfT);
    }

    public static <T> T returnObjectFromHtmlBody(Response HtmlResponse, Class<T> classOfT) {
        String body = HtmlResponse.then().contentType(ResponseAttribute.CONTENT_TYPE_HTML)
                .extract().body().htmlPath().getString(ResponseAttribute.PATH_HTML);
        Gson gson = new Gson();
        return gson.fromJson(body, classOfT);
    }

    public static void checkLikeOnPostFromUserId(int postId, int userId) {
        List<Integer> likesOfThePost = RequestToLikesGetList.getListPostsLikes(postId).jsonPath().getList(JsonConstants.PATH_TO_ITEMS);
        Assert.assertNotNull(likesOfThePost.stream().filter(x -> x == userId).findFirst().orElse(null),
                String.format("Нет лайка на посте с postId = '%d' от проверяемого пользователя с userId = '%d'", postId, userId));
    }
}
