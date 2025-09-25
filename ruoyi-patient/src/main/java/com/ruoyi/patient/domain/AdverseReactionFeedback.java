package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 范佳兴
 * @date 2025/9/23 17:45
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_adverse_reaction_feedback")
public class AdverseReactionFeedback implements Serializable {

    /**
     * 主键，反馈ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 不良反应ID
     */
    private Long reactionId;

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
     * 反馈内容
     */
    private String feedbackContent;

    /**
     * 反馈时间
     */
    private Date createTime;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
