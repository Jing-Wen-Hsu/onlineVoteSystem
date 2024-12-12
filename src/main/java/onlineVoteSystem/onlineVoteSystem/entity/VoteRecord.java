package onlineVoteSystem.onlineVoteSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "vote_records")
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增
    @Column(name = "vote_record_id")
    private Long voteRecordId;

    @Size(min = 2, max = 100, message = "投票者姓名長度應介於 2 到 100 之間")
    @Column(name = "voter" , nullable = false)
    private String voter;

    @ManyToOne
    @JoinColumn(name = "vote_item_id", referencedColumnName = "vote_item_id", nullable = false)
    private VoteItem voteItem;

    public VoteRecord() {
    }

    public VoteRecord(Long voteRecordId, String voter, Long voteItemId, VoteItem voteItem) {
        this.voteRecordId = voteRecordId;
        this.voter = voter;
        this.voteItem = voteItem;
    }

    public Long getVoteRecordId() {
        return voteRecordId;
    }

    public void setVoteRecordId(Long voteRecordId) {
        this.voteRecordId = voteRecordId;
    }

    public String getVoter() {
        return voter;
    }

    public void setVoter(String voter) {
        this.voter = voter;
    }


    public VoteItem getVoteItem() {
        return voteItem;
    }

    public void setVoteItem(VoteItem voteItem) {
        this.voteItem = voteItem;
    }
}
