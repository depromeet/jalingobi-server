package depromeet.user.domain;

// import javax.persistence.Entity;
// import javax.persistence.Id;


import lombok.Getter;

// @Entity
@Getter
public class User {

    //    @Id
    private Long id;

    private String socialId;

    private UserRole role;

    // 추가

    // 검증

}
