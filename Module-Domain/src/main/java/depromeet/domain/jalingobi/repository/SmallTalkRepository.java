package depromeet.domain.jalingobi.repository;


import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.jalingobi.domain.SmallTalk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmallTalkRepository extends JpaRepository<SmallTalk, Long> {
    List<SmallTalk> findByLevel(Level level);
}
