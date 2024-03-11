package org.storage;

public class DataStorage {
    private static String randomMessage;
    private static int postId;

    public static String getRandomMessage() {
        return randomMessage;
    }

    public static void setRandomMessage(String randomMessage) {
        DataStorage.randomMessage = randomMessage;
    }

    public static int getPostId() {
        return postId;
    }

    public static void setPostId(int postId) {
        DataStorage.postId = postId;
    }
}
