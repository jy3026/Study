package wanted.jinyoung.board.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import wanted.jinyoung.board.dto.BoardDto;
import wanted.jinyoung.board.entity.Board;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BoardMapper {
    Board boardPostToBoard(BoardDto.Post requestBody);

    BoardDto.Response boardToBoardResponse(Board board);
}