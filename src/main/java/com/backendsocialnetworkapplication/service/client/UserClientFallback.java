package com.backendsocialnetworkapplication.service.client;

import com.backendsocialnetworkapplication.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;


@Component
public class UserClientFallback implements UserClient {

        @Override
        public UserEntity getUserById(@PathVariable String userId) {
            return UserEntity.builder().id("fallback-id").build();
        }
    }

