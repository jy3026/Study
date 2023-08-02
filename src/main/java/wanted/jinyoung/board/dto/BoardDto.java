package wanted.jinyoung.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

public class BoardDto {
    @Getter
    public static class Post{
        @NotBlank(message = "제목을 입력 해주세요.")
        private String title;
        @NotBlank(message = "내용을 입력 해주세요.")
        private String contents;
    }

    @Getter
    @Setter
    public static class Response{
        private String id;
        private String title;
        private String contents;
    }
}
