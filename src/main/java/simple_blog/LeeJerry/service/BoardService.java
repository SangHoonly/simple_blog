package simple_blog.LeeJerry.service;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import simple_blog.LeeJerry.dto.BoardReq;
import simple_blog.LeeJerry.dto.BoardRes;
import simple_blog.LeeJerry.entity.Board;
import simple_blog.LeeJerry.login.UserProxy;
import simple_blog.LeeJerry.repository.BoardRepository;

@Service
public class BoardService {

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    BoardRepository boardRepository;

    public List<BoardRes> getBoards() {
        return boardRepository.findAllBoard().stream().map(BoardRes::toRes).collect(toList());
    }

    @Transactional
    public void saveBoard(BoardReq boardReq, Authentication authentication) {
        UserProxy userProxy = (UserProxy) authentication.getPrincipal();

        boardRepository.save(boardReq.toEntity(userProxy.getUser()));
    }

    @Transactional
    public void deleteBoard(Long boardId, Authentication authentication) {
        Board board = boardRepository.findById(boardId)
            .orElseThrow(() -> new RuntimeException("해당 보드가 존재하지 않습니다."));
        UserProxy userProxy = (UserProxy) authentication.getPrincipal();

        if (board.getUser().getId() != userProxy.getId())
            throw new RuntimeException("해당 게시물의 작성자가 아닙니다!");

        boardRepository.deleteById(boardId);
    }
}
