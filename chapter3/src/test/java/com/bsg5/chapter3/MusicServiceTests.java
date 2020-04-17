package com.bsg5.chapter3;

import com.bsg5.chapter3.model.Song;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.testng.Assert.assertEquals;

public class MusicServiceTests {
    private List<Object []> model = Arrays.asList(
        new Object[] { "Threadbare Loaf", "Someone stole the Flour", 4 },
        new Object[] { "Threadbare Loaf", "What Happened To Our First CD?", 17 },
        new Object[] { "Therapy Zeppelin", "Medium", 4 },
        new Object[] { "Clancy in Silt", "Igneous", 5 }
    );

    void populateService(MusicService musicService) {
        model.stream().forEach(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                musicService.voteForSong((String) data[0], (String) data[1]);
            }
        });
    }

    void reset(MusicService musicService) {
        if (musicService instanceof Resettable) {
            ((Resettable)musicService).reset();
        } else {
            throw new RuntimeException(
                String.format("Service doesn't implement resettable %s\n", musicService)
            );
        }
    }

    void testSongVoting(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        model.stream().forEach(data -> {
            assertEquals(
                musicService.getSong((String) data[0], (String) data[1]).getVotes(),
                ((Integer) data[2]).intValue()
            );
        });
    }

    void testSongsForArtist(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        List<Song> songs = musicService.getSongsForArtist("Threadbare Loaf");
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    void testMatchingArtistNames(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        List<String> names = musicService.getMatchingArtistNames("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    void testMatchingSongNamesForArtist(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        List<String> songNames = musicService.getMatchingSongNamesForArtist("Threadbare Loaf", "W");
        assertEquals(songNames.size(), 1);
        assertEquals(songNames.get(0), "What Happened To Our First CD?");
    }

    void testService(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        Map<String, List<Object[]>> artistMap = new HashMap<>();
        model.stream().forEach(data -> {
            List<Object[]> arr = artistMap.computeIfAbsent((String) data[0], k -> new ArrayList<Object[]>());
            arr.add(new Object[] { data[1], data[2] });
        });
        artistMap.keySet().stream().forEach(artistName -> {
            System.out.printf("Artist -> %s\n", artistName);
            List<Object[]> artistDetails = artistMap.get(artistName);
            artistDetails.sort((a, b) -> ((Integer)b[1]).compareTo((Integer)a[1])); // Sort in reverse
            List<Song> songs = musicService.getSongsForArtist(artistName);
            assertEquals(artistDetails.size(), songs.size());
            for (int i = 0; i < artistDetails.size(); i++) {
                Object[] songDetails = artistDetails.get(i);
                Song song = songs.get(i);
                System.out.printf("Song -> %s\n", song);
                System.out.printf("SongDetails => %s %d\n", songDetails[0], songDetails[1]);
                assertEquals((String) songDetails[0], song.getName());
                assertEquals(((Integer) songDetails[1]).intValue(), song.getVotes());
            }
        });
    }
}
