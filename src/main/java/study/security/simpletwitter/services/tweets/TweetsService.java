package study.security.simpletwitter.services.tweets;

import study.security.simpletwitter.dto.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweets;
import study.security.simpletwitter.entities.User;

import java.util.Optional;

public interface TweetsService {
    Tweets createTweet(CreateTweetDTO createTweeterDTO, User user);
    Tweets findTweetById(Long tweetId);
    void deleteTweetById(Long tweetId);
}
