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
 * @date 2025/9/23 11:38
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gvp_hospital")
public class Hospital implements Serializable {

    /**
     * 主键，医院ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 医院名称
     */
    private String name;

    /**
     * 医院地址
     */
    private String address;

    /**
     * 联系方式
     */
    private String contact;

    private static final long serialVersionUID = 1L;
}
