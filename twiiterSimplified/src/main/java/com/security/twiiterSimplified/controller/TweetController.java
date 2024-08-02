package com.security.twiiterSimplified.controller;

import com.security.twiiterSimplified.dtos.CreateTweetDto;
import com.security.twiiterSimplified.dtos.FeedDto;
import com.security.twiiterSimplified.service.TweetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<Void> createTweet(@RequestBody CreateTweetDto dto,
                                            JwtAuthenticationToken token) {

        tweetService.createTweet(dto, token);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable(name = "id") Long id,
                                            JwtAuthenticationToken token) {

        tweetService.deleteTweet(id, token);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/feed")
    public ResponseEntity<FeedDto> feed(@RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {


        var response = tweetService.feed(page, pageSize);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
