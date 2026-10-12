package com.tech.ian.video_streaming_be.service;

import com.tech.ian.video_streaming_be.repository.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public VideoResponse saveVideo() {
        VideoModel
        //name
        //date
        //description
        //user
        //file-type
        //video-duration
        //file-size
    }
}
