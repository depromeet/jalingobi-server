package depromeet.api.domain.auth.feign;


import java.util.List;
import lombok.Data;

@Data
public class Keys {

    private List<PubKey> keys;

    @Data
    public static class PubKey {
        private String alg;

        private String e;

        private String kid;

        private String kty;

        private String n;

        private String use;
    }
}
