package com.bsg5.chapter3;
// Code configuration for MusicService1

import com.bsg5.chapter3.mem01.MusicService1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration7 {
    @Bean
    MusicService musicService() {
        return new MusicService1();
    }
}