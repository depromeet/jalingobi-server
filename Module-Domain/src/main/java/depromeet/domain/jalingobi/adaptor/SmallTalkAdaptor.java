package depromeet.domain.jalingobi.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.jalingobi.domain.Level;
import depromeet.domain.jalingobi.domain.SmallTalk;
import depromeet.domain.jalingobi.exception.SmallTalkNotFoundException;
import depromeet.domain.jalingobi.repository.SmallTalkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class SmallTalkAdaptor {

    private final SmallTalkRepository smallTalkRepository;

    public List<SmallTalk> findSmallTalkByLevel(Level level) {
        List<SmallTalk> smallTalks = smallTalkRepository.findByLevel(level);
        if (smallTalks.isEmpty()) {
            throw SmallTalkNotFoundException.EXCEPTION;
        }
        return smallTalks;
    }
}
