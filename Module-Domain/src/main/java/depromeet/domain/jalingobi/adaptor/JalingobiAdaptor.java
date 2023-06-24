package depromeet.domain.jalingobi.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.jalingobi.exception.JalingobiNotFoundException;
import depromeet.domain.jalingobi.repository.JalingobiRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class JalingobiAdaptor {

    private final JalingobiRepository jalingobiRepository;

    public List<Jalingobi> findAll() {
        List<Jalingobi> jalingobiList = jalingobiRepository.findAll();
        if (jalingobiList.isEmpty()) {
            throw JalingobiNotFoundException.EXCEPTION;
        }
        return jalingobiList;
    }

    public Jalingobi findJalingobi(Level level) {
        return jalingobiRepository
                .findByLevel(level)
                .orElseThrow(() -> JalingobiNotFoundException.EXCEPTION);
    }
}
