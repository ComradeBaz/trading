package com.domrade.controllers;

import com.domrade.user.entity.User;
import com.domrade.user.service.api.IUserService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@NoArgsConstructor
@RestController
@RequestMapping("/rest/v1/user/")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping (value = "addUser")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        return new ResponseEntity(userService.addUser(user), HttpStatus.CREATED);
    }
}
