package depromeet.common.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CustomExceptionStatus {
    SUCCESS(true, 1000, "요청에 성공하였습니다."),
    BAD_REQUEST(false, 1001, "잘못된 요청입니다."),
    TOO_MANY_REQUEST(false, 1002, "잠시 후에 다시 시도해주세요."),
    INTERNAL_SERVER_ERROR(false, 1003, "서버 내부에서 문제가 발생하였습니다."),

    // auth
    REQUEST_ERROR(false, 1100, "입력 값을 확인해 주세요."),
    EMPTY_JWT(false, 1101, "토큰이 없습니다."),
    INVALID_JWT(false, 1102, "유효하지 않은 토큰입니다."),
    INVALID_REFRESH_TOKEN(false, 1103, "유효하지 않은 RefreshToken 입니다."),
    NOT_AUTHENTICATED_ACCOUNT(false, 1104, "로그인이 필요합니다."),
    INVALID_KEY(false, 1105, "유효하지 않는 key 값입니다."),

    // user
    ACCOUNT_NOT_FOUND(false, 1200, "사용자를 찾을 수 없습니다."),
    ACCOUNT_NOT_VALID(false, 1201, "유효한 사용자가 아닙니다."),

    // [POST] /user
    POST_USERS_EMPTY_NAME(false, 1300, "닉네임을 입력해주세요."),
    POST_USERS_INVALID_NAME(false, 1301, "닉네임 형식을 확인해주세요."),
    ACCOUNT_ALREADY_EXIST(false, 1302, "다른 플랫폼에 해당 이메일로 가입된 계정이 존재합니다."),

    // Role
    ACCOUNT_ACCESS_DENIED(false, 1400, "권한이 없습니다."),

    // Response
    RESPONSE_ERROR(false, 1500, "값을 불러오는데 실패하였습니다."),

    // Database, Server 오류
    DATABASE_ERROR(false, 1600, "데이터베이스 연결에 실패하였습니다."),
    SERVER_ERROR(false, 1601, "서버와의 연결에 실패하였습니다."),

    // File
    FILE_CONVERT_FAIL(false, 1700, "변환할 수 없는 파일입니다."),

    // challenge
    CHALLENGE_NOT_FOUND(false, 1800, "존재하지 않는 챌린지입니다."),
    CHALLENGE_IS_FULL(false, 1801, "챌린지 참가 인원이 꽉 찼습니다."),

    // feed
    FEED_NOT_FOUND(false, 1900, "요청한 날짜에 피드가 없습니다."),
    COMMENT_NOT_FOUND(false, 1901, "댓글이 존재하지 않습니다."),

    // record
    RECORD_NOT_FOUND(false, 2000, "유효하지 않은 평가 점수입니다."),
    RECORD_EVALUATION_NOT_VALID(false, 2001, "유효하지 않은 평가 점수입니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
