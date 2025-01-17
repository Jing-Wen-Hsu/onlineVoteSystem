package onlineVoteSystem.onlineVoteSystem.repository;

import onlineVoteSystem.onlineVoteSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 檢查用戶是否投過票
    @Procedure(procedureName = "sp_check_username_exists")
    boolean checkUsernameExists(@Param("p_username") String username);

    // 檢查用戶是否已存在(註冊)
    @Procedure(procedureName = "sp_check_user_exists")
    boolean checkUserExists(@Param("p_username") String username);

    // 註冊
    // 使用 @Procedure 註解來調用儲存過程
    @Procedure(procedureName  = "sp_register_user")  // 這裡使用儲存過程的名稱
    void registerUser(String username, String password);

    // 使用用戶名稱尋找用戶
    @Procedure(procedureName = "sp_find_user_by_username")
    User findUserByUsername(@Param("p_username") String username);
}