package com.ruoyi.patient.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.patient.domain.Drug;
import com.ruoyi.patient.domain.MedicationRecord;
import com.ruoyi.patient.mapper.MedicationRecordMapper;
import com.ruoyi.patient.service.DrugService;
import com.ruoyi.patient.service.MedicationRecordService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 15:51
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MedicationRecordServiceImpl implements MedicationRecordService {

    private final MedicationRecordMapper medicationRecordMapper;

    private final DrugService drugService;

    private final ISysUserService sysUserService;

    private final ISysRoleService iSysRoleService;

    /**
     * 获取所有用药记录
     *
     * @return 用药记录列表
     */
    @Override
    public List<MedicationRecord> getAllMedicationRecords() {
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equalsIgnoreCase("patient")){
            List<MedicationRecord> medicationRecordsByPatientId = medicationRecordMapper.getMedicationRecordsByPatientId(userId);
            fillExtraInfo(medicationRecordsByPatientId);
            return medicationRecordsByPatientId;
        } else {
            List<MedicationRecord> records = medicationRecordMapper.getAllMedicationRecords();
            fillExtraInfo(records);
            return records;
        }
    }

    /**
     * 根据ID获取单条用药记录
     *
     * @param id 用药记录ID
     * @return 用药记录信息
     */
    @Override
    public MedicationRecord getMedicationRecordById(Long id) {
        MedicationRecord record = medicationRecordMapper.getMedicationRecordById(id);
        fillExtraInfo(record);
        return record;
    }

    /**
     * 根据患者ID获取用药记录
     *
     * @param patientId 患者ID
     * @return 用药记录列表
     */
    @Override
    public List<MedicationRecord> getMedicationRecordsByPatientId(Long patientId) {
        List<MedicationRecord> records = medicationRecordMapper.getMedicationRecordsByPatientId(patientId);
        fillExtraInfo(records);
        return records;
    }

    /**
     * 根据药品ID获取用药记录
     *
     * @param drugId 药品ID
     * @return 用药记录列表
     */
    @Override
    public List<MedicationRecord> getMedicationRecordsByDrugId(Long drugId) {
        List<MedicationRecord> records = medicationRecordMapper.getMedicationRecordsByDrugId(drugId);
        fillExtraInfo(records);
        return records;
    }

    /**
     * 添加用药记录
     *
     * @param medicationRecord 待添加的用药记录
     * @return 是否成功
     */
    @Override
    public boolean addMedicationRecord(MedicationRecord medicationRecord) {
        int rows = medicationRecordMapper.addMedicationRecord(medicationRecord);
        return rows > 0;
    }

    /**
     * 更新用药记录
     *
     * @param medicationRecord 待更新的用药记录
     * @return 是否成功
     */
    @Override
    public boolean updateMedicationRecord(MedicationRecord medicationRecord) {
        int rows = medicationRecordMapper.updateMedicationRecord(medicationRecord);
        return rows > 0;
    }

    /**
     * 删除用药记录
     *
     * @param id 用药记录ID
     * @return 是否成功
     */
    @Override
    public boolean deleteMedicationRecord(Long id) {
        int rows = medicationRecordMapper.deleteMedicationRecord(id);
        return rows > 0;
    }

    /**
     * 批量填充额外信息（例如未来可能需要补充药品名称、患者姓名等）
     *
     * @param records 用药记录列表
     */
    private void fillExtraInfo(List<MedicationRecord> records) {
        for (MedicationRecord record : records) {
            fillExtraInfo(record);
        }
    }

    /**
     * 填充单条用药记录的额外信息
     *
     * @param record 用药记录对象
     */
    private void fillExtraInfo(MedicationRecord record) {
        if (record == null) {
            return;
        }
        // TODO: 如果需要，可以补充药品名称、患者姓名等
        Drug drugById = drugService.getDrugById(record.getDrugId());
        record.setDrugName(drugById.getDrugName());
        record.setPatientName(sysUserService.selectUserById(record.getPatientId()).getNickName());
    }
}
