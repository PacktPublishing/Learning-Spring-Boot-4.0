package com.learningspringboot4;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    private final VideoService videoService;

    public ApiController(VideoService videoService) {
        this.videoService = videoService;
    }

    //@GetMapping(value = "/api/{version}/videos", version = "1")  // Enable path versioning
    @GetMapping(value = "/api/videos", version = "1")  // Enable header versioning
    public List<Video> all() {
        return videoService.getVideos();
    }

    @PostMapping("/api/videos")
    public Video newVideo(@RequestBody Video newVideo) {
        return videoService.create(newVideo);
    }

    //@GetMapping(value = "/api/{version}/videos", version = "2") // Enable path versioning
    @GetMapping(value = "/api/videos", version = "2")  // Enable header versioning
    public List<VideoV2> allV2() {
        return videoService.getVideosV2();
    }

    //JSpecify
    @GetMapping(value = "/api/videos/get-first-by-name")
    public Video getFirstByName(@RequestParam String name) {

        if(name.isBlank()) name = null;

        Video videoV2 = videoService.getFirstVideosByName(name);

        IO.println(videoV2.toString());

        return videoV2;
    }


}
