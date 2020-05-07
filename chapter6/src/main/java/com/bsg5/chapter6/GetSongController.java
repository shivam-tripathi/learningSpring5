package com.bsg5.chapter6;

import com.bsg5.chapter3.MusicService;
import com.bsg5.chapter3.model.Song;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@Controller
public class GetSongController {
    @Autowired
    MusicService service;

    @GetMapping("/artist/{artistName}/song/{songName}")
    @ResponseBody
    public ResponseEntity<Song> getSong(
        @PathVariable("artistName") final String artistNameEncoded,
        @PathVariable("songName") final String songNameEncoded
    ) {
        String artistName = URLDecoder.decode(artistNameEncoded, StandardCharsets.UTF_8);
        String songName = URLDecoder.decode(songNameEncoded, StandardCharsets.UTF_8);
        Song song = service.getSong(artistName, songName);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }
}
