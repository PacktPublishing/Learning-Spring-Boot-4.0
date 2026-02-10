package com.learningspringboot4;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiClientController {

    private final VideoClient videoClient;

    public ApiClientController(VideoClient videoClient) {
        this.videoClient = videoClient;
    }

    @GetMapping(value = "/api/videos/client-test", version = "1")  // Enable header versioning
    public List<Video> allV1() {
        return videoClient.getVideosV1();
    }

    @GetMapping(value = "/api/videos/client-test", version = "2")  // Enable header versioning
    public List<VideoV2> allV2() {
        return videoClient.getVideosV2();
    }

}
