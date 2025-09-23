package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.MedicationRecord;

import java.util.List;

/**
 * 患者用药记录管理 Service 接口
 *
 * @Author 范佳兴
 * @date 2025/9/23 17:04
 */
public interface MedicationRecordService {

    /**
     * 获取所有用药记录
     *
     * @return 用药记录列表
     */
    List<MedicationRecord> getAllMedicationRecords();

    /**
     * 根据用药记录ID获取信息
     *
     * @param id 用药记录ID
     * @return 用药记录信息
     */
    MedicationRecord getMedicationRecordById(Long id);

    /**
     * 根据患者ID获取用药记录
     *
     * @param patientId 患者ID
     * @return 用药记录列表
     */
    List<MedicationRecord> getMedicationRecordsByPatientId(Long patientId);

    /**
     * 根据药品ID获取用药记录
     *
     * @param drugId 药品ID
     * @return 用药记录列表
     */
    List<MedicationRecord> getMedicationRecordsByDrugId(Long drugId);

    /**
     * 添加用药记录
     *
     * @param medicationRecord 待添加的用药记录
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addMedicationRecord(MedicationRecord medicationRecord);

    /**
     * 更新用药记录
     *
     * @param medicationRecord 待更新的用药记录
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateMedicationRecord(MedicationRecord medicationRecord);

    /**
     * 删除用药记录
     *
     * @param id 用药记录ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteMedicationRecord(Long id);
}
