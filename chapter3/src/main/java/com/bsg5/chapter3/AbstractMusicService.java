package com.bsg5.chapter3;

import com.bsg5.chapter3.model.Artist;
import com.bsg5.chapter3.model.Song;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractMusicService implements MusicService, Resettable {
    private Map<String, Artist> bands = new HashMap<>();

    protected String transformArtist(String artistName) {
        return artistName;
    }

    protected String transformSong(String songName) {
        return songName;
    }

    @Override
    public void reset() {
        bands.clear();
    }

    private Artist getArtist(String artistName) {
        String normalizedName = transformArtist(artistName);
        return bands.computeIfAbsent(normalizedName, k -> new Artist(normalizedName));
    }

    @Override
    public Song getSong(String artistName, String songName) {
        Artist artist = getArtist(artistName);
        String normalizedSongName = transformSong(songName);
        return artist
            .getSongs()
            .computeIfAbsent(normalizedSongName, Song::new);
    }

    @Override
    public List<Song> getSongsForArtist(String artistName) {
        Artist artist = getArtist(artistName);
        List<Song> songs = new ArrayList<>(artist.getSongs().values());
        songs.sort(Song::compareTo);
        return songs;
    }

    @Override
    public List<String> getMatchingArtistNames(String artistNamePrefix) {
        String normalizedPrefix = transformArtist(artistNamePrefix).toLowerCase();
        return bands
            .keySet()
            .stream()
            .filter(artistName -> transformArtist(artistName).toLowerCase().startsWith(normalizedPrefix))
            .sorted(Comparator.comparing(Function.identity()))
            .collect(Collectors.toList());
    }

    @Override
    public List<String> getMatchingSongNamesForArtist(String artistName, String songNamePrefix) {
        String prefix = transformSong(songNamePrefix).toLowerCase();
        return getArtist(artistName)
            .getSongs()
            .keySet()
            .stream()
            .filter(name -> transformSong(name).toLowerCase().startsWith(prefix))
            .sorted(Comparator.comparing(Function.identity()))
            .collect(Collectors.toList());
    }

    @Override
    public Song voteForSong(String artistName, String songName) {
        Song song = getSong(artistName, songName);
        song.setVotes(song.getVotes() + 1);
        return song;
    }
}
