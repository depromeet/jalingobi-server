package depromeet.api.domain.image.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageFileExtension {
    JPEG("jpeg"),
    JPG("jpg"),
    PNG("png");

    private final String uploadExtension;
}
