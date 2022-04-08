package com.backendsocialnetworkapplication.service.client;

import com.backendsocialnetworkapplication.entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "userFeignClient", url = "${client.post.baseUrl}", fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {


    @GetMapping("/{userId}")
    UserEntity getUserById(@PathVariable String userId);
}
