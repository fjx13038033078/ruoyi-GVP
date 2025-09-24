package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @Author 范佳兴
 * @date 2025/9/23 17:07
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_medication_log")
public class MedicationLog implements Serializable {
    /**
     * 主键，服药打卡ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用药记录ID
     */
    private Long recordId;

    /**
     * 服药时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date takeTime;

    /**
     * 是否服药(1已服 0未服)
     */
    private Integer isTaken;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
