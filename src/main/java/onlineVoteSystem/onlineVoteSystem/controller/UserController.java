package onlineVoteSystem.onlineVoteSystem.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import onlineVoteSystem.onlineVoteSystem.dto.login.LoginDTO;
import onlineVoteSystem.onlineVoteSystem.dto.register.RegisterDTO;
import onlineVoteSystem.onlineVoteSystem.entity.User;
import onlineVoteSystem.onlineVoteSystem.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 用戶註冊
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDTO registerDTO) {
        return ResponseEntity.ok(userService.registerUser(registerDTO));
    }

    // 用戶登入
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
        // 調用 UserService 進行登入邏輯處理，並將 session 傳遞給 service 層
        String loginMessage = userService.loginUser(loginDTO, session);

        if ("登入成功".equals(loginMessage)) {
            return ResponseEntity.ok(loginMessage);  // 登入成功
        } else {
            return ResponseEntity.status(400).body(loginMessage);  // 登入失敗，返回錯誤訊息
        }
    }

    // 用戶登出
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // 呼叫 UserService 來登出
        String logoutMessage = userService.logoutUser(session);
        return ResponseEntity.ok(logoutMessage);  // 登出成功
    }

    // 查詢 Session 中的用戶
    @GetMapping("/checkSession")
    public ResponseEntity<String> checkSession() {
        String sessionMessage = userService.checkUserSession();

        if ("未登入".equals(sessionMessage)) {
            return ResponseEntity.status(400).body(sessionMessage);  // 返回未登入的狀態
        }

        return ResponseEntity.ok(sessionMessage);  // 返回登入用戶
    }
}