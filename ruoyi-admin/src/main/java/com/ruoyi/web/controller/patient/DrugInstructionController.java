package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.DrugInstruction;
import com.ruoyi.patient.service.DrugInstructionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 15:00
 */
@RestController
@RequestMapping("/drugInstruction")
@RequiredArgsConstructor
public class DrugInstructionController extends BaseController {
    private final DrugInstructionService drugInstructionService;

    // 获取所有说明书列表
    @GetMapping("/listAll")
    public TableDataInfo listAllDrugInstructions() {
        startPage();
        List<DrugInstruction> allInstructions = drugInstructionService.getAllDrugInstructions();
        return getDataTable(allInstructions);
    }

    // 根据说明书ID获取详情
    @GetMapping("/detail")
    public AjaxResult getDrugInstructionById(@RequestParam Long instructionId) {
        return AjaxResult.success(drugInstructionService.getDrugInstructionById(instructionId));
    }

    // 根据药品ID获取说明书列表
    @GetMapping("/listByDrugId")
    public TableDataInfo getDrugInstructionsByDrugId(@RequestParam Long drugId) {
        startPage();
        List<DrugInstruction> instructions = drugInstructionService.getDrugInstructionsByDrugId(drugId);
        return getDataTable(instructions);
    }

    // 添加说明书
    @PostMapping("/add")
    public AjaxResult addDrugInstruction(@RequestBody DrugInstruction drugInstruction) {
        return toAjax(drugInstructionService.addDrugInstruction(drugInstruction));
    }

    // 更新说明书信息
    @PostMapping("/update")
    public AjaxResult updateDrugInstruction(@RequestBody DrugInstruction drugInstruction) {
        return toAjax(drugInstructionService.updateDrugInstruction(drugInstruction));
    }

    // 删除说明书
    @GetMapping("/delete")
    public AjaxResult deleteDrugInstruction(@RequestParam Long instructionId) {
        return toAjax(drugInstructionService.deleteDrugInstruction(instructionId));
    }
}
