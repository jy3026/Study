package wanted.jinyoung.member.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import wanted.jinyoung.board.entity.Board;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Board> boards = new ArrayList<>();

    public void updateMemberInfo(Long id,List<String> roles) {
        this.id = id;
        this.roles = roles;
    }

    public void updateRoles(List<String> roles) {
        this.roles = roles;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
