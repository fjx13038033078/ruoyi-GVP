package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.MedicationLog;
import com.ruoyi.patient.service.MedicationLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 患者服药打卡记录 Controller
 *
 * @Author 范佳兴
 * @date 2025/9/24 16:58
 */
@RestController
@RequestMapping("/medicationLog")
@RequiredArgsConstructor
public class MedicationLogController extends BaseController {
    private final MedicationLogService medicationLogService;

    // 获取所有服药打卡记录
    @GetMapping("/listAll")
    public TableDataInfo listAllMedicationLogs() {
        startPage();
        List<MedicationLog> allLogs = medicationLogService.getAllMedicationLogs();
        return getDataTable(allLogs);
    }

    // 根据打卡ID获取记录
    @GetMapping("/detail")
    public AjaxResult getMedicationLogById(@RequestParam Long logId) {
        return AjaxResult.success(medicationLogService.getMedicationLogById(logId));
    }

    // 根据用药记录ID获取打卡记录列表
    @GetMapping("/listByRecordId")
    public TableDataInfo getMedicationLogsByRecordId(@RequestParam Long recordId) {
        startPage();
        List<MedicationLog> logs = medicationLogService.getMedicationLogsByRecordId(recordId);
        return getDataTable(logs);
    }

    // 添加打卡记录
    @PostMapping("/add")
    public AjaxResult addMedicationLog(@RequestBody MedicationLog medicationLog) {
        return toAjax(medicationLogService.addMedicationLog(medicationLog));
    }

    // 更新打卡记录
    @PostMapping("/update")
    public AjaxResult updateMedicationLog(@RequestBody MedicationLog medicationLog) {
        return toAjax(medicationLogService.updateMedicationLog(medicationLog));
    }

    // 删除打卡记录
    @GetMapping("/delete")
    public AjaxResult deleteMedicationLog(@RequestParam Long logId) {
        return toAjax(medicationLogService.deleteMedicationLog(logId));
    }
}
