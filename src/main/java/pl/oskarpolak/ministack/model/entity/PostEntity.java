package pl.oskarpolak.ministack.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "post")
@Data
public class PostEntity {
    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String content;

    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

//    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY)
//    private List<CommentEntity> comments;
}
