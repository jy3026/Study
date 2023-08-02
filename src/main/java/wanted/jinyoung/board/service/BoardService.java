package wanted.jinyoung.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wanted.jinyoung.board.entity.Board;
import wanted.jinyoung.board.repository.BoardRepository;
import wanted.jinyoung.exception.BusinessLogicException;
import wanted.jinyoung.exception.ExceptionCode;
import wanted.jinyoung.member.entity.Member;
import wanted.jinyoung.member.repository.MemberRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    public void createBoard(Board board){
        Member member = findLoggedInMember();
        board.addMember(member);
        boardRepository.save(board);
    }

    public Board findBoard(long boardId){
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        return optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));
    }

    private Member findLoggedInMember() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<Member> findbyEmailMember = memberRepository.findByEmail(principal);
        return findbyEmailMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_EXISTS));
    }
}
