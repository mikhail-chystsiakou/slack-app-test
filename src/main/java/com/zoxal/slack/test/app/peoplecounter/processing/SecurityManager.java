package com.zoxal.slack.test.app.peoplecounter.processing;

/**
 * @author mich0217
 * @version 08/13/2018
 */
// TODO configure as bean
public class SecurityManager {
    private static String adminUID;

    public static boolean isResetAllowed(String userId) {
        return adminUID.equals(userId);
    }

    public static void setAdminUID(String adminUID) {
        SecurityManager.adminUID = adminUID;
    }
}
