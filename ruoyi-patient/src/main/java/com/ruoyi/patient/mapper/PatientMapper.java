package com.ruoyi.patient.mapper;

import com.ruoyi.patient.domain.Patient;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 16:52
 */
@Mapper
public interface PatientMapper {
    /**
     * 获取所有患者
     *
     * @return 患者列表
     */
    List<Patient> selectAll();

    /**
     * 根据ID获取患者
     *
     * @param id 患者ID
     * @return 患者信息
     */
    Patient selectById(Long id);

    /**
     * 根据user_id获取患者
     *
     * @return 患者列表
     */
    Patient selectByUserId(Long userId);

    /**
     * 根据doctor_id获取患者
     *
     * @return 患者列表
     */
    List<Patient> selectByDoctorId(Long doctorId);

    /**
     * 添加患者
     *
     * @param patient 待添加的患者
     * @return 影响行数
     */
    int insert(Patient patient);

    /**
     * 更新患者
     *
     * @param patient 待更新的患者
     * @return 影响行数
     */
    int update(Patient patient);

    /**
     * 删除患者
     *
     * @param id 患者ID
     * @return 影响行数
     */
    int deleteById(Long id);
}
