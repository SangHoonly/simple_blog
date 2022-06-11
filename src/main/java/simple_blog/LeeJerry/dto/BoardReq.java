package simple_blog.LeeJerry.dto;

import java.security.InvalidParameterException;
import java.util.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import simple_blog.LeeJerry.entity.Board;
import simple_blog.LeeJerry.entity.User;

@Getter
@Builder
@NoArgsConstructor
public class BoardReq {
    private String title;

    private String body;

    private Integer view_count;

    private Date created_date;

    private Date modified_date;

    public Board toEntity(User user) {
        if (title.equals("")) throw new InvalidParameterException("Board.title is null");
        if (body.equals("")) throw new InvalidParameterException("Board.body is null");

        return Board.builder()
            .user(user)
            .title(this.title)
            .body(this.body)
            .view_count(this.view_count)
            .build();
    }
}
