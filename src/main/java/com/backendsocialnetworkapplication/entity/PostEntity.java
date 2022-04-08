package com.backendsocialnetworkapplication.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "post")
@Builder
public class PostEntity {
    @Id
    private String id;

    private String userId;

    private String content;

    private Instant createdAt;

    private String quoteMessage;

    List<String> share = new ArrayList<>();

    List<String> quote = new ArrayList<>();

}
