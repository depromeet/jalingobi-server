package depromeet.api.domain.feed.controller;


import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Component
@Slf4j
public class SubscribeChallengeUseCase {

    private final Map<String, List<SseEmitter>> emittersPerTopic = new ConcurrentHashMap<>();

    public SseEmitter add(String id, SseEmitter emitter) {

        this.emittersPerTopic.computeIfAbsent(id, t -> new CopyOnWriteArrayList<>()).add(emitter);

        emitter.onCompletion(() -> removeEmitterFromTopic(id, emitter));
        emitter.onTimeout(() -> removeEmitterFromTopic(id, emitter));

        log.info("new emitter added: {}", emitter);
        log.info("emitter list size: {}", emittersPerTopic.get(id).size());
        emitter.onCompletion(
                () -> {
                    log.info("onCompletion callback");
                    removeEmitterFromTopic(id, emitter);
                });
        emitter.onTimeout(
                () -> {
                    log.info("onTimeout callback");
                    removeEmitterFromTopic(id, emitter);
                });

        return emitter;
    }

    public void pub(String id, String message) {
        List<SseEmitter> sseEmitters = emittersPerTopic.get(id);
        sseEmitters.forEach(
                emitter -> {
                    try {
                        emitter.send(SseEmitter.event().name(id).data(message));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    private void removeEmitterFromTopic(String topic, SseEmitter emitter) {
        List<SseEmitter> emitters = this.emittersPerTopic.get(topic);
        if (emitters != null) {
            emitters.remove(emitter);
            if (emitters.isEmpty()) {
                this.emittersPerTopic.remove(topic);
            }
        }
    }
}
