package wanted.jinyoung.board.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wanted.jinyoung.board.dto.BoardDto;
import wanted.jinyoung.board.entity.Board;
import wanted.jinyoung.board.mapper.BoardMapper;
import wanted.jinyoung.board.service.BoardService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
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

}
