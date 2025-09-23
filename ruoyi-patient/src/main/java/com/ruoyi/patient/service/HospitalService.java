package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.Hospital;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 11:41
 */
public interface HospitalService {
    /**
     * 获取所有医院
     *
     * @return 所有医院列表
     */
    List<Hospital> getAllHospitals();

    /**
     * 根据医院ID获取医院信息
     *
     * @param id 医院ID
     * @return 医院信息
     */
    Hospital getHospitalById(Long id);

    /**
     * 添加医院
     *
     * @param hospital 待添加的医院信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addHospital(Hospital hospital);

    /**
     * 更新医院信息
     *
     * @param hospital 待更新的医院信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateHospital(Hospital hospital);

    /**
     * 删除医院
     *
     * @param id 待删除的医院ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteHospital(Long id);
}
