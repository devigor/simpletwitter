package study.security.simpletwitter.services.tweets;

import org.springframework.stereotype.Service;
import study.security.simpletwitter.dto.CreateTweetDTO;
import study.security.simpletwitter.entities.Tweets;
import study.security.simpletwitter.entities.User;
import study.security.simpletwitter.repositories.TweetsRepository;
import study.security.simpletwitter.services.user.UserService;

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
}
