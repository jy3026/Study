package wanted.jinyoung.auth.dto;

import lombok.Getter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
public class LoginDto {
    private String email;
    private String password;

    public boolean isValidEmailFormat() {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
