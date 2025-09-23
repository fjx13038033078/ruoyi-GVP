package com.ruoyi.patient.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author 范佳兴
 * @date 2025/9/23 16:37
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_drug")
public class Drug implements Serializable {
    /**
     * 主键，药品ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 药品名称
     */
    private String drugName;

    /**
     * 药品描述
     */
    private String description;

    /**
     * 药品适应症
     */
    private String indications;

    /**
     * 药品注意事项
     */
    private String precautions;

    /**
     * 序列化版本UID
     */
    private static final long serialVersionUID = 1L;
}
