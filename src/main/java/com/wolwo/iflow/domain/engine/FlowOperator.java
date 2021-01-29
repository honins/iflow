package com.wolwo.iflow.domain.engine;

import com.wolwo.iflow.domain.engine.service.HistoryService;
import com.wolwo.iflow.domain.engine.service.RepositoryService;
import com.wolwo.iflow.domain.engine.service.RuntimeService;
import com.wolwo.iflow.domain.engine.service.TaskService;
import com.wolwo.iflow.domain.entity.ProcessDefine;
import com.wolwo.iflow.domain.entity.ProcessHistory;
import com.wolwo.iflow.domain.entity.ProcessInstance;
import com.wolwo.iflow.domain.entity.ProcessNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by hy
 * @date on 2021/1/29 8:43
 */
@Component
public class FlowOperator {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    /**
     * 新增流程定义
     */
    public void createProcessDefine(ProcessDefine processDefine) {
        repositoryService.saveProcessDefine(processDefine);
    }

    /**
     * 新增流程节点
     */
    public void createProcessNode(ProcessNode processNode) {
        repositoryService.saveProcessNode(processNode);
    }

    /**
     * 列出流程节点
     */
    public List<ProcessNode> listProcessNode(String procDefKey) {
        return repositoryService.listProcessNode(procDefKey);
    }

    /**
     * 获得流程节点示例
     */
    public ProcessInstance getInstance(String procDefKey, String businessKey) {
        return runtimeService.getInstance(procDefKey, businessKey);
    }

    /**
     * 启动流程
     */
    public ProcessInstance startProcess(String defineKey, String businessKey, String assigneeName, String remark) {
        return runtimeService.startProcessInstanceByKey(defineKey, null, businessKey, assigneeName, remark);
    }

    /**
     * 终止流程
     */
    public void stopProcess(String defineKey, String businessKey, String assigneeName, String remark) {
        runtimeService.stopProcess(defineKey, businessKey, assigneeName, remark);
    }

    /**
     * 通过流程任务
     */
    public void completeTask(String procDefKey, String businessKey, String assigneeName, String remark) {
        taskService.completeTask(procDefKey, businessKey, assigneeName, remark);
    }

    /**
     * 拒绝流程任务
     */
    public void rejectTask(String procDefKey, String businessKey, String assigneeName, String remark) {
        taskService.rejectTask(procDefKey, businessKey, assigneeName, remark);
    }

    /**
     * 列出流程历史记录
     */
    public List<ProcessHistory> listHistory(String procDefKey, String businessKey) {
        return historyService.list(procDefKey, businessKey);
    }
}
