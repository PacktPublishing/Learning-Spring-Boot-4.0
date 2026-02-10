package com.learningspringboot4;

import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange("/api/videos")
public interface VideoClient {

    @GetExchange(version = "1")
    List<Video> getVideosV1();

    @GetExchange(version = "2")
    List<VideoV2> getVideosV2();
}