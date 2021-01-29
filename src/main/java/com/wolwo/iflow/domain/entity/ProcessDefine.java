package com.wolwo.iflow.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author Summer
 * @since 2021-01-22
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "FlwReProcessDefine对象", description = "")
@TableName("flw_re_process_define")
public class ProcessDefine {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String PROC_DEF_KEY = "proc_def_key";
    public static final String NAME = "name";
    public static final String ENABLE = "enable";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "唯一标识")
    private String procDefKey;

    @ApiModelProperty(value = "流程用于展示的名字")
    private String name;

    private Boolean enable;


}
