package onlineVoteSystem.onlineVoteSystem.dto.login;

import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {

    @NotEmpty(message = "姓名為必填項目")
    private String username; // 用戶名

    @NotEmpty(message = "密碼為必填項目")
    private String password; // 密碼

    public LoginDTO() {
    }

    public LoginDTO(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
