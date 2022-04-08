package com.backendsocialnetworkapplication.service;

import com.backendsocialnetworkapplication.entity.PostByFollowing;
import com.backendsocialnetworkapplication.entity.PostEntity;
import com.backendsocialnetworkapplication.entity.UserEntity;
import com.backendsocialnetworkapplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public ResponseObjectService insertPost(final PostEntity inputPost) {
        inputPost.setCreatedAt(Instant.now());
        return ResponseObjectService.builder()
                .status("success")
                .message("success")
                .payload(postRepository.save(inputPost))
                .build();
    }

    public ResponseObjectService findPostByUserId(final String inputUserId) {
        Optional<List<PostEntity>> userPostsOpt = postRepository.findByUserIdOrderByCreatedAtDesc(inputUserId);
        if (userPostsOpt.isEmpty()) {
            return ResponseObjectService.builder()
                    .status("fail")
                    .message("cannot find any post from user id: " + inputUserId)
                    .payload(null)
                    .build();
        } else {
            List<PostEntity> userPosts = userPostsOpt.get();
            return ResponseObjectService.builder()
                    .status("success")
                    .message("success")
                    .payload(userPosts)
                    .build();
        }
    }

    public ResponseObjectService findPostByFollowing(final String inputUserId) {
        UserEntity user = (UserEntity) userService.findById("randomId").getPayload();
        if (user.getFollowing() != null) {
            // if user followed someone, get their ids
            List<String> followingIds = new ArrayList<>();
            for (String id : user.getFollowing()) {
                followingIds.add(id);
            }
            // based on these ids, get their equivalent posts
            List<PostByFollowing> listPosts = new ArrayList<>();
            for (String followingId : followingIds) {
                // get following user info based on Id
                UserEntity followingUser = new UserEntity();
                UserEntity optFollowingUser = (UserEntity) userService.findById("randomId").getPayload();
                if (optFollowingUser != null) {
                    followingUser = optFollowingUser;
                }

                // get equivalent posts
                Optional<List<PostEntity>> followingPostsOpt = postRepository.findByUserId(followingId);
                if (followingPostsOpt.isPresent()) {
                    // if followed account has any post, collect them
                    List<PostEntity> followingPosts = followingPostsOpt.get();
                    if (followingPosts != null) {
                        for (PostEntity item : followingPosts) {
                            listPosts.add(new PostByFollowing(followingUser, item));
                        }
                    }
                }
            }
            Collections.sort(listPosts, (o1, o2) -> o2.getPost().getCreatedAt().compareTo(o1.getPost().getCreatedAt()));
            return ResponseObjectService.builder()
                    .status("success")
                    .message("success")
                    .payload(listPosts)
                    .build();
        } else {
            return ResponseObjectService.builder()
                    .status("fail")
                    .message("user id: " + inputUserId + " has empty following list")
                    .payload(null)
                    .build();
        }

    }

}
