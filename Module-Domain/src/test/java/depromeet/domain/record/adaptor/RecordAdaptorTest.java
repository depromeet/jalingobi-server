package depromeet.domain.record.adaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import depromeet.domain.record.domain.Record;
import depromeet.domain.record.domain.RecordEvaluation;
import depromeet.domain.record.repository.RecordRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class RecordAdaptorTest {
    // mock 객체 생성
    @Mock private RecordRepository recordRepository;

    private RecordAdaptor recordAdaptor;

    @BeforeEach
    public void setup() {
        // mock 객체 초기화
        MockitoAnnotations.openMocks(this);
        recordAdaptor = new RecordAdaptor(recordRepository);
    }

    @Test
    @DisplayName("[Record Adaptor] 챌린지 기록 저장 메서드 테스트")
    public void RecordAdaptorTest() throws Exception {
        // given
        // 메서드가 record 객체를 반환하도록 mockito 설정
        Record record = Record.createRecord(1L, 1L, 4000, "커피", "커피는 무죄", "", RecordEvaluation.ONE);
        when(recordRepository.save(record)).thenReturn(record);
        // when
        Record savedRecord = recordAdaptor.save(record);
        // then
        // 예상한 결과와 실제 결과가 일치하는지 확인
        verify(recordRepository, times(1)).save(record);
        Assertions.assertThat(savedRecord.getName()).isEqualTo(record.getName());
        assertEquals(record, savedRecord);
    }
}
