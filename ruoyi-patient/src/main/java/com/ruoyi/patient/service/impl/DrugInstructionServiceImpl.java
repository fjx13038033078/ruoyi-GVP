package com.ruoyi.patient.service.impl;

import com.ruoyi.patient.domain.DrugInstruction;
import com.ruoyi.patient.mapper.DrugInstructionMapper;
import com.ruoyi.patient.service.DrugInstructionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 14:55
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DrugInstructionServiceImpl implements DrugInstructionService {
    private final DrugInstructionMapper drugInstructionMapper;

    @Override
    public List<DrugInstruction> getAllDrugInstructions() {
        List<DrugInstruction> instructions = drugInstructionMapper.getAllDrugInstructions();
        fillExtraInfo(instructions);
        return instructions;
    }

    @Override
    public DrugInstruction getDrugInstructionById(Long id) {
        DrugInstruction instruction = drugInstructionMapper.getDrugInstructionById(id);
        fillExtraInfo(instruction);
        return instruction;
    }

    @Override
    public List<DrugInstruction> getDrugInstructionsByDrugId(Long drugId) {
        List<DrugInstruction> instructions = drugInstructionMapper.getDrugInstructionsByDrugId(drugId);
        fillExtraInfo(instructions);
        return instructions;
    }

    @Override
    public boolean addDrugInstruction(DrugInstruction drugInstruction) {
        drugInstruction.setUpdateTime(new Date());
        int rows = drugInstructionMapper.addDrugInstruction(drugInstruction);
        return rows > 0;
    }

    @Override
    public boolean updateDrugInstruction(DrugInstruction drugInstruction) {
        drugInstruction.setUpdateTime(new Date());
        int rows = drugInstructionMapper.updateDrugInstruction(drugInstruction);
        return rows > 0;
    }

    @Override
    public boolean deleteDrugInstruction(Long id) {
        int rows = drugInstructionMapper.deleteDrugInstruction(id);
        return rows > 0;
    }

    /**
     * 填充额外信息（如果未来需要展示更多，比如药品名称、更新人等）
     *
     * @param instructions 说明书列表
     */
    private void fillExtraInfo(List<DrugInstruction> instructions) {
        for (DrugInstruction instruction : instructions) {
            fillExtraInfo(instruction);
        }
    }

    /**
     * 填充单个说明书的额外信息
     *
     * @param instruction 说明书对象
     */
    private void fillExtraInfo(DrugInstruction instruction) {
        if (instruction == null) {
            return;
        }
        // TODO: 如果需要，可以补充药品名称、药厂信息等
    }
}
