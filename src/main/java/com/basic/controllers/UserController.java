package com.basic.controllers;

import com.basic.entities.User;
import com.basic.models.BaseResponse;
import com.basic.models.RegisterUserRequest;
import com.basic.models.UpdateUserRequest;
import com.basic.models.UserResponse;
import com.basic.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(
            path = "/api/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<String> register(@RequestBody RegisterUserRequest request) {
        userService.register(request);
        return BaseResponse.<String>builder().status("success").build();
    }

    @GetMapping(
            path = "/api/users/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<UserResponse> get(User user) {
        UserResponse userResponse = userService.get(user);
        return BaseResponse.<UserResponse>builder().status("success").data(userResponse).build();
    }

    @PatchMapping(
            path = "/api/users/current",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BaseResponse<UserResponse> update(User user, @RequestBody UpdateUserRequest request) {
        UserResponse userResponse = userService.update(user, request);
        return BaseResponse.<UserResponse>builder().status("success").data(userResponse).build();
    }

}
