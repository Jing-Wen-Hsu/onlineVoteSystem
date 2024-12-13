package onlineVoteSystem.onlineVoteSystem.controller;

import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsDeleteDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsGetDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsCreateDTO;
import onlineVoteSystem.onlineVoteSystem.service.VoteItemService;
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
    public ResponseEntity<String> addVoteItem(@RequestBody VoteDetailsCreateDTO voteDetailsCreateDTO) {

       String response =  voteItemService.addVoteItem(voteDetailsCreateDTO);
        return ResponseEntity.ok(response);
    }

    //刪除投票項目
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteVoteItem(@RequestBody VoteDetailsDeleteDTO voteDetailsDeleteDTO) {

        String response =  voteItemService.deleteVoteItem(voteDetailsDeleteDTO);
        return ResponseEntity.ok(response);
    }
}