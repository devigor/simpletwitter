package study.security.simpletwitter.services.tweets;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import study.security.simpletwitter.entities.Tweet.DTOs.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweet.Tweets;
import study.security.simpletwitter.entities.User.User;
import study.security.simpletwitter.repositories.TweetsRepository;

@Service
@AllArgsConstructor
public class TweetsServiceImpl implements TweetsService {
    private final TweetsRepository tweetsRepository;

    @Override
    public Tweets createTweet(CreateTweetDTO createTweeterDTO, User user) {
        Tweets tweet = new Tweets();
        tweet.setUser(user);
        tweet.setContent(createTweeterDTO.content());

        return tweetsRepository.save(tweet);
    }

    @Override
    public Tweets findTweetById(Long tweetId) {
        return tweetsRepository.findById(tweetId).orElseThrow();
    }

    @Override
    public void deleteTweetById(Long tweetId) {
        tweetsRepository.deleteById(tweetId);
    }
}
