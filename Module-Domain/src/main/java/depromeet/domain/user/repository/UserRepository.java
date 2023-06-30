package depromeet.domain.user.repository;


import depromeet.domain.user.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySocialId(String socialId);

    Optional<User> findByProfileEmail(String email);

    boolean existsBySocialId(String socialId);
}
