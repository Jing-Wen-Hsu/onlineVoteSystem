package onlineVoteSystem.onlineVoteSystem.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteRequestDTO;
import onlineVoteSystem.onlineVoteSystem.entity.User;
import onlineVoteSystem.onlineVoteSystem.entity.VoteItem;
import onlineVoteSystem.onlineVoteSystem.repository.UserRepository;
import onlineVoteSystem.onlineVoteSystem.repository.VoteItemRepository;
import onlineVoteSystem.onlineVoteSystem.repository.VoteRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteRecordService {

    private final VoteRecordRepository voteRecordRepository;
    private final VoteItemRepository voteItemRepository;
    private  final UserRepository userRepository;

    public VoteRecordService(VoteRecordRepository voteRecordRepository, VoteItemRepository voteItemRepository, UserRepository userRepository) {
        this.voteRecordRepository = voteRecordRepository;
        this.voteItemRepository = voteItemRepository;
        this.userRepository = userRepository;
    }

    // 新增投票紀錄
    @Transactional
    public void processVotes(List<String> voteItems, String username) throws JsonProcessingException {
        // 將 List<String> 轉換為 JSON 字符串
        String voteItemsJson = new ObjectMapper().writeValueAsString(voteItems);

        // 調用儲存過程來處理投票
        voteRecordRepository.processVote(username, voteItemsJson);

    }
}
