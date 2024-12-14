package onlineVoteSystem.onlineVoteSystem.dto.vote;

import java.util.List;

public class VoteRequestDTO {

    private List<String> voteItemName;

    public VoteRequestDTO() {
    }

    public VoteRequestDTO(List<String> voteItemName) {
        this.voteItemName = voteItemName;
    }

    public List<String> getVoteItemName() {
        return voteItemName;
    }

    public void setVoteItemName(List<String> voteItemName) {
        this.voteItemName = voteItemName;
    }
}
