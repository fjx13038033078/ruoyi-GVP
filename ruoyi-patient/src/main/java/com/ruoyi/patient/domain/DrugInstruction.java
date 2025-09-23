package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @date 2025/9/23 16:42
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_drug_instruction")
public class DrugInstruction implements Serializable {
    /**
     * 主键，说明书ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 药品ID
     */
    private Long drugId;

    /**
     * 说明书内容
     */
    private String content;

    /**
     * 版本号
     */
    private String version;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
