package com.sparta.myblog.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Images")
public class PostImage extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long imageId;

    @Column(nullable = false)
    private String imageUrl;

    @JoinColumn(name = "post_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Post post;
}
