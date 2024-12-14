package onlineVoteSystem.onlineVoteSystem.repository;


import onlineVoteSystem.onlineVoteSystem.entity.VoteRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;


public interface VoteRecordRepository extends JpaRepository<VoteRecord,Long> {

        @Procedure(procedureName = "sp_process_vote")
        void processVote(@Param("p_username") String username, @Param("p_vote_items") String voteItems);
    }
