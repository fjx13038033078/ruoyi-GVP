package com.ruoyi.patient.service;

import com.ruoyi.patient.domain.DrugInstruction;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/23 16:44
 */
public interface DrugInstructionService {
    /**
     * 获取所有说明书
     *
     * @return 所有说明书列表
     */
    List<DrugInstruction> getAllDrugInstructions();

    /**
     * 根据说明书ID获取说明书信息
     *
     * @param id 说明书ID
     * @return 说明书信息
     */
    DrugInstruction getDrugInstructionById(Long id);

    /**
     * 根据药品ID获取说明书信息
     *
     * @param drugId 药品ID
     * @return 说明书信息列表
     */
    List<DrugInstruction> getDrugInstructionsByDrugId(Long drugId);

    /**
     * 添加说明书
     *
     * @param drugInstruction 待添加的说明书信息
     * @return 添加成功返回 true，否则返回 false
     */
    boolean addDrugInstruction(DrugInstruction drugInstruction);

    /**
     * 更新说明书信息
     *
     * @param drugInstruction 待更新的说明书信息
     * @return 更新成功返回 true，否则返回 false
     */
    boolean updateDrugInstruction(DrugInstruction drugInstruction);

    /**
     * 删除说明书
     *
     * @param id 待删除的说明书ID
     * @return 删除成功返回 true，否则返回 false
     */
    boolean deleteDrugInstruction(Long id);
}
