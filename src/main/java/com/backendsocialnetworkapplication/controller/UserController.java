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
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(value = "/profile")
    public ResponseEntity<ResponseObjectService> findById(@RequestParam  String id) {
        return new ResponseEntity<ResponseObjectService>(userService.findById(id), HttpStatus.OK);
    }

}
