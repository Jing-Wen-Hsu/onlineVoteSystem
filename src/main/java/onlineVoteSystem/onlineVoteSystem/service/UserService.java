package onlineVoteSystem.onlineVoteSystem.service;

import jakarta.validation.Valid;
import onlineVoteSystem.onlineVoteSystem.dto.register.RegisterDTO;
import onlineVoteSystem.onlineVoteSystem.repository.UserRepository;
import onlineVoteSystem.onlineVoteSystem.utils.PasswordUtil;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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

}