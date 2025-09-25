package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.AdverseReaction;
import com.ruoyi.patient.service.AdverseReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 17:41
 */
@RestController
@RequestMapping("/adverseReaction")
@RequiredArgsConstructor
public class AdverseReactionController extends BaseController {
    private final AdverseReactionService adverseReactionService;

    // 获取所有不良反应记录
    @GetMapping("/listAll")
    public TableDataInfo listAllAdverseReactions() {
        startPage();
        List<AdverseReaction> reactions = adverseReactionService.getAllAdverseReactions();
        return getDataTable(reactions);
    }

    // 根据ID获取单条不良反应记录
    @GetMapping("/detail")
    public AjaxResult getAdverseReactionById(@RequestParam Long id) {
        return AjaxResult.success(adverseReactionService.getAdverseReactionById(id));
    }

    // 根据患者ID获取不良反应列表
    @GetMapping("/listByPatientId")
    public TableDataInfo getAdverseReactionsByPatientId(@RequestParam Long patientId) {
        startPage();
        List<AdverseReaction> reactions = adverseReactionService.getAdverseReactionsByPatientId(patientId);
        return getDataTable(reactions);
    }

    // 根据药品ID获取不良反应列表
    @GetMapping("/listByDrugId")
    public TableDataInfo getAdverseReactionsByDrugId(@RequestParam Long drugId) {
        startPage();
        List<AdverseReaction> reactions = adverseReactionService.getAdverseReactionsByDrugId(drugId);
        return getDataTable(reactions);
    }

    // 添加不良反应记录
    @PostMapping("/add")
    public AjaxResult addAdverseReaction(@RequestBody AdverseReaction adverseReaction) {
        return toAjax(adverseReactionService.addAdverseReaction(adverseReaction));
    }

    // 更新不良反应记录
    @PostMapping("/update")
    public AjaxResult updateAdverseReaction(@RequestBody AdverseReaction adverseReaction) {
        return toAjax(adverseReactionService.updateAdverseReaction(adverseReaction));
    }

    //修改医生确认状态
    @PostMapping("/updateDoctorConfirmed")
    public AjaxResult updateDoctorConfirmed(@RequestBody AdverseReaction adverseReaction) {
        return toAjax(adverseReactionService.updateDoctorConfirmed(adverseReaction));
    }

    // 删除不良反应记录
    @GetMapping("/delete")
    public AjaxResult deleteAdverseReaction(@RequestParam Long id) {
        return toAjax(adverseReactionService.deleteAdverseReaction(id));
    }
}
