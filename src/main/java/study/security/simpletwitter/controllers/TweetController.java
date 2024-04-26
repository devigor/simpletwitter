package study.security.simpletwitter.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import study.security.simpletwitter.dto.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweets;
import study.security.simpletwitter.entities.User;
import study.security.simpletwitter.services.tweets.TweetsService;
import study.security.simpletwitter.services.user.UserService;


@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    private final UserService userService;
    private final TweetsService tweetsService;

    public TweetController(UserService userService, TweetsService tweetsService) {
        this.userService = userService;
        this.tweetsService = tweetsService;
    }

    @PostMapping("")
    public ResponseEntity<Tweets> createNewTweet(@RequestBody CreateTweetDTO createTweetDTO, JwtAuthenticationToken token) {
        User user = userService.findUserById(Long.parseLong(token.getName()));

        var tweet = tweetsService.createTweet(createTweetDTO, user);

       return ResponseEntity.status(HttpStatus.CREATED).body(tweet);
    }
}
