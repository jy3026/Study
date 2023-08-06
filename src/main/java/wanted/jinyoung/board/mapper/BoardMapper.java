package wanted.jinyoung.board.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import wanted.jinyoung.board.dto.BoardDto;
import wanted.jinyoung.board.entity.Board;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    Board boardPostToBoard(BoardDto.Post requestBody);

    @Mapping(source = "member.email" , target = "email")
    BoardDto.Response boardToBoardResponse(Board board);

    List<BoardDto.Response> boardsToBoardResponses(List<Board> boards);

    Board boardPatchToBoard(BoardDto.Patch requestBody);
}