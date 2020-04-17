package com.bsg5.chapter3.mem05;

import com.bsg5.chapter3.AbstractMusicService;
import com.bsg5.chapter3.Normalizer;

public class MusicService5 extends AbstractMusicService {
    Normalizer artistNormalizer;

    Normalizer songNormalizer;

    public Normalizer getArtistNormalizer() {
        return artistNormalizer;
    }

    public void setArtistNormalizer(Normalizer artistNormalizer) {
        this.artistNormalizer = artistNormalizer;
    }

    public Normalizer getSongNormalizer() {
        return songNormalizer;
    }

    public void setSongNormalizer(Normalizer songNormalizer) {
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
