package com.backendsocialnetworkapplication.service.client;

import com.backendsocialnetworkapplication.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        log.error("An exception occurred when calling the UserClient", cause);
        return new UserClient() {
            @Override
            public UserEntity getUserById(String userId) {
                return UserEntity.builder().id("123456").build();
            }
        };
    }
}
