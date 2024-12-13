package onlineVoteSystem.onlineVoteSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "vote_items")
public class VoteItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增
    @Column(name = "vote_item_id")
    private Long voteItemId;

    @NotEmpty(message = "投票項目為必填項目")
    @Size(min = 2, max = 100, message = "投票項目名稱長度應介於 2 到 100 之間")
    @Column(name = "vote_item_name", length = 100 , nullable = false)
    private String voteItemName;

    @Column(nullable = false)
    private Integer voteCount  = 0;   // 記錄該投票項目的投票次數，預設為0

    @OneToMany(mappedBy = "voteItem")
    private List<VoteRecord> voteRecords;

    public VoteItem() {
    }

    public VoteItem(Long voteItemId, String voteItemName, Integer voteCount, List<VoteRecord> voteRecords) {
        this.voteItemId = voteItemId;
        this.voteItemName = voteItemName;
        this.voteCount = voteCount;
        this.voteRecords = voteRecords;
    }

    public Long getVoteItemId() {
        return voteItemId;
    }

    public void setVoteItemId(Long voteItemId) {
        this.voteItemId = voteItemId;
    }

    public String getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName (String voteItemName) {
        this.voteItemName = voteItemName;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }


    public List<VoteRecord> getVoteRecords() {
        return voteRecords;
    }

    public void setVoteRecords(List<VoteRecord> voteRecords) {
        this.voteRecords = voteRecords;
    }
}
