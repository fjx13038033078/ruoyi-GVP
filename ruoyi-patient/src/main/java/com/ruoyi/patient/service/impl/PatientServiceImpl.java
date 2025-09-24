package com.ruoyi.patient.service.impl;

import com.ruoyi.patient.domain.Patient;
import com.ruoyi.patient.mapper.PatientMapper;
import com.ruoyi.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 范佳兴
 * @date 2025/9/24 8:59
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientMapper patientMapper;

    @Override
    public List<Patient> getAllPatients() {
        List<Patient> patients = patientMapper.selectAll();
        fillExtraInfo(patients);
        return patients;
    }

    @Override
    public Patient getPatientById(Long id) {
        Patient patient = patientMapper.selectById(id);
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
        // 示例：patient.setHospitalName(hospitalService.getNameById(patient.getHospitalId()));
        // 示例：patient.setDoctorName(doctorService.getNameById(patient.getDoctorId()));
    }
}
