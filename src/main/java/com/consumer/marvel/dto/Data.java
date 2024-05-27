package com.consumer.marvel.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class Data {

    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<Result> results;
}
