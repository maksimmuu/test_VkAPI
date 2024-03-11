package org.steps;

import aquality.selenium.elements.interfaces.ILabel;
import org.constants.HtmlAttribute;
import org.pages.MyPage;
import org.testng.Assert;

import java.util.List;

public class MyPageSteps {
    private final MyPage myPage;

    public MyPageSteps() {
        myPage = new MyPage();
    }

    public int getPostCommentAuthorId(int postId, int commentId) {
        List<ILabel> listAllCommentsToPost = myPage.getListAllCommentsToPostByPostId(postId);
        int authorId = 0;

        for (ILabel comment : listAllCommentsToPost) {
            if (myPage.getAttributeElement(comment, HtmlAttribute.DATA_POST_ID).contains(String.valueOf(commentId))) {
                authorId = Integer.parseInt(myPage.getAttributeElement(comment, HtmlAttribute.ANSWERING_ID));
            }
        }
        return authorId;
    }

    public void checkHasCommentsOnPostByPostId(int postId) {
        myPage.openCommentsToPostByPostId(postId);
        List<ILabel> listAllPostsComments = myPage.getListAllCommentsToPostByPostId(postId);
        Assert.assertFalse(listAllPostsComments.isEmpty(), String.format("На посте с postId = '%d' нет комментариев", postId));
    }

    public void checkEqualTexts(String actualText, String expectedText) {
        Assert.assertEquals(actualText, expectedText,
                String.format("Полученный текст '%s' не совпадает с ожидаемым текстом '%s'", actualText, expectedText));
    }

    public void checkNotEqualTexts(String actualText, String expectedText) {
        Assert.assertNotEquals(actualText, expectedText,
                String.format("Полученный текст '%s' совпадает с ожидаемым текстом '%s'", actualText, expectedText));
    }

    public void checkEqualUsersId(int actualUserId, int expectedUserId) {
        Assert.assertEquals(actualUserId, expectedUserId,
                String.format("Полученный userId = '%d' не совпадает с ожидаемым userId = '%d'", actualUserId, expectedUserId));
    }

    public void checkEqualPhotosId(int actualPhotoId, int expectedPhotoId) {
        Assert.assertEquals(actualPhotoId, expectedPhotoId,
                String.format("Полученный photoId = '%d' не совпадает с ожидаемым photoId = '%d'", actualPhotoId, expectedPhotoId));
    }

    public void checkDeletePostByPostId(int postId) {
        ILabel post = myPage.getPostByPostId(postId);
        boolean postIsDeleted = myPage.elementIsNotDisplayed(post);
        Assert.assertTrue(postIsDeleted, String.format("Пост с postId = '%d' не удален", postId));
    }
}
