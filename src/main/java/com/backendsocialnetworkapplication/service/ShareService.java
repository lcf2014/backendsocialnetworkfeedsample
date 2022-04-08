package com.backendsocialnetworkapplication.service;

import com.backendsocialnetworkapplication.entity.DoubleIdObjectEntity;
import com.backendsocialnetworkapplication.entity.PostEntity;
import com.backendsocialnetworkapplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShareService {

    private final PostRepository postRepository;
    private final UserService userService;

    public ResponseObjectService updatePostByShare(final DoubleIdObjectEntity doubleId) {
        // id 1 - post Id, id 2 - user who shared post
        Optional<PostEntity> optPost = postRepository.findById(doubleId.getId1());
        if (optPost.isEmpty()) {
            return ResponseObjectService.builder()
                    .status("fail")
                    .message("cannot find post id: " + doubleId.getId1())
                    .payload(null)
                    .build();
        } else {
            PostEntity targetPost = optPost.get();
            List<String> shareList = targetPost.getShare();
            if (shareList == null) {
                shareList = new ArrayList<>();
            }
            // save id of user who shared the post then update post
            shareList.add(doubleId.getId2());
            targetPost.setShare(shareList);
            postRepository.save(targetPost);
            // update post list of user who shared the post
            targetPost.setUserId(doubleId.getId2());
            targetPost.setId(null);
            targetPost.setContent("Shared a post: " + targetPost.getContent());
            targetPost.setShare(new ArrayList<>());
            targetPost.setQuote(new ArrayList<>());
            postRepository.save(targetPost);

            return ResponseObjectService.builder()
                    .status("success")
                    .message("add a share to the target post id: " + targetPost.getId())
                    .payload(targetPost)
                    .build();
        }
    }
}

