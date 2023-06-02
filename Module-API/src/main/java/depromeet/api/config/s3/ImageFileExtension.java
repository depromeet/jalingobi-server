package depromeet.api.config.s3;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ImageFileExtension {
    JPEG("jpeg"),
    JPG("jpeg"),
    PNG("png");

    private final String uploadExtension;
}
