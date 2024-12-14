package onlineVoteSystem.onlineVoteSystem.service;


import jakarta.transaction.Transactional;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsDeleteDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsGetDTO;
import onlineVoteSystem.onlineVoteSystem.dto.vote.VoteDetailsCreateDTO;
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
    public String addVoteItem(VoteDetailsCreateDTO voteDetailsCreateDTO) {
        // 呼叫 Repository 的方法來新增投票項目
        // 檢查 voteItemName 是否已存在
        if (voteItemRepository.existsByVoteItemName(voteDetailsCreateDTO.getVoteItemName())) {
            return "投票項目名稱已存在";
        } else {
            voteItemRepository.addVoteItem(voteDetailsCreateDTO.getVoteItemName());
            return "新增投票項目成功";
        }
    }

    // 刪除投票項目
    @Transactional
    public String deleteVoteItem(VoteDetailsDeleteDTO voteDetailsDeleteDTO) {
        // 檢查投票項目是否存在
        if (voteItemRepository.existsByVoteItemName(voteDetailsDeleteDTO.getVoteItemName())) {
            // 刪除投票項目
            voteItemRepository.deleteVoteItem(voteDetailsDeleteDTO.getVoteItemName());
            return "刪除投票項目成功";
        } else {
            return "投票項目不存在";
        }
    }
}