package wanted.jinyoung.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import wanted.jinyoung.board.dto.BoardDto;
import wanted.jinyoung.board.entity.Board;
import wanted.jinyoung.board.mapper.BoardMapper;
import wanted.jinyoung.board.service.BoardService;
import wanted.jinyoung.dto.MultiResponseDto;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
@Validated
public class BoardController {
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @PostMapping
    public ResponseEntity<String> postBoard(@RequestBody @Valid BoardDto.Post requestBody){
        boardService.createBoard(boardMapper.boardPostToBoard(requestBody));

        return ResponseEntity.ok("게시글 생성 완료");
    }

    @GetMapping("/{board-id}")
    public ResponseEntity getBoard(@PathVariable("board-id") @Positive long boardId) {
        Board board = boardService.findBoard(boardId);

        return new ResponseEntity(boardMapper.boardToBoardResponse(board), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getBoards(@RequestParam @Positive int page,
                                    @RequestParam @Positive int size){
        Page<Board> boardPage = boardService.findBoards(page, size);
        List<Board> boards = boardPage.getContent();

        return new ResponseEntity(new MultiResponseDto<>(boardMapper.boardsToBoardResponses(boards),boardPage),HttpStatus.OK);
    }

    @PatchMapping("/{board-id}")
    public ResponseEntity patchBoard(@PathVariable("board-id") @Positive long boardId,
                                     @RequestBody @Valid BoardDto.Patch requestBody){
        requestBody.setId(boardId);
        Board board = boardService.updateBoard(boardMapper.boardPatchToBoard(requestBody));

        return new ResponseEntity(boardMapper.boardToBoardResponse(board),HttpStatus.OK);
    }

    @DeleteMapping("/{board-id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("board-id") @Positive long boardId){
        boardService.removeBoard(boardId);

        return ResponseEntity.ok("게시글 삭제 완료.");
    }

}
