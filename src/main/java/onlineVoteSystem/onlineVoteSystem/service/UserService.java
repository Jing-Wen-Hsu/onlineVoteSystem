package onlineVoteSystem.onlineVoteSystem.service;

import onlineVoteSystem.onlineVoteSystem.repository.UserRepository;
import onlineVoteSystem.onlineVoteSystem.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(String username, String password) {
        // 檢查用戶是否已存在
        if (userRepository.existsByUsername(username)) {
            return "用戶名已存在";
        }

        // 使用 PasswordUtil 加密密碼
        String hashedPassword = PasswordUtil.encodePassword(password);

        // 調用儲存過程來註冊用戶
        userRepository.registerUser(username, hashedPassword);

        return "註冊成功";
    }
}