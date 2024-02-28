package com.nmt.freelancermarketplacespringboot.common.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class RestResponse {
    private Boolean           success;

    private List<String>      messages;

    private Object            data;

    public RestResponse(Boolean success, List<String> messages, Object data) { //Boolean success,
        this.success = success;
        this.messages = messages;
        this.data = data;
    }

    public RestResponse(Boolean success, String message, Object data) { //Boolean success,
        this.success = success;
        List<String> messages = null;
        if (message != null && !message.isEmpty()) {
            messages = new ArrayList<String>();
            messages.add(message);
        }
        this.messages = messages;
        this.data = data;
    }
}
