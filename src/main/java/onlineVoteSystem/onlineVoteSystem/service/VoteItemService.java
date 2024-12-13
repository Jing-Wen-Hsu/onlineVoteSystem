package onlineVoteSystem.onlineVoteSystem.service;


import jakarta.transaction.Transactional;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsDTO;
import onlineVoteSystem.onlineVoteSystem.repository.VoteItemRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VoteItemService {
    private final VoteItemRepository voteItemRepository;

    public VoteItemService(VoteItemRepository voteItemRepository) {
        this.voteItemRepository = voteItemRepository;
    }

    // 取得所有投票項目及其計數
    @Transactional
    public List<VoteDetailsDTO> getAllVoteItems() {
        List<Object[]> results = voteItemRepository.findAllByOrderByVoteItemCountDesc();

        // 手動映射結果為 DTO
        List<VoteDetailsDTO> voteDetailsDTOs = new ArrayList<>();
        for (Object[] result : results) {
            String voteItemName = (String) result[0]; // 投票項目名稱
            Integer voteItemCount = (Integer) result[1]; // 投票次數
            voteDetailsDTOs.add(new VoteDetailsDTO(voteItemName, voteItemCount));
        }

        return voteDetailsDTOs;
    }
}
