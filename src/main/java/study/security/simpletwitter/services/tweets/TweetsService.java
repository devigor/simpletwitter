package study.security.simpletwitter.services.tweets;

import study.security.simpletwitter.entities.Tweet.DTOs.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweet.Tweets;
import study.security.simpletwitter.entities.User.User;

public interface TweetsService {
    Tweets createTweet(CreateTweetDTO createTweeterDTO, User user);
    Tweets findTweetById(Long tweetId);
    void deleteTweetById(Long tweetId);
}
