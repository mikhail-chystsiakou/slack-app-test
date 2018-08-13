package com.zoxal.slack.test.app.peoplecounter.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Insert description vere
 *
 * @author Mike
 * @version 08/12/2018
 */
public class InvocationRequest {
    private String command;

    private String text;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;


    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
