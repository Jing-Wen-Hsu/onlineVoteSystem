package onlineVoteSystem.onlineVoteSystem.controller;

import onlineVoteSystem.onlineVoteSystem.entity.User;
import onlineVoteSystem.onlineVoteSystem.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //用戶註冊
    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword());
    }
}