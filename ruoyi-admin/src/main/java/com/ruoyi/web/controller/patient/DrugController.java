package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.Drug;
import com.ruoyi.patient.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 14:58
 */
@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor
public class DrugController extends BaseController {
    private final DrugService drugService;

    // 获取所有药品列表
    @GetMapping("/listAll")
    public TableDataInfo listAllDrugs() {
        startPage();
        List<Drug> allDrugs = drugService.getAllDrugs();
        return getDataTable(allDrugs);
    }

    // 根据药品ID获取详情
    @GetMapping("/detail")
    public AjaxResult getDrugById(@RequestParam Long drugId) {
        return AjaxResult.success(drugService.getDrugById(drugId));
    }

    // 添加药品
    @PostMapping("/add")
    public AjaxResult addDrug(@RequestBody Drug drug) {
        return toAjax(drugService.addDrug(drug));
    }

    // 更新药品信息
    @PostMapping("/update")
    public AjaxResult updateDrug(@RequestBody Drug drug) {
        return toAjax(drugService.updateDrug(drug));
    }

    // 删除药品
    @GetMapping("/delete")
    public AjaxResult deleteDrug(@RequestParam Long drugId) {
        return toAjax(drugService.deleteDrug(drugId));
    }
}
