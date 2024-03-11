package org.example;

import io.restassured.response.Response;
import org.constants.ConfigConstants;
import org.constants.TestConstants;
import org.model.ResponseUploadPhoto;
import org.model.SavedPhoto;
import org.pages.MyPage;
import org.requests.*;
import org.steps.MyPageSteps;
import org.storage.DataStorage;
import org.testng.annotations.Test;
import org.utils.ApiUtils;
import org.utils.StringGenerator;

import java.io.File;

public class VkApiTestCase extends BaseTest {
    MyPage myPage = new MyPage();
    MyPageSteps myPageSteps = new MyPageSteps();

    @Test(priority = 1)
    public void testCreatePostOnWall() {
        String randomMessage = StringGenerator.generate(TestConstants.LENGTH_STRING);
        DataStorage.setRandomMessage(randomMessage);

        Response responsePostText = RequestToWallPost.postTextOnWall(randomMessage);

        int postId = ApiUtils.getPostIdByResponse(responsePostText);
        DataStorage.setPostId(postId);

        String postText = myPage.getPostTextByPostId(postId);
        int postAuthorId = myPage.getPostAuthorIdByPostId(postId);

        myPageSteps.checkEqualTexts(postText, randomMessage);
        myPageSteps.checkEqualUsersId(postAuthorId, Integer.parseInt(System.getenv(ConfigConstants.ENV_OWNER_ID)));
    }

    @Test(priority = 2)
    public void testEditPostOnWall() {
        Response responseGetUploadServer = RequestToPhotosGetWallUploadServer.getUploadServer();
        String urlUploadServer = ApiUtils.getUrlUploadServerByResponse(responseGetUploadServer);

        File uploadPhoto = new File(TestConstants.PATH_TO_IMAGE);

        Response uploadPhotoResponse = RequestToUploadPhoto.uploadPhoto(uploadPhoto, urlUploadServer);

        ResponseUploadPhoto responseUploadPhoto = ApiUtils.returnObjectFromHtmlBody(uploadPhotoResponse, ResponseUploadPhoto.class);

        String photo = responseUploadPhoto.getPhoto();
        int server = responseUploadPhoto.getServer();
        String hash = responseUploadPhoto.getHash();

        Response responseSavePhoto = RequestToPhotosSaveWallPhoto.savePhotoOnServer(photo, server, hash);

        SavedPhoto savedPhoto = ApiUtils.getSavedPhotoByResponse(responseSavePhoto);

        int photoId = savedPhoto.getId();
        int ownerId = savedPhoto.getOwner_id();

        String newRandomMessage = StringGenerator.generate(TestConstants.LENGTH_STRING);

        int postId = DataStorage.getPostId();
        String randomMessage = DataStorage.getRandomMessage();

        RequestToWallEdit.editPostByPostId(postId, newRandomMessage, ownerId, photoId);

        int receivedPhotoId = myPage.getPostPhotoIdByPostId(postId);
        String receivedTextMessage = myPage.getPostTextByPostId(postId);

        myPageSteps.checkEqualPhotosId(receivedPhotoId, photoId);
        myPageSteps.checkNotEqualTexts(receivedTextMessage, randomMessage);
    }

    @Test(priority = 3)
    public void testCreateCommentOnPost() {
        int postId = DataStorage.getPostId();

        String randomCommentMessage = StringGenerator.generate(TestConstants.LENGTH_STRING);
        Response responseCreateComment = RequestToWallCreateComment.createCommentToPost(postId, randomCommentMessage);

        int commentId = ApiUtils.getCommentIdByResponse(responseCreateComment);

        myPageSteps.checkHasCommentsOnPostByPostId(postId);

        int receivedCommentAuthorId = myPageSteps.getPostCommentAuthorId(postId, commentId);
        myPageSteps.checkEqualUsersId(receivedCommentAuthorId, Integer.parseInt(System.getenv(ConfigConstants.ENV_OWNER_ID)));
    }

    @Test(priority = 4)
    public void testCreateLikeOnPost() {
        int postId = DataStorage.getPostId();

        myPage.likePostByPostId(postId);

        ApiUtils.checkLikeOnPostFromUserId(postId, Integer.parseInt(System.getenv(ConfigConstants.ENV_OWNER_ID)));
    }

    @Test(priority = 5)
    public void testDeletePost() {
        int postId = DataStorage.getPostId();

        RequestToWallDelete.deletePostByPostId(postId);

        myPageSteps.checkDeletePostByPostId(postId);
    }
}

