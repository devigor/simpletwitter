package study.security.simpletwitter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import study.security.simpletwitter.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
