package depromeet.api.domain.auth.feign;


import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "kakaoAuthClient", url = "https://kauth.kakao.com")
public interface kakaoAuthFeignClient {

    @Cacheable(cacheNames = "kakaoOICD", cacheManager = "oidcCacheManager")
    @GetMapping(value = "/.well-known/jwks.json")
    Keys getKeys();
}
