package com.ruoyi.patient.mapper;

import com.ruoyi.patient.domain.MedicationLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 17:08
 */
@Mapper
public interface MedicationLogMapper {
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
     * @return 影响行数
     */
    int addMedicationLog(MedicationLog medicationLog);

    /**
     * 更新服药打卡记录
     *
     * @param medicationLog 待更新的打卡记录
     * @return 影响行数
     */
    int updateMedicationLog(MedicationLog medicationLog);

    /**
     * 删除服药打卡记录
     *
     * @param id 打卡ID
     * @return 影响行数
     */
    int deleteMedicationLog(Long id);
}
