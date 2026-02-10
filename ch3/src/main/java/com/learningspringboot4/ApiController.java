package com.learningspringboot4;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ApiController {

  private final VideoService videoService;

  public ApiController(VideoService videoService) {
    this.videoService = videoService;
  }

  @GetMapping("/api/videos")
  public List<VideoEntity> all() {
    return videoService.getVideos();
  }

  @PostMapping("/api/videos")
  public VideoEntity newVideo(@RequestBody NewVideo newVideo) {
    return videoService.create(newVideo);
  }
}

