package com.bsg5.chapter6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import com.bsg5.chapter3.MusicService;
import com.bsg5.chapter3.model.Song;

@Controller
public class GetSongsController {
    @Autowired
    MusicService service;

    @GetMapping("/songs")
    @ResponseBody
    public ResponseEntity<List<Song>> getSongsById(@RequestParam(name = "artist") String artist) {
        System.out.printf("%s\n", artist);
        List<Song> songs = service.getSongsForArtist(artist);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
