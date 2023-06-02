package depromeet.api.config.s3;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import depromeet.api.domain.image.dto.ImageFileExtension;
import depromeet.api.domain.image.dto.ImageUploadType;
import depromeet.api.domain.image.dto.ImageUrlDto;
import java.net.URL;
import java.util.Date;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class S3UploadPresignedUrlService {

    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageUrlDto execute(
            String socialId, ImageFileExtension fileExtension, ImageUploadType type) {
        String valueFileExtension = fileExtension.getUploadExtension();
        String valueType = type.getType();
        String fileName = createFileName(socialId, valueFileExtension, valueType);
        log.info(fileName);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                getGeneratePreSignedUrlRequest(bucket, fileName, valueFileExtension);
        URL url = amazonS3Client.generatePresignedUrl(generatePresignedUrlRequest);
        log.info(url.toString());
        return ImageUrlDto.of(url.toString(), fileName);
    }

    private String createFileName(String socialId, String fileExtension, String valueType) {
        return valueType + "/" + socialId + "/" + UUID.randomUUID() + "." + fileExtension;
    }

    // 업로드용 Pre-Signed URL을 생성하기 때문에, PUT을 지정
    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(
            String bucket, String fileName, String fileExtension) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(bucket, fileName)
                        .withMethod(HttpMethod.PUT)
                        .withKey(fileName)
                        .withContentType("image/" + fileExtension)
                        .withExpiration(getPreSignedUrlExpiration());
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL, CannedAccessControlList.PublicRead.toString());
        return generatePresignedUrlRequest;
    }

    private Date getPreSignedUrlExpiration() {
        Date expiration = new Date();
        long expTimeMillis = expiration.getTime();
        // 유효 기간 설정
        expTimeMillis += 60 * 60 * 3;
        expiration.setTime(expTimeMillis);
        return expiration;
    }
}
