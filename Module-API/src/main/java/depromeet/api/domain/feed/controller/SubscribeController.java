package depromeet.api.domain.feed.controller;


import java.io.IOException;
import javax.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Slf4j
@RequiredArgsConstructor
@RestController
public class SubscribeController {

    private final SubscribeChallengeUseCase sseEmitters;

    @GetMapping(value = "/connect/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<SseEmitter> subscribe(@PathVariable("id") String id) throws IOException {
        SseEmitter emitter = new SseEmitter();
        sseEmitters.add(id, emitter);
        emitter.send(SseEmitter.event().name(id).data("connected!"));
        return ResponseEntity.ok(emitter);
    }

    @PostMapping("/{id}")
    public ResponseEntity<Void> count(
            @PathVariable("id") String id, @PathParam("message") String message) {
        sseEmitters.pub(id, message);
        return ResponseEntity.ok().build();
    }
}
