package com.backendsocialnetworkapplication.controller;

import com.backendsocialnetworkapplication.entity.DoubleIdObjectEntity;
import com.backendsocialnetworkapplication.entity.PostEntity;
import com.backendsocialnetworkapplication.entity.QuoteEntity;
import com.backendsocialnetworkapplication.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posts")
@AllArgsConstructor
public class PostController {

    private PostService postService;

    private QuoteService quoteService;

    private ShareService shareService;

    @PostMapping("/insertpost")
    public ResponseEntity<ResponseObjectService> insertPost(@RequestBody PostEntity inputPost) {
        return new ResponseEntity<ResponseObjectService>(postService.insertPost(inputPost), HttpStatus.CREATED);
    }

    @GetMapping("/myposts")
    public ResponseEntity<ResponseObjectService> findPostByUserId(@RequestParam String inputUserId) {
        return new ResponseEntity<ResponseObjectService>(postService.findPostByUserId(inputUserId), HttpStatus.OK);
    }

    @GetMapping("/followingposts")
    public ResponseEntity<ResponseObjectService> findPostByFollowing(@RequestParam String inputUserId) {
        return new ResponseEntity<ResponseObjectService>(postService.findPostByFollowing(inputUserId), HttpStatus.OK);
    }

    @PostMapping("/sharepost")
    public ResponseEntity<ResponseObjectService> sharePost(@RequestBody DoubleIdObjectEntity doubleId) {
        return new ResponseEntity<ResponseObjectService>(shareService.updatePostByShare(doubleId), HttpStatus.CREATED);
    }

    @PostMapping("/quotepost")
    public ResponseEntity<ResponseObjectService> quotePost(@RequestBody QuoteEntity quoteEntity) {
        return new ResponseEntity<ResponseObjectService>(quoteService.updatePostByQuote(quoteEntity), HttpStatus.CREATED);
    }

}
