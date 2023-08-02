package wanted.jinyoung.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import wanted.jinyoung.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board,Long> {
}
