package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * 患者用药记录实体类
 *
 * @Author 范佳兴
 * @date 2025/9/23 17:01
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_medication_record")
public class MedicationRecord implements Serializable {
    /**
     * 主键，用药记录ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 患者姓名
     */
    @TableField(exist = false)
    private String patientName;

    /**
     * 药品ID
     */
    private Long drugId;

    /**
     * 药品名称
     */
    @TableField(exist = false)
    private String drugName;

    /**
     * 剂量
     */
    private String dosage;

    /**
     * 频率
     */
    private String frequency;

    /**
     * 开始日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 结束日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 状态(1使用中 0已结束)
     */
    private Integer status;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
