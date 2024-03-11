package org.pages;

import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILabel;
import org.constants.ConfigConstants;
import org.constants.HtmlAttribute;
import org.constants.RegExPatterns;
import org.openqa.selenium.By;
import org.utils.RegExUtils;

import java.util.List;

public class MyPage extends BasePage {
    private final String postFlexLocator = "//div[contains(@id,'post%s_%s') and contains(@class,'post--')]";
    private final By postText = By.xpath("//div[contains(@class,'wall_post_text')]");
    private final By postLike = By.xpath("//div[contains(@class,'ActionContainer')]");
    private final By postPhoto = By.xpath("//a[contains(@class,'image_cover')]");
    private final By postAuthor = By.xpath("//h5[contains(@class,'author')]//a");
    private final By postComments = By.xpath("//div[contains(@class,'reply_dived')]");
    private final By openCommentsToPost = By.xpath("//span[contains(@class,'next_label')]");

    public MyPage() {
        super(By.xpath("//div[@class='ProfileInfo']"), "My page");
    }

    public ILabel getPostByPostId(int postId) {
        return getElementFactory().getLabel(By.xpath(String.format(postFlexLocator, System.getenv(ConfigConstants.ENV_OWNER_ID), postId)), String.format("Post with postId = %s", postId));
    }

    public String getPostTextByPostId(int postId) {
        return getTextElement(getPostByPostId(postId).findChildElement(postText, ElementType.LABEL));
    }

    public void likePostByPostId(int postId) {
        IButton postLikeButton = getPostByPostId(postId).findChildElement(postLike, ElementType.BUTTON);
        clickElement(postLikeButton);
    }

    public int getPostPhotoIdByPostId(int postId) {
        ILabel photo = getPostByPostId(postId).findChildElement(postPhoto, ElementType.LABEL);
        String attributeValue = getAttributeElement(photo, HtmlAttribute.PATH_NAME);
        return Integer.parseInt(RegExUtils.useRegExPattern(attributeValue, RegExPatterns.PATTERN_FOR_PHOTO_ID));
    }

    public int getPostAuthorIdByPostId(int postId) {
        ILabel author = getPostByPostId(postId).findChildElement(postAuthor, ElementType.LABEL);
        String attributeValue = getAttributeElement(author, HtmlAttribute.HREF);
        return Integer.parseInt(RegExUtils.useRegExPattern(attributeValue, RegExPatterns.PATTERN_FOR_AUTHOR_ID));
    }

    public List<ILabel> getListAllCommentsToPostByPostId(int postId) {
        elementIsDisplayed(getPostByPostId(postId).findChildElement(postComments, ElementType.LABEL));
        return getPostByPostId(postId).findChildElements(postComments, ElementType.LABEL);
    }

    public void openCommentsToPostByPostId(int postId) {
        IButton openCommentsToPostButton = getPostByPostId(postId).findChildElement(openCommentsToPost, ElementType.BUTTON);
        clickElement(openCommentsToPostButton);
    }
}

