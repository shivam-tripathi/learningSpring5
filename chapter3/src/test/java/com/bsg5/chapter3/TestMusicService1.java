package com.bsg5.chapter3;

import com.bsg5.chapter3.mem01.MusicService1;
import com.bsg5.chapter3.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration("/config-01.xml")
public class TestMusicService1 extends AbstractTestNGSpringContextTests {
    @Autowired
    ApplicationContext context;
    @Autowired
    MusicService service;

    @Test
    public void testConfiguration() {
        assertNotNull(context);
        Set<String> definitions = new HashSet<>(Arrays.asList(context.getBeanDefinitionNames()));
        for (String definition: definitions) {
            System.out.printf("%s\n", definition);
        }
        System.out.printf("\n");
        assertTrue(definitions.contains("musicService1"));
    }

    @Test
    public void testMusicService() {
        System.out.printf("IsMusicService1 -> %s\n", service instanceof MusicService1);
        Song song = service.getSong("Threadbare Loaf", "Someone stole the flour");
        assertEquals(song.getVotes(), 0);
    }
}