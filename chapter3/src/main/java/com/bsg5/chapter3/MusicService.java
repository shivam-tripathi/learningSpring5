package com.bsg5.chapter3;

import com.bsg5.chapter3.model.Song;

import java.util.List;

public interface MusicService {
    List<Song> getSongsForArtist(String artistName);
    List<String> getMatchingArtistNames(String artistName);
    List<String> getMatchingSongNamesForArtist(String artistName, String songNamePrefix);
    Song getSong(String artistName, String songName);
    Song voteForSong(String artistName, String songName);
}