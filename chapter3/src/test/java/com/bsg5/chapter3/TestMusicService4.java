package com.bsg5.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-04.xml")
public class TestMusicService4 extends AbstractTestNGSpringContextTests {
    @Autowired MusicService musicService;
    @Autowired MusicServiceTests tests;

    @Test
    public void testSongVoting() {
        tests.testSongVoting(musicService);
    }

    @Test
    public void testMatchingArtistNames() {
        tests.testMatchingArtistNames(musicService);
    }

    @Test
    public void testGetSongsForArtist() {
        tests.testSongsForArtist(musicService);
    }

    @Test
    public void testMatchingSongNamesForArtist() {
        tests.testMatchingSongNamesForArtist(musicService);
    }
}