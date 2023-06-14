package depromeet.api.domain.image.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageUploadType {
    RECORD("record"),
    PROFILE("profile"),
    CUSTOM_PROFILE("custom-profile"),
    CHALLENGE("challenge");

    private final String type;
}
