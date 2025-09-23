package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.MedicationLog;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 17:09
 */
public interface MedicationLogService {
    /**
     * 获取所有服药打卡记录
     *
     * @return 服药打卡记录列表
     */
    List<MedicationLog> getAllMedicationLogs();

    /**
     * 根据打卡ID获取记录
     *
     * @param id 打卡ID
     * @return 服药打卡记录
     */
    MedicationLog getMedicationLogById(Long id);

    /**
     * 根据用药记录ID获取打卡记录列表
     *
     * @param recordId 用药记录ID
     * @return 服药打卡记录列表
     */
    List<MedicationLog> getMedicationLogsByRecordId(Long recordId);

    /**
     * 添加服药打卡记录
     *
     * @param medicationLog 待添加的打卡记录
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addMedicationLog(MedicationLog medicationLog);

    /**
     * 更新服药打卡记录
     *
     * @param medicationLog 待更新的打卡记录
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateMedicationLog(MedicationLog medicationLog);

    /**
     * 删除服药打卡记录
     *
     * @param id 打卡ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteMedicationLog(Long id);
}
