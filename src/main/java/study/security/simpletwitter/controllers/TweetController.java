package study.security.simpletwitter.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import study.security.simpletwitter.entities.Tweet.DTOs.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweet.Tweets;
import study.security.simpletwitter.entities.User.User;
import study.security.simpletwitter.services.tweets.TweetsService;
import study.security.simpletwitter.services.user.UserService;


@RestController
@RequestMapping("/api/tweets")
@AllArgsConstructor
public class TweetController {
    private final UserService userService;
    private final TweetsService tweetsService;

    @PostMapping("")
    public ResponseEntity<Tweets> createNewTweet(@RequestBody CreateTweetDTO createTweetDTO, JwtAuthenticationToken token) {
        User user = userService.findUserById(Long.parseLong(token.getName()));

        var tweet = tweetsService.createTweet(createTweetDTO, user);

       return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable("id") long tweetId, JwtAuthenticationToken token) {
        var tweet = tweetsService.findTweetById(tweetId);
        boolean isTweetOwner = tweet.getUser().getId().equals(Long.parseLong(token.getName()));

        if (isTweetOwner) {
            tweetsService.deleteTweetById(tweetId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

    }
}
