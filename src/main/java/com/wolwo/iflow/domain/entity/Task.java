package com.wolwo.iflow.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

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
@ApiModel(value = "FlwRuTask对象", description = "")
@TableName("flw_ru_task")
public class Task {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String PROC_DEF_ID = "proc_def_id";
    public static final String PROC_DEF_KEY = "proc_def_key";
    public static final String PROC_INST_ID = "proc_inst_id";
    public static final String BUSINESS_KEY = "business_key";
    public static final String VARIABLES = "variables";
    public static final String CURRENT_PROC_NODE_ID = "current_proc_node_id";
    public static final String ASSIGNEE_KEY = "assignee_key";
    public static final String GROUP_KEY = "group_key";
    public static final String CREATION_DATE = "creation_date";
    public static final String LAST_UPDATE_DATE = "last_update_date";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long procDefId;

    @ApiModelProperty(value = "唯一标识")
    private String procDefKey;

    private Long procInstId;

    private String businessKey;

    private String variables;

    @ApiModelProperty(value = "当前流程节点id")
    private Long currentProcNodeId;

    private String assigneeKey;

    private String groupKey;

    private Date creationDate;

    private Date lastUpdateDate;

}
