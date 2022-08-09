package com.sparta.myblog.domain.ec;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.myblog.domain.Timestamped;
import com.sparta.myblog.domain.ec.Comment;
import com.sparta.myblog.dto.ec.ReplyDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Replys")
public class Reply extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long replyId;

    @Column(nullable = false)
    private String content;

    @Column
    private int likeCount;

    @JsonIgnore
    @JoinColumn(name = "comment_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Comment comment;

//    @JoinColumn(name = "user_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private PostUser postUser;


    public Reply(Comment comment, ReplyDto dto){
        this.content = dto.getContent();
        this.comment = comment;
    }

    public void update(ReplyDto dto){
        this.content = dto.getContent();
    }
}
