package com.consumer.marvel.dto;

import lombok.Getter;

@Getter
public class ApiResponse {
    private int code;
    private String status;
    private String copyright;
    private String attributionText;
    private String attributionHTML;
    private Data data;
    private String etag;
}
