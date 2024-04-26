package study.security.simpletwitter.services.tweets;

import org.springframework.stereotype.Service;
import study.security.simpletwitter.dto.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweets;
import study.security.simpletwitter.entities.User;
import study.security.simpletwitter.repositories.TweetsRepository;

import java.util.Optional;

@Service
public class TweetsServiceImpl implements TweetsService {
    private final TweetsRepository tweetsRepository;

    public TweetsServiceImpl(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }


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
