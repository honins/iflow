package com.wolwo.iflow.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@ApiModel(value = "FlwRuExecution对象", description = "")
@TableName("flw_ru_execution")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Execution {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String PROC_DEF_ID = "proc_def_id";
    public static final String PROC_DEF_KEY = "proc_def_key";
    public static final String PROC_INST_ID = "proc_inst_id";
    public static final String BUSINESS_KEY = "business_key";
    public static final String CURRENT_PROC_NODE_ID = "current_proc_node_id";
    public static final String PRE_PROC_NODE_ID = "pre_proc_node_id";
    public static final String NEXT_PROC_NODE_ID = "next_proc_node_id";
    public static final String ASSIGNEE_KEY = "assignee_key";
    public static final String GROUP_KEY = "group_key";
    public static final String VARIABLES = "variables";
    public static final String DELEGATE_CLASS = "delegate_class";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long procDefId;

    private String procDefKey;

    private Long procInstId;

    @ApiModelProperty(value = "关联业务的key")
    private String businessKey;

    @ApiModelProperty(value = "当前流程节点id")
    private Long currentProcNodeId;

    @ApiModelProperty(value = "上一个节点的id，为空则代表无相应节点")
    private Long preProcNodeId;

    @ApiModelProperty(value = "下一个节点的id，为空则代表无相应节点")
    private Long nextProcNodeId;

    @ApiModelProperty(value = "分配人的key，可为主键")
    private String assigneeKey;

    @ApiModelProperty(value = "分配组的key，可为角色")
    private String groupKey;

    @ApiModelProperty(value = "流程流转过程中的变量，HashMap转出json字符串形式保存")
    private String variables;

    @ApiModelProperty(value = "节点处理操作后的委托处理类，全类名")
    private String delegateClass;


}
