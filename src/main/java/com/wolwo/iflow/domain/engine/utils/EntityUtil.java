package com.wolwo.iflow.domain.engine.utils;

import com.wolwo.iflow.domain.entity.Execution;
import com.wolwo.iflow.domain.entity.ProcessHistory;
import com.wolwo.iflow.domain.entity.ProcessInstance;
import com.wolwo.iflow.domain.entity.ProcessNode;

import java.util.Map;

/**
 * @author Created by hy
 * @date on 2021/1/26 11:16
 */
public class EntityUtil {

    public static ProcessHistory createProcessHistory(ProcessNode processNode, ProcessInstance processInstance,
                                                      String assigneeName, Long taskId, Map<String, Object> variables, String remark) {
        return ProcessHistory.builder()
                .procDefId(processNode.getProcDefId())
                .procDefKey(processNode.getProcDefKey())
                .procNodeId(processNode.getId())
                .procNodeName(processNode.getName())
                .procInstId(processInstance.getId())
                .businessKey(processInstance.getBusinessKey())
                .variables(JsonUtil.toJson(variables))
                .assigneeName(assigneeName)
                .assigneeKey(processNode.getAssigneeKey())
                .groupKey(processNode.getGroupKey())
                .taskId(taskId)
                .remark(remark)
                .build();
    }

//    public static ProcessHistory createProcessHistory(Execution execution, String assigneeName, Map<String, Object> variables,String remark) {
//        return ProcessHistory.builder()
//                .procDefId(execution.getProcDefId())
//                .procDefKey(execution.getProcDefKey())
//                .procNodeId(execution.getCurrentProcNodeId())
//                .procNodeName(processNode.getName())
//                .procInstId(execution.getId())
//                .businessKey(execution.getBusinessKey())
//                .variables(JsonUtil.toJson(variables))
//                .assigneeName(assigneeName)
//                .assigneeKey(execution.getAssigneeKey())
//                .groupKey(execution.getGroupKey())
//                .taskId(taskId)
//                .remark(remark)
//                .build();
//    }

}
