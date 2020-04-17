package com.bsg5.chapter3.mem06;

import com.bsg5.chapter3.AbstractMusicService;
import com.bsg5.chapter3.Normalizer;


public class MusicService6 extends AbstractMusicService {
    private final Normalizer artistNormalizer;
    private final Normalizer songNormalizer;

    public MusicService6(Normalizer artistNormalizer, Normalizer songNormalizer) {
        this.artistNormalizer = artistNormalizer;
        this.songNormalizer = songNormalizer;
    }

    @Override
    protected String transformArtist(String artistName) {
        return artistNormalizer.transform(artistName);
    }

    @Override
    protected String transformSong(String songName) {
        return songNormalizer.transform(songName);
    }
}
