package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.AdverseReactionFeedback;
import com.ruoyi.patient.service.AdverseReactionFeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 不良反应反馈管理 Controller
 *
 * @Author 范佳兴
 * @date 2025/9/25 9:53
 */

@RestController
@RequestMapping("/adverseReactionFeedback")
@RequiredArgsConstructor
public class AdverseReactionFeedbackController extends BaseController {
    private final AdverseReactionFeedbackService adverseReactionFeedbackService;

    // 获取所有反馈记录
    @GetMapping("/listAll")
    public TableDataInfo listAllFeedbacks() {
        startPage();
        List<AdverseReactionFeedback> list = adverseReactionFeedbackService.getAllFeedbacks();
        return getDataTable(list);
    }

    // 根据ID获取单条反馈记录
    @GetMapping("/detail")
    public AjaxResult getFeedbackById(@RequestParam Long id) {
        return AjaxResult.success(adverseReactionFeedbackService.getFeedbackById(id));
    }

    // 根据不良反应ID获取反馈列表
    @GetMapping("/listByReactionId")
    public TableDataInfo getFeedbacksByReactionId(@RequestParam Long reactionId) {
        startPage();
        List<AdverseReactionFeedback> list = adverseReactionFeedbackService.getFeedbacksByReactionId(reactionId);
        return getDataTable(list);
    }

    // 添加反馈记录
    @PostMapping("/add")
    public AjaxResult addFeedback(@RequestBody AdverseReactionFeedback feedback) {
        return toAjax(adverseReactionFeedbackService.addFeedback(feedback));
    }

    // 更新反馈记录
    @PostMapping("/update")
    public AjaxResult updateFeedback(@RequestBody AdverseReactionFeedback feedback) {
        return toAjax(adverseReactionFeedbackService.updateFeedback(feedback));
    }

    // 删除反馈记录
    @GetMapping("/delete")
    public AjaxResult deleteFeedback(@RequestParam Long id) {
        return toAjax(adverseReactionFeedbackService.deleteFeedback(id));
    }
}
