package pl.oskarpolak.ministack.model.dto;

import lombok.Data;
import pl.oskarpolak.ministack.model.entity.PostEntity;
import pl.oskarpolak.ministack.model.entity.UserEntity;

@Data
public class PostDto {
    private String title;
    private String content;
    private int userId;

    public static PostEntity convertToEntity(PostDto postDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(postDto.getUserId());

        PostEntity postEntity = new PostEntity();
        postEntity.setUser(userEntity);
        postEntity.setContent(postDto.getContent());
        postEntity.setTitle(postDto.getTitle());

        return postEntity;
    }
}
