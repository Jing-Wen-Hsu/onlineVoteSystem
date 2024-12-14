package onlineVoteSystem.onlineVoteSystem.repository;


import onlineVoteSystem.onlineVoteSystem.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface VoteRecordRepository extends JpaRepository<VoteRecord,Long> {

        // 投票
        @Procedure(procedureName = "sp_process_vote")
        void processVote(@Param("p_username") String username, @Param("p_vote_items") String voteItems);

        // 檢查用戶是否已經投過票
        @Procedure(procedureName = "sp_check_vote_exists")
        void checkVoteExists(@Param("p_username") String username, @Param("p_exists") Boolean exists);

        // 檢查用戶是否已存在
        @Procedure(procedureName = "sp_check_username_exists")
        void checkUsernameExists(@Param("p_username") String username, @Param("p_exists") Boolean exists);


}
