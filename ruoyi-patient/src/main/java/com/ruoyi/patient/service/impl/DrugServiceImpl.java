package com.ruoyi.patient.service.impl;

import com.ruoyi.patient.domain.Drug;
import com.ruoyi.patient.mapper.DrugMapper;
import com.ruoyi.patient.service.DrugService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 14:49
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DrugServiceImpl implements DrugService {

    private final DrugMapper drugMapper;

    @Override
    public List<Drug> getAllDrugs() {
        List<Drug> allDrugs = drugMapper.getAllDrugs();
        fillExtraInfo(allDrugs);
        return allDrugs;
    }

    @Override
    public Drug getDrugById(Long id) {
        Drug drug = drugMapper.getDrugById(id);
        fillExtraInfo(drug);
        return drug;
    }

    @Override
    public boolean addDrug(Drug drug) {
        int rows = drugMapper.addDrug(drug);
        return rows > 0;
    }

    @Override
    public boolean updateDrug(Drug drug) {
        int rows = drugMapper.updateDrug(drug);
        return rows > 0;
    }

    @Override
    public boolean deleteDrug(Long id) {
        int rows = drugMapper.deleteDrug(id);
        return rows > 0;
    }

    /**
     * 填充额外信息（如果未来需要展示更多，比如药厂信息等）
     *
     * @param drugs 药品列表
     */
    private void fillExtraInfo(List<Drug> drugs) {
        for (Drug drug : drugs) {
            fillExtraInfo(drug);
        }
    }

    /**
     * 填充单个药品的额外信息
     *
     * @param drug 药品对象
     */
    private void fillExtraInfo(Drug drug) {
        if (drug == null) {
            return;
        }
        // TODO: 如果需要，可以补充药厂、说明书版本等信息
    }
}
