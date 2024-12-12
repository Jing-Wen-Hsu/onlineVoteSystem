package onlineVoteSystem.onlineVoteSystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vote_topic") //投票主題
public class VoteTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voteTopicId; // 主題ID

    @Column(nullable = false, length = 500)
    private String voteTopicName; // 主題名稱

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user; // 投票發起者 關聯到用戶

    public VoteTopic() {
    }

    public VoteTopic(Long voteTopicId, String voteTopicName, User user) {
        this.voteTopicId = voteTopicId;
        this.voteTopicName = voteTopicName;
        this.user = user;
    }

    public Long getVoteTopicId() {
        return voteTopicId;
    }

    public void setVoteTopicId(Long voteTopicId) {
        this.voteTopicId = voteTopicId;
    }

    public String getVoteTopicName() {
        return voteTopicName;
    }

    public void setVoteTopicName(String voteTopicName) {
        this.voteTopicName = voteTopicName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}