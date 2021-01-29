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
@ApiModel(value = "FlwReProcessNode对象", description = "")
@TableName("flw_re_process_node")
public class ProcessNode {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String PROC_DEF_KEY = "proc_def_key";
    public static final String NAME = "name";
    public static final String PROC_DEF_ID = "proc_def_id";
    public static final String PRE_PROC_NODE_ID = "pre_proc_node_id";
    public static final String NEXT_PROC_NODE_ID = "next_proc_node_id";
    public static final String ASSIGNEE_KEY = "assignee_key";
    public static final String GROUP_KEY = "group_key";
    public static final String DELEGATE_CLASS = "delegate_class";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Long procDefId;

    @ApiModelProperty(value = "唯一标识")
    private String procDefKey;

    @ApiModelProperty(value = "上一个节点的id，为空则代表无相应节点")
    private Long preProcNodeId;

    @ApiModelProperty(value = "下一个节点的id，为空则代表无相应节点")
    private Long nextProcNodeId;

    @ApiModelProperty(value = "分配人的key，可为主键")
    private String assigneeKey;

    @ApiModelProperty(value = "分配组的key，可为角色")
    private String groupKey;

    @ApiModelProperty(value = "节点处理操作后的委托处理类，全类名")
    private String delegateClass;

}
