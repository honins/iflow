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
@ApiModel(value = "FlwRuProcessInstance对象", description = "")
@TableName("flw_ru_process_instance")
public class ProcessInstance {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String PROC_DEF_ID = "proc_def_id";
    public static final String PROC_DEF_KEY = "proc_def_key";
    public static final String BUSINESS_KEY = "business_key";
    public static final String STATUS = "status";
    public static final String CURRENT_PROC_NODE_ID = "current_proc_node_id";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long procDefId;

    private String businessKey;

    private String status;

    @ApiModelProperty(value = "当前流程节点id")
    private Long currentProcNodeId;

    @ApiModelProperty(value = "唯一标识")
    private String procDefKey;

}
