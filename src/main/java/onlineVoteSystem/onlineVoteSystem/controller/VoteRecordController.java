package onlineVoteSystem.onlineVoteSystem.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpSession;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsCreateDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteRequestDTO;
import onlineVoteSystem.onlineVoteSystem.entity.User;
import onlineVoteSystem.onlineVoteSystem.service.VoteRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VoteRecordController {

    private final VoteRecordService voteRecordService;
    private final HttpSession session;

    public VoteRecordController(VoteRecordService voteRecordService, HttpSession session) {
        this.voteRecordService = voteRecordService;
        this.session = session;
    }

    // 新增投票紀錄
    @PostMapping("/addRecords")
    public ResponseEntity<?> vote(@RequestBody VoteRequestDTO voteRequestDTO) throws JsonProcessingException {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return ResponseEntity.status(401).body("未登入，請先登入");
        }

        // 直接取得投票項目列表
        List<String> voteItems = voteRequestDTO.getVoteItemName();

        voteRecordService.processVotes(voteItems, user.getUsername());
        return ResponseEntity.ok("投票成功");
    }

}