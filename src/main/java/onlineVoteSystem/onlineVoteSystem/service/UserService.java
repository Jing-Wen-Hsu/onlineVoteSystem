package onlineVoteSystem.onlineVoteSystem.service;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import onlineVoteSystem.onlineVoteSystem.dto.login.LoginDTO;
import onlineVoteSystem.onlineVoteSystem.dto.register.RegisterDTO;
import onlineVoteSystem.onlineVoteSystem.entity.User;
import onlineVoteSystem.onlineVoteSystem.repository.UserRepository;
import onlineVoteSystem.onlineVoteSystem.utils.PasswordUtil;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession session;

    public UserService(UserRepository userRepository, HttpSession session) {
        this.userRepository = userRepository;
        this.session = session;
    }

    // 註冊
    public String registerUser(@Valid RegisterDTO registerDTO) {
        // 檢查用戶是否已存在
        if (userRepository.checkUsernameExists(registerDTO.getUsername())) {
            return "用戶名已存在";
        }

        // 使用 PasswordUtil 加密密碼
        String hashedPassword = PasswordUtil.encodePassword(registerDTO.getPassword());

        // 調用儲存過程來註冊用戶
        try {
            userRepository.registerUser(registerDTO.getUsername(), hashedPassword);
            return "註冊成功";
        } catch (Exception e) {
            return e.getMessage();  // 如果用戶名已存在，會觸發錯誤
        }
    }

    // 登入
    @Transactional
    public String loginUser(LoginDTO loginDTO, HttpSession session) {
        // 根據用戶名查詢用戶
        User user = userRepository.findUserByUsername(loginDTO.getUsername());

        if (user == null || !PasswordUtil.matchPassword(loginDTO.getPassword(), user.getPassword())) {
            return "該用戶不存在或密碼錯誤";
        }
        // 登入成功後，設置會話
        session.setAttribute("user", user);  // 保存用戶對象到會話中（或保存用戶ID）
        return "登入成功";
    }

    // 登出
    public String logoutUser(HttpSession session) {
        // 清除 session 中的用戶資料
        session.invalidate();  // 使會話無效，清除所有存儲的資料
        return "登出成功";
    }

    // 查詢 Session 中的用戶資料
    public String checkUserSession() {
        // 從 session 中獲取用戶資料（例如：username 或 User 物件）
        User user = (User) session.getAttribute("user");

        if (user != null) {
            return "當前登入用戶: " + user.getUsername() + "，Session ID: " + session.getId();  // 返回用戶名
        } else {
            return "未登入";  // 如果 session 中沒有用戶資料
        }
    }
}