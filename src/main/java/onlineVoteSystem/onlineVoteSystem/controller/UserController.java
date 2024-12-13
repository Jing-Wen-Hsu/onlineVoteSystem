package onlineVoteSystem.onlineVoteSystem.controller;

import jakarta.validation.Valid;
import onlineVoteSystem.onlineVoteSystem.dto.register.RegisterDTO;
import onlineVoteSystem.onlineVoteSystem.service.UserService;
import org.springframework.http.ResponseEntity;
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
//    @PostMapping("/register")
//    public String register(@RequestBody @Valid RegisterDTO registerDTO) {
//        return userService.registerUser(registerDTO);
//    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.registerUser(registerDTO));
    }
}