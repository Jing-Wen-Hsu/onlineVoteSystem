package onlineVoteSystem.onlineVoteSystem.dto.vote;

public class VoteItemCreateDTO  extends VoteDetailsBaseDTO{
    // 無參數構造函數，將 voteItemCount 預設為 0
    public VoteItemCreateDTO() {
        super(null, 0); // 調用父類構造函數，將 voteItemName 設為 null，voteItemCount 設為 0
    }

    // 有參數構造函數
    public VoteItemCreateDTO(String voteItemName) {
        super(voteItemName, 0); // 只提供 voteItemName，voteItemCount 預設為 0
    }

    public VoteItemCreateDTO(String voteItemName, Integer voteItemCount) {
        super(voteItemName, voteItemCount); // 如果有提供 voteItemCount，使用提供的值
    }
}
