package wanted.jinyoung.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id",nullable = false)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
