package com.bsg5.chapter6;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
public class GreetingController {
    @GetMapping(path = "/greeting", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("Hello, world!", HttpStatus.OK);
    }
}
