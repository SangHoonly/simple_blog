package simple_blog.LeeJerry.dto;

import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import simple_blog.LeeJerry.entity.Board;

@Getter
@Builder
@NoArgsConstructor
public class BoardRes {

    private Long id;

    private Long user_id;

    private String title;

    private String body;

    private Integer view_count;

    private Date created_date;

    private Date modified_date;


    public static BoardRes toRes(Board board) {
        return BoardRes.builder().
                    id(board.getId())
                    .user_id(board.getUser().getId())
                    .title(board.getTitle())
                    .body(board.getBody())
                    .view_count(board.getView_count())
                    .created_date(board.getCreated_date())
                    .modified_date(board.getModified_date())
                    .build();
    }
}
