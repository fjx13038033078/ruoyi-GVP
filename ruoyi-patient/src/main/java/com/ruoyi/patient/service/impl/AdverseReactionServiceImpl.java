package com.ruoyi.patient.service.impl;

import com.ruoyi.patient.domain.AdverseReaction;
import com.ruoyi.patient.domain.Drug;
import com.ruoyi.patient.mapper.AdverseReactionMapper;
import com.ruoyi.patient.service.AdverseReactionService;
import com.ruoyi.patient.service.DrugService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 17:39
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdverseReactionServiceImpl  implements AdverseReactionService {
    private final AdverseReactionMapper adverseReactionMapper;

    private final DrugService drugService;

    private final ISysUserService sysUserService;

    @Override
    public List<AdverseReaction> getAllAdverseReactions() {
        List<AdverseReaction> reactions = adverseReactionMapper.getAllAdverseReactions();
        fillExtraInfo(reactions);
        return reactions;
    }

    @Override
    public AdverseReaction getAdverseReactionById(Long id) {
        AdverseReaction reaction = adverseReactionMapper.getAdverseReactionById(id);
        fillExtraInfo(reaction);
        return reaction;
    }

    @Override
    public List<AdverseReaction> getAdverseReactionsByPatientId(Long patientId) {
        List<AdverseReaction> reactions = adverseReactionMapper.getAdverseReactionsByPatientId(patientId);
        fillExtraInfo(reactions);
        return reactions;
    }

    @Override
    public List<AdverseReaction> getAdverseReactionsByDrugId(Long drugId) {
        List<AdverseReaction> reactions = adverseReactionMapper.getAdverseReactionsByDrugId(drugId);
        fillExtraInfo(reactions);
        return reactions;
    }

    @Override
    public boolean addAdverseReaction(AdverseReaction adverseReaction) {
        int rows = adverseReactionMapper.addAdverseReaction(adverseReaction);
        return rows > 0;
    }

    @Override
    public boolean updateAdverseReaction(AdverseReaction adverseReaction) {
        int rows = adverseReactionMapper.updateAdverseReaction(adverseReaction);
        return rows > 0;
    }

    @Override
    public boolean deleteAdverseReaction(Long id) {
        int rows = adverseReactionMapper.deleteAdverseReaction(id);
        return rows > 0;
    }

    /**
     * 批量填充额外信息（例如药品名称、患者姓名等）
     *
     * @param reactions 不良反应列表
     */
    private void fillExtraInfo(List<AdverseReaction> reactions) {
        for (AdverseReaction reaction : reactions) {
            fillExtraInfo(reaction);
        }
    }

    /**
     * 填充单条不良反应记录的额外信息
     *
     * @param reaction 不良反应对象
     */
    private void fillExtraInfo(AdverseReaction reaction) {
        if (reaction == null) {
            return;
        }
        // 填充药品名称
        Drug drugById = drugService.getDrugById(reaction.getDrugId());
        reaction.setDrugName(drugById.getDrugName());
        // 填充患者姓名
        reaction.setPatientName(sysUserService.selectUserById(reaction.getPatientId()).getNickName());
    }
}
