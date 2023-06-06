package depromeet.api.domain.image.dto.request;


import depromeet.api.domain.image.dto.ImageFileExtension;
import depromeet.api.domain.image.dto.ImageUploadType;
import depromeet.common.annotation.ValidEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class IssuePresignedUrlRequest {

    @Schema(nullable = false, description = "JPG, PNG, JPEG")
    @NotBlank(message = "파일 확장자를 입력해주세요.")
    @ValidEnum(
            enumClass = ImageFileExtension.class,
            message = "유효하지 않은 ImageFileExtension 파라미터입니다.")
    private ImageFileExtension imageFileExtension;

    @Schema(nullable = false, description = "RECODE, PROFILE, CHALLENGE")
    @NotBlank(message = "이미지가 업로드 되는 타입(RECODE, PROFILE, CHALLENGE)을 입력해주세요.")
    @ValidEnum(enumClass = ImageUploadType.class, message = "유효하지 않은 ImageUploadType 파라미터입니다.")
    private ImageUploadType type;
}
