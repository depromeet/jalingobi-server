package depromeet.api.domain.image.dto.request;


import depromeet.api.config.s3.ImageFileExtension;
import depromeet.common.annotation.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPresignedUrlRequest {

    @ValidEnum(enumClass = ImageFileExtension.class, message = "Invalid evaluation parameters")
    private ImageFileExtension imageFileExtension;
}
