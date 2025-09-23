package com.ruoyi.patient.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.ruoyi.patient.domain.AdverseReactionFeedback;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 17:47
 */
@Mapper
public interface AdverseReactionFeedbackMapper {
    /**
     * 获取所有反馈记录
     *
     * @return 反馈列表
     */
    List<AdverseReactionFeedback> getAllFeedbacks();

    /**
     * 根据反馈ID获取信息
     *
     * @param id 反馈ID
     * @return 反馈信息
     */
    AdverseReactionFeedback getFeedbackById(Long id);

    /**
     * 根据不良反应ID获取反馈列表
     *
     * @param reactionId 不良反应ID
     * @return 反馈列表
     */
    List<AdverseReactionFeedback> getFeedbacksByReactionId(Long reactionId);

    /**
     * 添加反馈记录
     *
     * @param feedback 待添加的反馈
     * @return 影响行数
     */
    int addFeedback(AdverseReactionFeedback feedback);

    /**
     * 更新反馈记录
     *
     * @param feedback 待更新的反馈
     * @return 影响行数
     */
    int updateFeedback(AdverseReactionFeedback feedback);

    /**
     * 删除反馈记录
     *
     * @param id 反馈ID
     * @return 影响行数
     */
    int deleteFeedback(Long id);
}
