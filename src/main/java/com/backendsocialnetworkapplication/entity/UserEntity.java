package com.backendsocialnetworkapplication.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {
    @Id
    private String id;

    private String username;

    private String date;

    int numberOfFollowers;

    int numberOfFollowing;

    int numberOfPosts;

    List<String> following = new ArrayList<>();

    List<String> follower = new ArrayList<>();
}
