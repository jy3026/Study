package wanted.jinyoung.board.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wanted.jinyoung.member.entity.Member;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id",nullable = false)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String contents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 관계편의 메서드
    public void addMember(Member member) {
        if(!member.getBoards().contains(this)) member.getBoards().add(this);
        this.member = member;
    }
}
