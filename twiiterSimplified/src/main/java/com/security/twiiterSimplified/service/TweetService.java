package com.security.twiiterSimplified.service;

import com.security.twiiterSimplified.dtos.CreateTweetDto;
import com.security.twiiterSimplified.dtos.FeedDto;
import com.security.twiiterSimplified.dtos.FeedItemDto;
import com.security.twiiterSimplified.entiities.Role;
import com.security.twiiterSimplified.entiities.Tweet;
import com.security.twiiterSimplified.repository.TweetRepository;
import com.security.twiiterSimplified.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TweetService {

    private TweetRepository tweetRepository;
    private UserRepository userRepository;

    public TweetService(TweetRepository tweetRepository, UserRepository userRepository) {
        this.tweetRepository = tweetRepository;
        this.userRepository = userRepository;
    }

    public void createTweet(CreateTweetDto dto,
                            JwtAuthenticationToken token) {

        var user = userRepository.findById(Long.valueOf(token.getName()));
        var tweet = new Tweet();

        tweet.setUser(user.get());
        tweet.setContent(dto.content());

        tweetRepository.save(tweet);
    }

    public void deleteTweet(Long id, JwtAuthenticationToken token) {

        var tweet = tweetRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        var user = userRepository.findById(Long.valueOf(token.getName()));
        var isAdmin = user.get().getRoles()
                .stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(Role.Values.ADMIN.name()));

       if (tweet.getUser().getUserId().equals(Long.valueOf(token.getName())) || isAdmin) {
           tweetRepository.delete(tweet);
       } else {
           throw new RuntimeException("you can`t delete a tweet that was not posted by yours");
       }


    }

    public FeedDto feed(int page, int pageSize) {

        var tweets =
                tweetRepository.findAll(PageRequest.of(page, pageSize, Sort.Direction.DESC,
                        "creationTimeStamp"))
                        .map(tweet -> new FeedItemDto(tweet.getTweetId(), tweet.getContent(),
                                tweet.getUser().getUsername()));

        return new FeedDto(tweets.getContent(), page, pageSize, tweets.getTotalPages(), tweets.getTotalElements());




    }

}
