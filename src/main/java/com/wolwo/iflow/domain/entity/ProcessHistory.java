package com.wolwo.iflow.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
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
@ApiModel(value = "FlwHisProcessHistory对象", description = "")
@TableName("flw_his_process_history")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProcessHistory {

    private static final long serialVersionUID = 1L;

    public static final String ID = "id";
    public static final String PROC_DEF_ID = "proc_def_id";
    public static final String PROC_DEF_KEY = "proc_def_key";
    public static final String PROC_INST_ID = "proc_inst_id";
    public static final String PROC_NODE_ID = "proc_node_id";
    public static final String PROC_NODE_NAME = "proc_node_name";
    public static final String BUSINESS_KEY = "business_key";
    public static final String VARIABLES = "variables";
    public static final String TASK_ID = "task_id";
    public static final String ASSIGNEE_KEY = "assignee_key";
    public static final String ASSIGNEE_NAME = "assignee_name";
    public static final String GROUP_KEY = "group_key";
    public static final String REMARK = "remark";
    public static final String CREATION_DATE = "creation_date";
    public static final String LAST_UPDATE_DATE = "last_update_date";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long procDefId;

    private String procDefKey;

    private Long procInstId;

    private Long procNodeId;

    private String procNodeName;

    private String businessKey;

    private String variables;

    private Long taskId;

    private String assigneeKey;

    private String assigneeName;

    private String groupKey;

    private String remark;

    private Date creationDate;

    private Date lastUpdateDate;


}
