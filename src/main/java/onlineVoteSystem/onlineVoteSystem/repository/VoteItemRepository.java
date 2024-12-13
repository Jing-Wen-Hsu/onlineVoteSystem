package onlineVoteSystem.onlineVoteSystem.repository;

import onlineVoteSystem.onlineVoteSystem.entity.VoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteItemRepository extends JpaRepository <VoteItem,Long> {

    //查詢
    @Procedure(procedureName  = "sp_get_all_vote_items")
    List<Object[]> findAllByOrderByVoteItemCountDesc();

    // 新增
    @Procedure(procedureName = "sp_add_vote_item")
    void addVoteItem(@Param("vote_item_name") String voteItemName);

    // 刪除
    @Procedure(procedureName = "sp_delete_vote_item")
    void deleteVoteItem(@Param("p_vote_item_name") String voteItemName);




    // 檢查項目名稱是否存在
    @Procedure(procedureName = "sp_exists_vote_item_name")
    boolean existsByVoteItemName(@Param("p_vote_item_name") String voteItemName);

}
