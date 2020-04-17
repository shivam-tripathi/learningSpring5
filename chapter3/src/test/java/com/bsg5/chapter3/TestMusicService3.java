package com.bsg5.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-03.xml")
public class TestMusicService3 extends AbstractTestNGSpringContextTests {
    @Autowired
    MusicService musicService;
    MusicServiceTests musicServiceTests = new MusicServiceTests();

    @Test
    public void testSongVoting() {
        musicServiceTests.testSongVoting(musicService);
    }

    @Test
    public void testGetMatchingArtistNames() {
        musicServiceTests.testMatchingArtistNames(musicService);
    }

    @Test
    public void testGetSongsForArtist() {
        musicServiceTests.testSongsForArtist(musicService);
    }

    @Test
    public void testMatchingSongNamesForArtist() {
        musicServiceTests.testMatchingSongNamesForArtist(musicService);
    }
}