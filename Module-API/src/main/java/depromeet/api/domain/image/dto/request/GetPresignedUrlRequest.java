package depromeet.api.domain.image.dto.request;


import depromeet.api.config.s3.ImageFileExtension;
import depromeet.api.config.s3.ImageUploadType;
import depromeet.common.annotation.ValidEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPresignedUrlRequest {

    @ValidEnum(enumClass = ImageFileExtension.class, message = "Invalid evaluation parameters")
    private ImageFileExtension imageFileExtension;

    // type은 [record, userProfile, challenge] 이게 폴더 명이 될거임.
    @ValidEnum(enumClass = ImageUploadType.class, message = "Invalid evaluation parameters")
    private ImageUploadType type;
}
