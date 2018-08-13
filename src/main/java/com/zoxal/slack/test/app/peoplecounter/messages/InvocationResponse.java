package com.zoxal.slack.test.app.peoplecounter.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response message for people counter call
 *
 * @author Mike
 * @version 08/12/2018
 */
public class InvocationResponse {
    public static String SILENT = "ephemeral";
    public static String CHANNEL = "in_channel";

    private String text;
    @JsonProperty("response_type")
    private String responseType = SILENT;

    public InvocationResponse() {

    }

    public InvocationResponse(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "InvocationResponse{" +
                "text='" + text + '\'' +
                ", responseType='" + responseType + '\'' +
                '}';
    }
}
