package onlineVoteSystem.onlineVoteSystem.service;


import jakarta.transaction.Transactional;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsGetDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteItemCreateDTO;
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
    public List<VoteDetailsGetDTO> getAllVoteItems() {
        List<Object[]> results = voteItemRepository.findAllByOrderByVoteItemCountDesc();

        // 手動映射結果為 DTO
        List<VoteDetailsGetDTO> voteDetailsGetDTOS = new ArrayList<>();
        for (Object[] result : results) {
            String voteItemName = (String) result[0]; // 投票項目名稱
            Integer voteItemCount = (Integer) result[1]; // 投票次數
            voteDetailsGetDTOS.add(new VoteDetailsGetDTO(voteItemName, voteItemCount));
        }

        return voteDetailsGetDTOS;
    }

    // 新增投票項目
    public void addVoteItem(VoteItemCreateDTO voteItemCreateDTO) {
        // 呼叫 Repository 的方法來新增投票項目
        voteItemRepository.addVoteItem(voteItemCreateDTO.getVoteItemName());
    }

}
