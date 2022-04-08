package com.backendsocialnetworkapplication.repository;

import java.util.List;
import java.util.Optional;

import com.backendsocialnetworkapplication.entity.PostEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<PostEntity, String> {
    Optional<List<PostEntity>> findByUserId(String id);
    Optional<List<PostEntity>> findByUserIdOrderByCreatedAtDesc(String id);
    
}
