package onlineVoteSystem.onlineVoteSystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
public class VoteItems {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // 生成策略，根據資料庫來決定（例如自增）
    @Column(name = "vote_item_id")
    private Long voteItemId;

    @NotEmpty(message = "投票項目為必填項目")
    @Size(min = 1, max = 100, message = "投票項目名稱長度應介於 1 到 100 之間")
    @Column(name = "vote_item_name", length = 100)
    private String voteItemName;



}
