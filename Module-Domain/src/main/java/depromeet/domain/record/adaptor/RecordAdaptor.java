package depromeet.domain.record.adaptor;


import depromeet.common.annotation.Adaptor;
import depromeet.domain.record.repository.RecordRepository;
import lombok.RequiredArgsConstructor;

@Adaptor
@RequiredArgsConstructor
public class RecordAdaptor {
    private final RecordRepository recordRepository;
}
