package depromeet.api.config.s3;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageUploadType {
    RECORD("record"),
    PROFILE("profile"),
    CHALLENGE("challenge");

    private final String type;
}
