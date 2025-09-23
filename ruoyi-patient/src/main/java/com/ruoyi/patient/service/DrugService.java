package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.Drug;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 16:40
 */
public interface DrugService {
    /**
     * 获取所有药品
     *
     * @return 所有药品列表
     */
    List<Drug> getAllDrugs();

    /**
     * 根据药品ID获取药品信息
     *
     * @param id 药品ID
     * @return 药品信息
     */
    Drug getDrugById(Long id);

    /**
     * 添加药品
     *
     * @param drug 待添加的药品信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addDrug(Drug drug);

    /**
     * 更新药品信息
     *
     * @param drug 待更新的药品信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateDrug(Drug drug);

    /**
     * 删除药品
     *
     * @param id 待删除的药品ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteDrug(Long id);
}
