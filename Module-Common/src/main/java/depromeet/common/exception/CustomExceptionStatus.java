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
    LIST_NOT_NULL_ERROR(false, 1004, "비어있는 리스트 입니다."),

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
    ALREADY_WITHDRAWAL_USER(false, 1202, "이미 탈퇴한 유저입니다."),

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
    CHALLENGE_NOT_BELONG_TO_USER(false, 1802, "챌린지를 생성한 유저가 아닙니다."),
    CHALLENGE_CANNOT_BE_DELETED_AFTER_START(false, 1803, "시작된 챌린지는 삭제할 수 없습니다."),
    CHALLENGE_CANNOT_BE_UPDATED_AFTER_START(false, 1804, "시작된 챌린지는 수정할 수 없습니다."),
    INVALID_CHALLENGE_START_AT(false, 1805, "챌린지 시작일자는 생성일자 + 7입니다."),
    INVALID_CHALLENGE_END_AT(false, 1806, "챌린지 종료일자는 시작일자 + 챌린지 기간입니다."),
    CHALLENGE_NOT_IN_PROCEEDING(false, 1807, "진행 중인 챌린지가 아닙니다."),

    // feed
    FEED_NOT_FOUND(false, 1900, "요청한 날짜에 피드가 없습니다."),
    COMMENT_NOT_FOUND(false, 1901, "댓글이 존재하지 않습니다."),

    // record
    RECORD_NOT_FOUND(false, 2000, "유효하지 않은 평가 점수입니다."),
    RECORD_EVALUATION_NOT_VALID(false, 2001, "유효하지 않은 평가 점수입니다."),
    INVALID_RECORD_USER(false, 2002, "해당 지출을 기록한 사용자가 아닙니다."),
    CHARGE_CANNOT_BE_NEGATIVE(false, 2003, "현재 지출 비용은 마이너스 값이 될 수 없습니다."),

    // category
    CATEGORY_NOT_FOUND(false, 2100, "존재하지 않는 카테고리입니다."),
    CANNOT_ADD_CATEGORY(false, 2101, "카테고리를 추가할 수 없습니다."),

    // keyword
    INVALID_KEYWORD_FORMAT(false, 2200, "키워드 형식이 옳지 않습니다."),
    CANNOT_ADD_KEYWORD(false, 2201, "키워드를 추가할 수 없습니다."),
    ALL_KEYWORD_LENGTH_IS_OVER(false, 2202, "등록할 수 있는 전체 키워드 길이를 초과했습니다."),

    // emoji
    EMOJI_NOT_FOUND(false, 2300, "이모지가 존재하지 않습니다."),
    DUPLICATED_EMOJI(false, 2301, "이미 반응했던 이모지가 중복됩니다."),
    NO_MATCH_EMOJI(false, 2302, "삭제하려는 이모지가 존재하지 않습니다."),

    // userChallenge
    USER_NOT_PARTICIPATED_IN_CHALLENGE(false, 2400, "해당 챌린지에 참여중인 유저가 아닙니다."),
    PROGRESS_INFO_NOT_FOUND(false, 2401, "챌린지가 존재하지 않거나, 해당 챌린지에 참여중이 아닙니다."),
    CHALLENGE_IS_STARTED(false, 2402, "시작한 챌린지에는 참여할 수 없습니다."),
    DUPLICATE_PARTICIPATION(false, 2403, "같은 챌린지에 중복 참여는 불가능합니다."),
    STATUS_NOT_FOUND(false, 2405, "챌린지 상태가 존재하지 않습니다."),
    END_CHALLENGE_CANNOT_QUIT(false, 2406, "종료된 챌린지는 나갈 수 없습니다."),
    CREATOR_CANNOT_QUIT(false, 2407, "챌린지 생성자는 챌린지를 나갈 수 없습니다."),

    // comment
    COMMENT_NOT_BELONG_TO_USER(false, 2500, "댓글을 작성한 유저가 아닙니다."),

    // jalingobi
    JALINGOBI_LEVEL_NOT_FOUND(false, 2600, "현재 점수에 해당하는 자린고비 레벨이 없습니다."),
    JALINGOBI_NOT_FOUND(false, 2601, "존재하지 않는 자린고비 입니다."),
    SMALL_TALK_NOT_FOUND(false, 2602, "해당 레벨에는 자린고비 한마디가 존재하지 않습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
}
