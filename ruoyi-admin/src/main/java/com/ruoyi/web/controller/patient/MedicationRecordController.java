package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.MedicationRecord;
import com.ruoyi.patient.service.MedicationRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 15:54
 */
@RestController
@RequestMapping("/medicationRecord")
@RequiredArgsConstructor
public class MedicationRecordController extends BaseController {
    private final MedicationRecordService medicationRecordService;

    // 获取所有用药记录列表
    @GetMapping("/listAll")
    public TableDataInfo listAllMedicationRecords() {
        startPage();
        List<MedicationRecord> allRecords = medicationRecordService.getAllMedicationRecords();
        return getDataTable(allRecords);
    }

    // 根据用药记录ID获取详情
    @GetMapping("/detail")
    public AjaxResult getMedicationRecordById(@RequestParam Long recordId) {
        return AjaxResult.success(medicationRecordService.getMedicationRecordById(recordId));
    }

    // 根据患者ID获取用药记录列表
    @GetMapping("/listByPatientId")
    public TableDataInfo getMedicationRecordsByPatientId(@RequestParam Long patientId) {
        startPage();
        List<MedicationRecord> records = medicationRecordService.getMedicationRecordsByPatientId(patientId);
        return getDataTable(records);
    }

    // 根据药品ID获取用药记录列表
    @GetMapping("/listByDrugId")
    public TableDataInfo getMedicationRecordsByDrugId(@RequestParam Long drugId) {
        startPage();
        List<MedicationRecord> records = medicationRecordService.getMedicationRecordsByDrugId(drugId);
        return getDataTable(records);
    }

    // 添加用药记录
    @PostMapping("/add")
    public AjaxResult addMedicationRecord(@RequestBody MedicationRecord medicationRecord) {
        return toAjax(medicationRecordService.addMedicationRecord(medicationRecord));
    }

    // 更新用药记录
    @PostMapping("/update")
    public AjaxResult updateMedicationRecord(@RequestBody MedicationRecord medicationRecord) {
        return toAjax(medicationRecordService.updateMedicationRecord(medicationRecord));
    }

    // 删除用药记录
    @GetMapping("/delete")
    public AjaxResult deleteMedicationRecord(@RequestParam Long recordId) {
        return toAjax(medicationRecordService.deleteMedicationRecord(recordId));
    }
}
