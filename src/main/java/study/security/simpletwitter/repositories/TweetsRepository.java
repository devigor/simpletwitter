package study.security.simpletwitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.security.simpletwitter.entities.Tweets;

@Repository
public interface TweetsRepository extends JpaRepository<Tweets, Long> {
}
