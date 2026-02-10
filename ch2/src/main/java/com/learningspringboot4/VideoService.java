package com.learningspringboot4;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoService {

    private List<Video> videos = List.of(
            new Video("Need HELP with your SPRING BOOT 4 App?"),
            new Video("Don't do THIS to your own CODE!"),
            new Video("SECRETS to fix BROKEN CODE!"));

    private List<@Nullable VideoV2> videosV2 = List.of(
            new VideoV2("MATRIX", "Matrix - Reload"),
            new VideoV2("Lord of the Rings", "The Fellowship of the Ring"),
            new VideoV2("The Silence of the Lambs", null)
            /*, null*/);

    public List<Video> getVideos() {
        return videos;
    }

    public List<VideoV2> getVideosV2() {
        return videosV2;
    }

    public Video create(Video newVideo) {
        List<Video> extend = new ArrayList<>(videos);
        extend.add(newVideo);
        this.videos = List.copyOf(extend);
        return newVideo;
    }

    @NonNull Video getFirstVideosByName( @NonNull String name) {
        return videos.stream().filter(n -> n.name().contains(name)).findFirst().orElse(null);
    }
}
