package onlineVoteSystem.onlineVoteSystem.repository;

import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsDTO;
import onlineVoteSystem.onlineVoteSystem.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteItemRepository extends JpaRepository <VoteItem,Long> {

    @Procedure(procedureName  = "sp_get_all_vote_items")
    List<Object[]> findAllByOrderByVoteItemCountDesc();
}
