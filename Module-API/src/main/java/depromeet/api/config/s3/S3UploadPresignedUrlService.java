package depromeet.api.config.s3;


import com.amazonaws.HttpMethod;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
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

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public ImageUrlDto execute(String socialId, ImageFileExtension fileExtension) {
        String fixedFileExtension = fileExtension.getUploadExtension();
        String fileName = createFileName(socialId, fixedFileExtension);
        log.info(fileName);

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                getGeneratePreSignedUrlRequest(bucket, fileName, fixedFileExtension);
        URL url = amazonS3.generatePresignedUrl(generatePresignedUrlRequest);
        return ImageUrlDto.of(url.toString(), fileName);
    }

    private String createFileName(String socialId, String fileExtension) {
        return "/record/" + socialId + "/" + UUID.randomUUID() + "." + fileExtension;
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
