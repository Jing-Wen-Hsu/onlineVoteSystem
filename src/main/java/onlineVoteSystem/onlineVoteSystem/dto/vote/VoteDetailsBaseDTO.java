package onlineVoteSystem.onlineVoteSystem.dto.vote;


public abstract class VoteDetailsBaseDTO {
    private String voteItemName;
    private Integer voteItemCount;

    public VoteDetailsBaseDTO() {
    }

    public VoteDetailsBaseDTO(String voteItemName, Integer voteItemCount) {
        this.voteItemName = voteItemName;
        this.voteItemCount = voteItemCount;
    }

    public String getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName(String voteItemName) {
        this.voteItemName = voteItemName;
    }

    public Integer getVoteItemCount() {
        return voteItemCount;
    }

    public void setVoteItemCount(Integer voteItemCount) {
        this.voteItemCount = voteItemCount;
    }
}
