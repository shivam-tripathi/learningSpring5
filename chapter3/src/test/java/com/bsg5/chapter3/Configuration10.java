package com.bsg5.chapter3;
// Code configuration for musicService4

import com.bsg5.chapter3.mem02.SimpleNormalizer;
import com.bsg5.chapter3.mem03.CapLeadingNormalizer;
import com.bsg5.chapter3.mem04.MusicService4;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration10 {
    @Bean
    Normalizer foo() {
        return new SimpleNormalizer();
    }

    @Bean
    Normalizer bar() {
        return new CapLeadingNormalizer();
    }

    @Bean
    MusicService musicService(Normalizer bar, @Qualifier("foo") Normalizer baz) {
        // bar and baz are singleton
        return new MusicService4(bar, baz);
    }
}
