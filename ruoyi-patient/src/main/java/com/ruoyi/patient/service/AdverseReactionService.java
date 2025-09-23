package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.AdverseReaction;

import java.util.List;

/**
 *
 * 不良反应管理 Service 接口
 *
 * @Author 范佳兴
 * @date 2025/9/23 17:44
 */
public interface AdverseReactionService {
    /**
     * 获取所有不良反应记录
     *
     * @return 不良反应列表
     */
    List<AdverseReaction> getAllAdverseReactions();

    /**
     * 根据ID获取不良反应信息
     *
     * @param id 不良反应ID
     * @return 不良反应信息
     */
    AdverseReaction getAdverseReactionById(Long id);

    /**
     * 根据患者ID获取不良反应列表
     *
     * @param patientId 患者ID
     * @return 不良反应列表
     */
    List<AdverseReaction> getAdverseReactionsByPatientId(Long patientId);

    /**
     * 根据药品ID获取不良反应列表
     *
     * @param drugId 药品ID
     * @return 不良反应列表
     */
    List<AdverseReaction> getAdverseReactionsByDrugId(Long drugId);

    /**
     * 添加不良反应记录
     *
     * @param adverseReaction 待添加的不良反应
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addAdverseReaction(AdverseReaction adverseReaction);

    /**
     * 更新不良反应记录
     *
     * @param adverseReaction 待更新的不良反应
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateAdverseReaction(AdverseReaction adverseReaction);

    /**
     * 删除不良反应记录
     *
     * @param id 不良反应ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteAdverseReaction(Long id);
}
