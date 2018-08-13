package com.zoxal.slack.test.app.peoplecounter.data;

import java.util.Collection;

/**
 * Allows to store users
 *
 * @author Mike
 * @version 08/12/2018
 */
public abstract class Storage {
    private static Storage defaultStorage;

    /**
     * Registers user. Returns the count of repeated attempts.
     *
     * @param userId        user id
     * @param userName      user name
     * @return              number of registration attempts
     */
    public abstract int registerUser(String userId, String userName);
    public abstract Collection<String> getRegisteredUsers();
    public abstract void reset();
    public abstract void ban(String userId);
    public abstract void doubleBan(String userId);

    public static synchronized Storage getDefaultStorage() {
        if (defaultStorage == null) {
            defaultStorage = new InMemoryStorage();
        }
        return defaultStorage;
    }
}
