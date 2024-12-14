package onlineVoteSystem.onlineVoteSystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote_records")
public class VoteRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 自增
    @Column(name = "vote_record_id")
    private Long voteRecordId;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;  // 每個投票紀錄都與一位使用者關聯

    @ManyToOne
    @JoinColumn(name = "vote_item_id", referencedColumnName = "vote_item_id", nullable = false)
    private VoteItem voteItem;

    public VoteRecord() {
    }

    public VoteRecord(Long voteRecordId, User user, VoteItem voteItem) {
        this.voteRecordId = voteRecordId;
        this.user = user;
        this.voteItem = voteItem;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getVoteRecordId() {
        return voteRecordId;
    }

    public void setVoteRecordId(Long voteRecordId) {
        this.voteRecordId = voteRecordId;
    }


    public VoteItem getVoteItem() {
        return voteItem;
    }

    public void setVoteItem(VoteItem voteItem) {
        this.voteItem = voteItem;
    }
}
