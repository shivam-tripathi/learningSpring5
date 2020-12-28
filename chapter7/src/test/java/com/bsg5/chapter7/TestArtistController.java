package com.bsg5.chapter7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestArtistController extends AbstractTestNGSpringContextTests {
  @LocalServerPort
  private int port;

  @Autowired
  TestRestTemplate restTemplate;

  @DataProvider
  Object[][] artistData() {
    return new Object[][]{
            new Object[]{1, "Threadbare Loaf"},
            new Object[]{2, "Therapy Zeppelin"},
            new Object[]{3, "Clancy In Silt"},
            new Object[]{-1, null},
            new Object[]{-1, "Not A Band"}
    };
  }

  @Test(dataProvider = "artistData")
  public void testGetArtist(int id, String name) {
    String url = String.format("http://localhost:%d/artist/%d", port, id);
    ResponseEntity<Artist> response = restTemplate.getForEntity(url, Artist.class);
    if (id != -1) {
      Assert.assertEquals(new Artist(id, name), response.getBody());
    } else {
      Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }

  @Test(dataProvider = "artistData")
  public void testSearchArtist(int id, String name) {
    String url = String.format("http://localhost:%d/artist/search/%s", port, name == null ? "" : name);
    ResponseEntity<Artist> response = restTemplate.getForEntity(url, Artist.class);
    if (id != -1) {
      Assert.assertEquals(new Artist(id, name), response.getBody());
    } else {
      Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
  }

  @Test
  public void testSaveExistingArtist() {
    String url = String.format("http://localhost:%d/artist", port);
    Artist existingArtist = restTemplate.getForObject(url + "/1", Artist.class);

    ResponseEntity<Artist> response = restTemplate.postForEntity(url, existingArtist.getName(), Artist.class);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Artist savedArtist = response.getBody();
    Assert.assertNotNull(savedArtist);
    Assert.assertEquals(savedArtist.getId(), existingArtist.getId());
    Assert.assertEquals(savedArtist.getName(), existingArtist.getName());

    response = restTemplate.getForEntity(url + "/" + existingArtist.getId(), Artist.class);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Artist foundArtist = response.getBody();
    Assert.assertNotNull(foundArtist);
    Assert.assertEquals(savedArtist, foundArtist);
  }

  @DataProvider
  public Object[][] artistMatches() {
    return new Object[][]{
            new Object[]{"", 3},
            new Object[]{"T", 2},
            new Object[]{"Th", 2},
            new Object[]{"Thr", 1},
            new Object[]{"C", 1},
            new Object[]{"Z", 0}
    };
  }

  @Test(dataProvider = "artistMatches")
  public void testMatches(String name, int matches) {
    ParameterizedTypeReference<List<Artist>> type = new ParameterizedTypeReference<>() {
    };
    ResponseEntity<List<Artist>> response = restTemplate.exchange(
            String.format("http://localhost:%d/artist/match/%s", port, name),
            HttpMethod.GET,
            null,
            type
    );
    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Assert.assertNotNull(response.getBody());
    Assert.assertEquals(response.getBody().size(), matches);
  }

  @Test(dependsOnMethods = "testMatches")
  public void testSaveArtist() {
    String url = String.format("http://localhost:%d/artist", port);
    Artist newArtist = new Artist(0, "Symphony");
    ResponseEntity<Artist> response = restTemplate.postForEntity(url, newArtist.getName(), Artist.class);
    Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
    Artist savedArtist = response.getBody();
    Assert.assertNotNull(savedArtist);
    int id = savedArtist.getId();
    Assert.assertNotEquals(id, 0);
    Assert.assertEquals(savedArtist.getName(), newArtist.getName());
  }
}
