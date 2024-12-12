package onlineVoteSystem.onlineVoteSystem.repository;

import onlineVoteSystem.onlineVoteSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 檢查用戶是否已存在
    boolean existsByUsername(String username);

    // 使用儲存過程來註冊用戶
    // 使用 @Procedure 註解來調用儲存過程
    @Procedure(procedureName  = "register_user")  // 這裡使用儲存過程的名稱
    void registerUser(String username, String password);
}