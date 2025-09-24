package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.Setter;
import lombok.Getter;

import java.io.Serializable;

/**
 * @Author 范佳兴
 * @date 2025/9/23 16:50
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_patient")
public class Patient implements Serializable {
    /** 患者ID */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 关联用户ID(小程序端) */
    private Long userId;

    /** 患者姓名 */
    @TableField(exist = false)
    private String patientName;

    /** 负责医生ID */
    private Long doctorId;

    /** 负责医生姓名 */
    @TableField(exist = false)
    private String doctorName;

    /** 病历号 */
    private String medicalRecordNo;

    /** 年龄 */
    private Integer age;

    /** 性别(M/F) */
    private String gender;

    /** 过敏史/病史 */
    private String history;

    /** 序列化版本UID */
    private static final long serialVersionUID = 1L;
}
