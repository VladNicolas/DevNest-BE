package com.nicoals.DevNestBE.request;

import lombok.Data;

@Data
public class MessageRequest {

    private Long senderId;
    private Long projectId;
    private String content;
}
