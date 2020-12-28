package com.bsg5.chapter7;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@AllArgsConstructor
public class ArtistController {
  private final ArtistRepository artistRepository;

  @GetMapping("/artist/{id}")
  public Artist findArtistById(@PathVariable int id) throws SQLException {
    return artistRepository.findArtistById(id);
  }

  @GetMapping({"/artist/search/{name}", "/artist/search"})
  public Artist findArtistByName(@PathVariable(required = false) String name) throws SQLException {
    return artistRepository.findArtistByName(name == null ? "" : name);
  }

  @PostMapping("/artist")
  public Artist saveArtist(@RequestBody String name) throws SQLException {
    Artist artist = artistRepository.saveArtist(name);
    System.out.println(">>" + artist);
    return artist;
  }

  @GetMapping({"/artist/match/{name}", "/artist/match/"})
  public List<Artist> matchArtists(@PathVariable(required = false) String name) throws SQLException {
    return artistRepository.findAllArtistsByName(name == null ? "" : name);
  }
}
