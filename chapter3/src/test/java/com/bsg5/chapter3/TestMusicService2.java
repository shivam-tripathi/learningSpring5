package com.bsg5.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-02.xml")
public class TestMusicService2 extends AbstractTestNGSpringContextTests {
    @Autowired
    MusicService musicService;
    MusicServiceTests testService = new MusicServiceTests();

    @Test
    public void testSongVoting() {
        testService.testSongVoting(musicService);
    }

    @Test
    public void testMatchingArtistNames() {
        testService.testMatchingArtistNames(musicService);
    }

    @Test
    public void testSongsForArtists() {
        testService.testSongsForArtist(musicService);
    }

    @Test
    public void testMatchingSongNamesForArtist() {
        testService.testMatchingSongNamesForArtist(musicService);
    }

    @Test
    public void testService() {
        testService.testService(musicService);
    }
}
