package com.backendsocialnetworkapplication.service;

import com.backendsocialnetworkapplication.entity.UserEntity;
import com.backendsocialnetworkapplication.service.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
@RequiredArgsConstructor
public class UserService {
    final UserClient userClient;

    public ResponseObjectService findById(final String id) {
        UserEntity userEntity = userClient.getUserById(id);
        return ResponseObjectService.builder()
                .payload(userEntity)
                .status("success")
                .message("success")
                .build();
    }
}
