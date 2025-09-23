package com.ruoyi.patient.mapper;

import com.ruoyi.patient.domain.MedicationRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 17:03
 */
@Mapper
public interface MedicationRecordMapper {
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
     * 根据患者ID获取用药记录列表
     *
     * @param patientId 患者ID
     * @return 用药记录列表
     */
    List<MedicationRecord> getMedicationRecordsByPatientId(Long patientId);

    /**
     * 根据药品ID获取用药记录列表
     *
     * @param drugId 药品ID
     * @return 用药记录列表
     */
    List<MedicationRecord> getMedicationRecordsByDrugId(Long drugId);

    /**
     * 添加用药记录
     *
     * @param medicationRecord 待添加的用药记录
     * @return 影响行数
     */
    int addMedicationRecord(MedicationRecord medicationRecord);

    /**
     * 更新用药记录
     *
     * @param medicationRecord 待更新的用药记录
     * @return 影响行数
     */
    int updateMedicationRecord(MedicationRecord medicationRecord);

    /**
     * 删除用药记录
     *
     * @param id 用药记录ID
     * @return 影响行数
     */
    int deleteMedicationRecord(Long id);
}
