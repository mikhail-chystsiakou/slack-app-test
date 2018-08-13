package com.zoxal.slack.test.app.peoplecounter.processing;

import com.zoxal.slack.test.app.peoplecounter.messages.InvocationResponse;
import org.slf4j.helpers.MessageFormatter;

import java.util.Arrays;
import java.util.List;

/**
 * @author mich0217
 * @version 08/13/2018
 */
public class RepeatedAttemptManager {
    private static List<String> messages;
    public static final int BAN_LIMIT = 6;
    public static final int DOUBLE_BAN_LIMIT = 8;
    public static final int REATTEMPT_LIMIT;

    static {
        messages = Arrays.asList(
                "Hello, {}!",
                "You have already said 'hi'",
                "Again? Ok, hello again",
                "Stop it and listen to Mike please -_-",
                "What are you doing? Stop this or you will get BANNED",
                "Attention, one step to get BANNED. FOREVER.",
                "Congrats, you are banned.",
                "Not enough? You wanna DOUBLE BAN?",
                "Congrats, you are banned twice",
                "You reached the deepest ban limit. Triple ban is not supported yet."
        );
        REATTEMPT_LIMIT = messages.size();
    }

    public String userRegistered(String userName, int count) {
        int messageNumber = count - 1;
        if (count >= REATTEMPT_LIMIT) {
            messageNumber = REATTEMPT_LIMIT - 1;
        }
        return MessageFormatter.format(messages.get(messageNumber), new Object[] {userName}).getMessage();
    }
}
