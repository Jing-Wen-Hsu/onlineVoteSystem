package onlineVoteSystem.onlineVoteSystem.repository;

import onlineVoteSystem.onlineVoteSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 檢查用戶是否已存在
    @Procedure(procedureName = "sp_check_username_exists")
    boolean checkUsernameExists(@Param("p_username") String phoneNumber);

    // 註冊
    // 使用 @Procedure 註解來調用儲存過程
    @Procedure(procedureName  = "sp_register_user")  // 這裡使用儲存過程的名稱
    void registerUser(String username, String password);

    //登入
    @Procedure(name = "sp_login_user")
    void loginUser(String username, String password, @Param("p_is_valid") Integer isValid);
}