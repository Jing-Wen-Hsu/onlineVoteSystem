package onlineVoteSystem.onlineVoteSystem.controller;

import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsGetDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteItemCreateDTO;
import onlineVoteSystem.onlineVoteSystem.service.VoteItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteItemController {

    private final VoteItemService voteItemService;

    public VoteItemController(VoteItemService voteItemService) {
        this.voteItemService = voteItemService;
    }

    // 獲取所有投票項目與投票數
    @GetMapping ("/getDatas")
    public ResponseEntity<List<VoteDetailsGetDTO>> getAllVoteItems() {
        List<VoteDetailsGetDTO> voteItems = voteItemService.getAllVoteItems();
        return ResponseEntity.ok(voteItems);
    }

    // 新增投票項目
    @PostMapping("/add")
    public ResponseEntity<Void> addVoteItem(@RequestBody VoteItemCreateDTO voteItemCreateDTO) {
        voteItemService.addVoteItem(voteItemCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}