package com.consumer.marvel.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Events {

    private int available;
    private int returned;
    private String collectionURI;
    private List<EventItem> items;
}
