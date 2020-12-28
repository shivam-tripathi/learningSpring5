package com.bsg5.chapter7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Objects;

import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestGreetingController extends AbstractTestNGSpringContextTests {
  @Autowired
  private GreetingController greetingController;

  @LocalServerPort
  private int port;

  @Autowired
  private TestRestTemplate testRestTemplate;

  @DataProvider
  Object[][] greetingData() {
    return new Object[][]{
            new Object[]{null, "Hello, world!"},
            new Object[]{"World", "Hello, World!"},
            new Object[]{"Andrew", "Hello, Andrew!"},
            new Object[]{"John Cena", "I don't know who you are."}
    };
  }

  @Test(dataProvider = "greetingData")
  public void testRestGreeting(String name, String greeting) {
    String url = String.format("http://localhost:%d/greeting/%s", port, name == null ? "" : name);
    ResponseEntity<Greeting> responseEntity = testRestTemplate.getForEntity(url, Greeting.class);
    assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    assertEquals(Objects.requireNonNull(responseEntity.getBody()).getMessage(), greeting);
  }

  @Test(dataProvider = "greetingData")
  public void testDirectGreeting(String name, String greeting) {
    assertEquals(greetingController.greeting(name).getMessage(), greeting);
  }
}
