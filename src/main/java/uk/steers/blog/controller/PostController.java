package uk.steers.blog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
public class PostController {

    @GetMapping("/test")
    public ResponseEntity<String> test () {
        return new ResponseEntity<>("Security on", HttpStatus.OK);
    }
}
