package onlineVoteSystem.onlineVoteSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; // 用戶ID

    @Column(nullable = false, length = 500)
    @NotEmpty(message = "姓名為必填項目")
    private String username; // 用戶名

    @Column(nullable = false , length = 255)
    @NotEmpty(message = "密碼為必填項目")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$", message = "密碼必須至少8位，並包含字母和數字")
    private String password; // 密碼

    public User() {
    }

    public User(Long userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public @NotEmpty(message = "姓名為必填項目") String getUsername() {
        return username;
    }

    public void setUsername(@NotEmpty(message = "姓名為必填項目") String username) {
        this.username = username;
    }

    public @NotEmpty(message = "密碼為必填項目") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$", message = "密碼必須至少8位，並包含字母和數字") String getPassword() {
        return password;
    }

    public void setPassword(@NotEmpty(message = "密碼為必填項目") @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$", message = "密碼必須至少8位，並包含字母和數字") String password) {
        this.password = password;
    }
}