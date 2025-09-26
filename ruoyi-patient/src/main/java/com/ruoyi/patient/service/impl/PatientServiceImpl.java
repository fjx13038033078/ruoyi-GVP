package com.ruoyi.patient.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.patient.domain.Patient;
import com.ruoyi.patient.mapper.PatientMapper;
import com.ruoyi.patient.service.PatientService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author 范佳兴
 * @date 2025/9/24 8:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientMapper patientMapper;

    private final ISysRoleService iSysRoleService;

    private final ISysUserService iSysUserService;

    @Override
    public List<Patient> getAllPatients() {
        Long userId = SecurityUtils.getUserId();
        String role = iSysRoleService.selectStringRoleByUserId(userId);
        if (role.equalsIgnoreCase("admin") ) {
            startPage();
            List<Patient> patients = patientMapper.selectAll();
            fillExtraInfo(patients);
            return patients;
        } else if (role.equalsIgnoreCase("doctor")){
            startPage();
            List<Patient> patients = patientMapper.selectByDoctorId(userId);
            fillExtraInfo(patients);
            return patients;
        } else {
            startPage();
            Patient patient = patientMapper.selectByUserId(userId);
            fillExtraInfo(patient);
            List<Patient> result = new ArrayList<>();
            if (patient != null) {
                result.add(patient);
            }
            return result;
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = patientMapper.selectById(id);
        fillExtraInfo(patient);
        return patient;
    }

    @Override
    public Patient getPatientByUserId(Long userId) {
        Patient patient = patientMapper.selectByUserId(userId);
        fillExtraInfo(patient);
        return patient;
    }

    @Override
    public boolean addPatient(Patient patient) {
        int rows = patientMapper.insert(patient);
        return rows > 0;
    }

    @Override
    public boolean updatePatient(Patient patient) {
        int rows = patientMapper.update(patient);
        return rows > 0;
    }

    @Override
    public boolean deletePatient(Long id) {
        int rows = patientMapper.deleteById(id);
        return rows > 0;
    }

    /**
     * 填充额外信息（如未来需要展示患者所属医院、医生姓名等）
     *
     * @param patients 患者列表
     */
    private void fillExtraInfo(List<Patient> patients) {
        for (Patient patient : patients) {
            fillExtraInfo(patient);
        }
    }

    /**
     * 填充单个患者的额外信息
     *
     * @param patient 患者对象
     */
    private void fillExtraInfo(Patient patient) {
        if (patient == null) {
            return;
        }
        // TODO: 可调用 hospitalService / doctorService 填充医院名、医生姓名
        SysUser sysUser = iSysUserService.selectUserById(patient.getUserId());
        patient.setPatientName(sysUser.getNickName());
        SysUser sysUser1 = iSysUserService.selectUserById(patient.getDoctorId());
        patient.setDoctorName(sysUser1.getNickName());
    }
}
