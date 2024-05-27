package com.consumer.marvel.dto;

import java.util.Date;
import java.util.List;

public class Result {

    private int id;
    private String name;
    private String description;
    private Date modified;
    private String resourceURI;
    private List<Url> urls;
    private Thumbnail thumbnail;
    private Comics comics;
    private Stories stories;
    private Events events;
    private Series series;
}
