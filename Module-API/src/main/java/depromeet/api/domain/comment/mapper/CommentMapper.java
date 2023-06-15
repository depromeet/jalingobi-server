package depromeet.api.domain.comment.mapper;


import depromeet.api.domain.comment.dto.response.CreateCommentResponse;
import depromeet.common.annotation.Mapper;
import depromeet.domain.comment.domain.Comment;
import lombok.RequiredArgsConstructor;

@Mapper
@RequiredArgsConstructor
public class CommentMapper {

    public CreateCommentResponse toCreateCommentResponse(Comment comment) {
        return CreateCommentResponse.of(
                comment, comment.getUserChallengeImageUrl(), comment.getUserChallengeNickName());
    }
}
