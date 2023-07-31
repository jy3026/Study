package wanted.jinyoung.member.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class MemberDto {
    @Getter
    public static class Post {
        @Email(message = "유효한 이메일 형식이어야 합니다.")
        private String email;

        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        private String password;
    }
}
