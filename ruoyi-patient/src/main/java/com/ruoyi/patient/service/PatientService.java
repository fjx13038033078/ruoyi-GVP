package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.Patient;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 16:53
 */
public interface PatientService {
    /**
     * 获取所有患者
     *
     * @return 患者列表
     */
    List<Patient> getAllPatients();

    /**
     * 根据ID获取患者
     *
     * @param id 患者ID
     * @return 患者信息
     */
    Patient getPatientById(Long id);

    /**
     * 添加患者
     *
     * @param patient 待添加的患者
     * @return 添加成功返回 true，否则 false
     */
    boolean addPatient(Patient patient);

    /**
     * 更新患者
     *
     * @param patient 待更新的患者
     * @return 更新成功返回 true，否则 false
     */
    boolean updatePatient(Patient patient);

    /**
     * 删除患者
     *
     * @param id 患者ID
     * @return 删除成功返回 true，否则 false
     */
    boolean deletePatient(Long id);
}
