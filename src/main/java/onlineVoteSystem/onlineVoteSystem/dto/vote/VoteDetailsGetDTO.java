package onlineVoteSystem.onlineVoteSystem.dto.vote;

public class VoteDetailsGetDTO extends VoteDetailsBaseDTO {

    public VoteDetailsGetDTO() {
    }

    public VoteDetailsGetDTO(String voteItemName, Integer voteItemCount) {
        super(voteItemName, voteItemCount);
    }
}
