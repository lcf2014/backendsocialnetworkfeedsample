package com.backendsocialnetworkapplication.service;

import com.backendsocialnetworkapplication.entity.DoubleIdObjectEntity;
import com.backendsocialnetworkapplication.entity.PostEntity;
import com.backendsocialnetworkapplication.entity.QuoteEntity;
import com.backendsocialnetworkapplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuoteService {

    private final PostRepository postRepository;
    private final UserService userService;

    public ResponseObjectService updatePostByQuote(final QuoteEntity quoteEntity) {
        DoubleIdObjectEntity doubleId = quoteEntity.getDoubleIdObjectEntity();
        // id 1 - post Id, id 2 - user who quoted post
        Optional<PostEntity> optPost = postRepository.findById(doubleId.getId1());
        if (optPost.isEmpty()) {
            return ResponseObjectService.builder()
                    .status("fail")
                    .message("cannot find post id: " + doubleId.getId1())
                    .payload(null)
                    .build();
        } else {
            PostEntity targetPost = optPost.get();
            List<String> quoteList = targetPost.getQuote();
            if (quoteList == null) {
                quoteList = new ArrayList<>();
            }
            // save id of user who quoted the post then update post
            quoteList.add(doubleId.getId2());
            targetPost.setQuote(quoteList);
            postRepository.save(targetPost);
            // update post list of user who quoted the post
            targetPost.setUserId(doubleId.getId2());
            targetPost.setId(null);
            targetPost.setContent("Shared a post: " + targetPost.getContent());
            targetPost.setQuote(new ArrayList<>());
            targetPost.setShare(new ArrayList<>());
            targetPost.setQuoteMessage(quoteEntity.getQuoteMessage());
            postRepository.save(targetPost);

            return ResponseObjectService.builder()
                    .status("success")
                    .message("add a Quote to the target post id: " + targetPost.getId())
                    .payload(targetPost)
                    .build();
        }
    }
}
