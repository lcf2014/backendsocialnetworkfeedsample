package com.backendsocialnetworkapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostByFollowing {
    private UserEntity user;
    private PostEntity post;
}
