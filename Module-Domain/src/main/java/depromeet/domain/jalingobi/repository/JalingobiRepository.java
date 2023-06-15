package depromeet.domain.jalingobi.repository;


import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JalingobiRepository extends JpaRepository<Jalingobi, Long> {
    Optional<Jalingobi> findByLevel(Level level);
}
