package com.ruoyi.web.controller.patient;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.patient.domain.Patient;
import com.ruoyi.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 9:03
 */
@RestController
@RequestMapping("/patient")
@RequiredArgsConstructor
public class PatientController extends BaseController {
    private final PatientService patientService;

    // 获取所有患者列表
    @GetMapping("/listAll")
    public TableDataInfo listAllPatients() {
        startPage();
        List<Patient> allPatients = patientService.getAllPatients();
        return getDataTable(allPatients);
    }

    // 根据患者ID获取详情
    @GetMapping("/detail")
    public AjaxResult getPatientById(@RequestParam Long patientId) {
        return AjaxResult.success(patientService.getPatientById(patientId));
    }

    // 添加患者
    @PostMapping("/add")
    public AjaxResult addPatient(@RequestBody Patient patient) {
        return toAjax(patientService.addPatient(patient));
    }

    // 更新患者信息
    @PostMapping("/update")
    public AjaxResult updatePatient(@RequestBody Patient patient) {
        return toAjax(patientService.updatePatient(patient));
    }

    // 删除患者
    @GetMapping("/delete")
    public AjaxResult deletePatient(@RequestParam Long patientId) {
        return toAjax(patientService.deletePatient(patientId));
    }
}
