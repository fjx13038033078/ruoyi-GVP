package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 范佳兴
 * @date 2025/9/23 17:41
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_adverse_reaction")
public class AdverseReaction implements Serializable {
    /**
     * 主键，不良反应ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 患者ID
     */
    private Long patientId;

    /**
     * 药品ID
     */
    private Long drugId;

    /**
     * 医生ID
     */
    private Long doctorId;

    /**
     * 医生姓名
     */
    @TableField(exist = false)
    private String doctorName;

    /**
     * 患者姓名
     */
    @TableField(exist = false)
    private String patientName;

    /**
     * 药品名称
     */
    @TableField(exist = false)
    private String drugName;

    /**
     * 反应描述
     */
    private String description;

    /**
     * 严重程度(轻微/中度/严重)
     */
    private String severity;

    /**
     * 发生时间
     */
    private Date occurTime;

    /**
     * 医生是否确认(0否 1是)
     */
    private Integer doctorConfirmed;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
