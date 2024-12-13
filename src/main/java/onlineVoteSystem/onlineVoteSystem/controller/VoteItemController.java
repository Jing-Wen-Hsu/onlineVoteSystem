package onlineVoteSystem.onlineVoteSystem.controller;

import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsDTO;
import onlineVoteSystem.onlineVoteSystem.service.VoteItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteItemController {

    private final VoteItemService voteItemService;

    public VoteItemController(VoteItemService voteItemService) {
        this.voteItemService = voteItemService;
    }

    // 提供取得所有投票項目名稱和計數的端點
    @GetMapping ("/getDatas")
    public ResponseEntity<List<VoteDetailsDTO>> getAllVoteItems() {
        List<VoteDetailsDTO> voteItems = voteItemService.getAllVoteItems();
        return ResponseEntity.ok(voteItems);
    }

}