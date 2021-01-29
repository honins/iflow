package com.wolwo.iflow.domain.engine;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wolwo.iflow.app.service.*;
import com.wolwo.iflow.domain.SpringContextUtil;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.entity.*;

import java.util.List;

/**
 * @author Created by hy
 * @date on 2021/1/22 15:17
 */
public class DbHandler {

    private static ProcessDefineService processDefineService = SpringContextUtil.getContext().getBean(ProcessDefineService.class);
    private static ProcessNodeService processNodeService = SpringContextUtil.getContext().getBean(ProcessNodeService.class);
    private static ProcessInstanceService processInstanceService = SpringContextUtil.getContext().getBean(ProcessInstanceService.class);
    private static ExecutionService executionService = SpringContextUtil.getContext().getBean(ExecutionService.class);
    private static DbTaskService dbTaskService = SpringContextUtil.getContext().getBean(DbTaskService.class);
    private static ProcessHistoryService processHistoryService = SpringContextUtil.getContext().getBean(ProcessHistoryService.class);

    public static void saveProcessDefine(ProcessDefine processDefine) {
        processDefineService.save(processDefine);
    }

    public static void saveProcessNode(ProcessNode processNode) {
        processNodeService.save(processNode);
    }

    public static ProcessNode getStartProcessNode(String defKey) {
        QueryWrapper<ProcessNode> processNodeQueryWrapper = new QueryWrapper<>();
        processNodeQueryWrapper.eq(ProcessNode.PROC_DEF_KEY, defKey).eq(ProcessNode.PRE_PROC_NODE_ID, -1);
        return processNodeService.getOne(processNodeQueryWrapper);
    }

    public static List<ProcessNode> listProcessNode(String defKey) {
        QueryWrapper<ProcessNode> processNodeQueryWrapper = new QueryWrapper<>();
        processNodeQueryWrapper.eq(ProcessNode.PROC_DEF_KEY, defKey);
        return processNodeService.list(processNodeQueryWrapper);
    }

    public static ProcessNode getProcessNode(Long currentProcNodeId) {
        return processNodeService.getById(currentProcNodeId);
    }

    public static ProcessNode getPreProcessNode(Long currentProcessNodeId) {
        QueryWrapper<ProcessNode> processNodeQueryWrapper = new QueryWrapper<>();
        processNodeQueryWrapper.eq(ProcessNode.NEXT_PROC_NODE_ID, currentProcessNodeId);
        return processNodeService.getOne(processNodeQueryWrapper);
    }

    public static ProcessNode getNextProcessNode(Long currentProcessNodeId) {
        ProcessNode currentProcessNode = processNodeService.getById(currentProcessNodeId);
        return processNodeService.getById(currentProcessNode.getNextProcNodeId());
    }

    public static List<ProcessNode> queryProcessNode(QueryWrapper<ProcessNode> processNodeQueryWrapper) {
        return processNodeService.list(processNodeQueryWrapper);
    }

    public static ProcessInstance getProcessInstanceById(Long processInstanceId) {
        return processInstanceService.getById(processInstanceId);
    }

    public static boolean saveProcessInstance(ProcessInstance processInstance) {
        return processInstanceService.save(processInstance);
    }

    public static boolean updateProcessInstance(ProcessInstance processInstance) {
        return processInstanceService.updateById(processInstance);
    }

    public static Execution getExecution(Long processInstanceId) {
        QueryWrapper<Execution> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Execution.PROC_INST_ID, processInstanceId);
        return executionService.getOne(queryWrapper);
    }

    public static boolean saveExecution(Execution execution) {
        return executionService.save(execution);
    }

    public static boolean updateExecutionById(Execution execution) {
        return executionService.updateById(execution);
    }

    public static List<Task> queryTask(String groupKey) {
        QueryWrapper<Task> taskQueryWrapper = new QueryWrapper<>();
        taskQueryWrapper.eq(Task.GROUP_KEY, groupKey).orderByDesc(Task.CREATION_DATE);
        return dbTaskService.list(taskQueryWrapper);
    }

    public static List<Task> queryTask(QueryWrapper<Task> taskQueryWrapper) {
        return dbTaskService.list(taskQueryWrapper);
    }

    public static Task getTaskById(Long id) {
        return dbTaskService.getById(id);
    }

    public static boolean saveTask(Task task) {
        return dbTaskService.save(task);
    }

    public static boolean updateTaskById(Task task) {
        return dbTaskService.updateById(task);
    }

    public static boolean saveProcessHistory(ProcessHistory processHistory) {
        return processHistoryService.save(processHistory);
    }

    public static void removeTask(Long taskId) {
        dbTaskService.removeById(taskId);
    }

    public static void removeExecution(Long executionId) {
        executionService.removeById(executionId);
    }

    public static void removeProcessInstance(Long procInstId) {
        processInstanceService.removeById(procInstId);
    }

    public static List<ProcessHistory> listProcessHistory(String procDefKey, String businessKey) {
        QueryWrapper<ProcessHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ProcessHistory.PROC_DEF_KEY, procDefKey)
                .eq(ProcessHistory.BUSINESS_KEY, businessKey)
                .orderByAsc(ProcessHistory.CREATION_DATE);
        return processHistoryService.list(queryWrapper);
    }

    public static ProcessInstance getProcessInstance(String defineKey, String businessKey) {
        QueryWrapper<ProcessInstance> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ProcessInstance.PROC_DEF_KEY, defineKey)
                .eq(ProcessInstance.BUSINESS_KEY, businessKey)
                .eq(ProcessInstance.STATUS, ProcessEngineConfig.PROC_INST_STATUES_RUN);
        return processInstanceService.getOne(queryWrapper);
    }

    public static Task getTaskByProcInstId(Long processInstanceId) {
        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Task.PROC_INST_ID, processInstanceId);
        return dbTaskService.getOne(queryWrapper);
    }

    public static ProcessNode getEndProcessNode(Long procDefId) {
        QueryWrapper<ProcessNode> processNodeQueryWrapper = new QueryWrapper<>();
        processNodeQueryWrapper.eq(ProcessNode.PROC_DEF_ID, procDefId)
                .eq(ProcessNode.NEXT_PROC_NODE_ID, -1);
        return processNodeService.getOne(processNodeQueryWrapper);
    }
}
