package study.security.simpletwitter.services.tweets;

import study.security.simpletwitter.dto.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweets;
import study.security.simpletwitter.entities.User;

public interface TweetsService {
    Tweets createTweet(CreateTweetDTO createTweeterDTO, User user);
}
