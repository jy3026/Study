package wanted.jinyoung.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    NOT_VALID_EMAIL_FORMAT(401,"유효 하지 않은 이메일 형식 입니다"),
    ENTER_AT_LEAST_EIGHT(401,"비밀번호를 8자리 이상 입력하세요"),
    BOARD_NOT_FOUND(404,"Board not found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
