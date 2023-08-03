package wanted.jinyoung.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public Page<Board> findBoards(int page,int size){
        return boardRepository.findAll(PageRequest.of(page-1, size, Sort.by("id").descending()));
    }

    public Board updateBoard(Board board){
        Optional<Board> optionalBoard = boardRepository.findById(board.getId());
        Board findBoard = optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));

        checkAuthor(findLoggedInMember().getId(),findBoard.getMember().getId());

        Optional.ofNullable(board.getTitle()).ifPresent(t -> findBoard.setTitle(t));
        Optional.ofNullable(board.getContents()).ifPresent(c -> findBoard.setContents(c));

        return boardRepository.save(findBoard);
    }

    public void removeBoard(long boardId){
        Optional<Board> optionalBoard = boardRepository.findById(boardId);
        Board findBoard = optionalBoard.orElseThrow(() -> new BusinessLogicException(ExceptionCode.BOARD_NOT_FOUND));

        checkAuthor(findLoggedInMember().getId(),findBoard.getMember().getId());

        boardRepository.delete(findBoard);
    }

    private Member findLoggedInMember() {
        String principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<Member> findbyEmailMember = memberRepository.findByEmail(principal);

        return findbyEmailMember.orElseThrow(() -> new BusinessLogicException(ExceptionCode.CHECK_LOGIN));
    }

    private static void checkAuthor(long memberId, long authorId) {
        if (authorId != memberId) {
            throw new BusinessLogicException(ExceptionCode.ONLY_AUTHOR);
        }
    }
}
