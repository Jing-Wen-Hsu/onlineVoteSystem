package onlineVoteSystem.onlineVoteSystem.dto.vote;

public class VoteDetailsDTO {
    private String voteItemName;
    private Integer voteItemCount;

    public VoteDetailsDTO() {
    }

    public VoteDetailsDTO(String voteItemName, Integer voteItemCount) {
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
