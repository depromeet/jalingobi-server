package depromeet.api.domain.auth.feign;


import depromeet.api.domain.auth.feign.dto.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakaoApiClient", url = "https://kapi.kakao.com")
public interface kakaoApiFeignClient {

    @GetMapping(value = "/v1/oidc/userinfo")
    UserInfo getUserInfo(@RequestHeader(name = "Authorization") String Authorization);
}
