package com.ruoyi.patient.service.impl;

import com.ruoyi.patient.domain.AdverseReactionFeedback;
import com.ruoyi.patient.mapper.AdverseReactionFeedbackMapper;
import com.ruoyi.patient.service.AdverseReactionFeedbackService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/25 9:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AdverseReactionFeedbackServiceImpl  implements AdverseReactionFeedbackService {
    private final AdverseReactionFeedbackMapper feedbackMapper;

    private final ISysUserService sysUserService;

    /**
     * 获取所有反馈记录
     *
     * @return 反馈列表
     */
    @Override
    public List<AdverseReactionFeedback> getAllFeedbacks() {
        List<AdverseReactionFeedback> feedbacks = feedbackMapper.getAllFeedbacks();
        fillExtraInfo(feedbacks);
        return feedbacks;
    }

    /**
     * 根据反馈ID获取信息
     *
     * @param id 反馈ID
     * @return 反馈信息
     */
    @Override
    public AdverseReactionFeedback getFeedbackById(Long id) {
        AdverseReactionFeedback feedback = feedbackMapper.getFeedbackById(id);
        fillExtraInfo(feedback);
        return feedback;
    }

    /**
     * 根据不良反应ID获取反馈列表
     *
     * @param reactionId 不良反应ID
     * @return 反馈列表
     */
    @Override
    public List<AdverseReactionFeedback> getFeedbacksByReactionId(Long reactionId) {
        List<AdverseReactionFeedback> feedbacks = feedbackMapper.getFeedbacksByReactionId(reactionId);
        fillExtraInfo(feedbacks);
        return feedbacks;
    }

    /**
     * 添加反馈记录
     *
     * @param feedback 待添加的反馈
     * @return 是否成功
     */
    @Override
    public boolean addFeedback(AdverseReactionFeedback feedback) {
        int rows = feedbackMapper.addFeedback(feedback);
        return rows > 0;
    }

    /**
     * 更新反馈记录
     *
     * @param feedback 待更新的反馈
     * @return 是否成功
     */
    @Override
    public boolean updateFeedback(AdverseReactionFeedback feedback) {
        int rows = feedbackMapper.updateFeedback(feedback);
        return rows > 0;
    }

    /**
     * 删除反馈记录
     *
     * @param id 反馈ID
     * @return 是否成功
     */
    @Override
    public boolean deleteFeedback(Long id) {
        int rows = feedbackMapper.deleteFeedback(id);
        return rows > 0;
    }

    /**
     * 批量填充额外信息（例如医生姓名等）
     *
     * @param feedbacks 反馈列表
     */
    private void fillExtraInfo(List<AdverseReactionFeedback> feedbacks) {
        for (AdverseReactionFeedback feedback : feedbacks) {
            fillExtraInfo(feedback);
        }
    }

    /**
     * 填充单条反馈记录的额外信息
     *
     * @param feedback 反馈对象
     */
    private void fillExtraInfo(AdverseReactionFeedback feedback) {
        if (feedback == null) {
            return;
        }
        // 填充医生姓名
        feedback.setDoctorName(sysUserService.selectUserById(feedback.getDoctorId()).getNickName());
    }
}
