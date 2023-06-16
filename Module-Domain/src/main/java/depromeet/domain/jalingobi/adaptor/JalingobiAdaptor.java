package depromeet.domain.jalingobi.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.jalingobi.domain.Jalingobi;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.jalingobi.exception.JalingobiNotFoundException;
import depromeet.domain.jalingobi.repository.JalingobiRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class JalingobiAdaptor {
    private final JalingobiRepository jalingobiRepository;

    public Jalingobi findJalingobi(Level level) {
        return jalingobiRepository
                .findByLevel(level)
                .orElseThrow(() -> JalingobiNotFoundException.EXCEPTION);
    }
}
