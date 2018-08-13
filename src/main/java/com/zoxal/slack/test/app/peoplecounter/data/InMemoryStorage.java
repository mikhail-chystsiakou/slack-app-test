package com.zoxal.slack.test.app.peoplecounter.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Simply stores users into in-memory map
 *
 * @author Mike
 * @version 08/12/2018
 */
public class InMemoryStorage extends Storage {
    private static final Logger logger = LoggerFactory.getLogger(InMemoryStorage.class);
    private static Map<String, UserInfo> registeredUsers = new ConcurrentHashMap<>();

    @Override
    public int registerUser(String userId, String userName) {
        UserInfo userInfo = registeredUsers.get(userId);
        if (userInfo == null) {
            userInfo = new UserInfo(userName, 1);
            registeredUsers.put(userId, userInfo);
        } else {
            userInfo.registrationAttempt();
        }
        return userInfo.getRegistrationCount();
    }

    @Override
    public Collection<String> getRegisteredUsers() {
        return registeredUsers.values().stream().map(UserInfo::getUserName).collect(Collectors.toList());
    }

    @Override
    public void reset() {
        registeredUsers.clear();
    }

    @Override
    public void ban(String userId) {
        if (registeredUsers.containsKey(userId)) {
            registeredUsers.get(userId).ban();
        }
    }

    @Override
    public void doubleBan(String userId) {
        if (registeredUsers.containsKey(userId)) {
            registeredUsers.get(userId).doubleBan();
        }
    }

    private class UserInfo {
        private String userName;
        private int registrationCount;
        private boolean banned = false;
        private boolean bannedTwice = false;

        public UserInfo(String userName, int registrationCount) {
            this.userName = userName;
            this.registrationCount = registrationCount;
        }

        public int getRegistrationCount() {
            return registrationCount;
        }

        public String getUserName() {
            if (bannedTwice) {
                return userName + " (BANNED TWICE)";
            }
            if (banned) {
                return userName + " (BANNED)";
            }
            return userName;
        }

        public void ban() {
            this.banned = true;
        }

        public void doubleBan() {
            this.bannedTwice = true;
        }

        public void registrationAttempt() {
            registrationCount++;
        }
    }
}
