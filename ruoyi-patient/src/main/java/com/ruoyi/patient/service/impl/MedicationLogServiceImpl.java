package com.ruoyi.patient.service.impl;

import com.ruoyi.patient.domain.MedicationLog;
import com.ruoyi.patient.mapper.MedicationLogMapper;
import com.ruoyi.patient.service.MedicationLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 16:56
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MedicationLogServiceImpl implements MedicationLogService {
    private final MedicationLogMapper medicationLogMapper;

    @Override
    public List<MedicationLog> getAllMedicationLogs() {
        List<MedicationLog> logs = medicationLogMapper.getAllMedicationLogs();
        fillExtraInfo(logs);
        return logs;
    }

    @Override
    public MedicationLog getMedicationLogById(Long id) {
        MedicationLog log = medicationLogMapper.getMedicationLogById(id);
        fillExtraInfo(log);
        return log;
    }

    @Override
    public List<MedicationLog> getMedicationLogsByRecordId(Long recordId) {
        List<MedicationLog> logs = medicationLogMapper.getMedicationLogsByRecordId(recordId);
        fillExtraInfo(logs);
        return logs;
    }

    @Override
    public boolean addMedicationLog(MedicationLog medicationLog) {
        int rows = medicationLogMapper.addMedicationLog(medicationLog);
        return rows > 0;
    }

    @Override
    public boolean updateMedicationLog(MedicationLog medicationLog) {
        int rows = medicationLogMapper.updateMedicationLog(medicationLog);
        return rows > 0;
    }

    @Override
    public boolean deleteMedicationLog(Long id) {
        int rows = medicationLogMapper.deleteMedicationLog(id);
        return rows > 0;
    }

    /**
     * 批量填充额外信息（例如未来可能需要补充用药状态说明等）
     *
     * @param logs 服药打卡记录列表
     */
    private void fillExtraInfo(List<MedicationLog> logs) {
        for (MedicationLog log : logs) {
            fillExtraInfo(log);
        }
    }

    /**
     * 填充单条打卡记录的额外信息
     *
     * @param log 服药打卡记录对象
     */
    private void fillExtraInfo(MedicationLog log) {
        if (log == null) {
            return;
        }
        // TODO: 如果需要，可补充用药记录信息、患者姓名、药品名称等
    }
}
