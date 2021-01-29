package com.wolwo.iflow.domain.engine;

import com.wolwo.iflow.config.ThreadPoolExecutor;
import com.wolwo.iflow.domain.engine.delegate.ProcessDelegate;
import com.wolwo.iflow.domain.engine.utils.EntityUtil;
import com.wolwo.iflow.domain.engine.utils.JsonUtil;
import com.wolwo.iflow.domain.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Created by hy
 * @date on 2021/1/27 10:31
 */
public class EngineHandler {

    @Transactional(rollbackFor = Exception.class)
    public void doTaskAction(Task task, ProcessNode currentProcNode, ProcessNode targetProcessNode, String assigneeName, Map<String, Object> variables, String remark) {
        //暂存指针
        Execution tempExecution = DbHandler.getExecution(task.getProcInstId());

        //暂存实例
        ProcessInstance processInstance = DbHandler.getProcessInstanceById(task.getProcInstId());

        updateProcessRuntime(task, targetProcessNode, variables);

        //保存历史记录
        ProcessHistory processHistory = EntityUtil.createProcessHistory(currentProcNode, processInstance, assigneeName, task.getId(), variables, remark);
        DbHandler.saveProcessHistory(processHistory);

        //执行流程节点上配置的委托类
        ThreadPoolExecutor.processThreadPoolExecutor().execute(() -> {
            ProcessDelegate processDelegate = new ProcessDelegate();
            processDelegate.continueProcessDelegate(tempExecution);
        });
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateProcessRuntime(Task task, ProcessNode targetProcessNode, Map<String, Object> variables) {
        //更新运行时
        //1 更新实例
        ProcessInstance processInstance = DbHandler.getProcessInstanceById(task.getProcInstId());
        processInstance.setCurrentProcNodeId(targetProcessNode.getId());
        DbHandler.updateProcessInstance(processInstance);

        //2 查找当前指针
        Execution execution = DbHandler.getExecution(processInstance.getId());

        //3 更新指针
        execution.setCurrentProcNodeId(targetProcessNode.getId());
        execution.setVariables(JsonUtil.toJson(variables));
        execution.setAssigneeKey(targetProcessNode.getAssigneeKey());
        execution.setGroupKey(targetProcessNode.getGroupKey());
        execution.setDelegateClass(targetProcessNode.getDelegateClass());
        execution.setPreProcNodeId(targetProcessNode.getPreProcNodeId());
        execution.setNextProcNodeId(targetProcessNode.getNextProcNodeId());
        DbHandler.updateExecutionById(execution);

        //4 更新任务
        task.setCurrentProcNodeId(targetProcessNode.getId());
        task.setAssigneeKey(targetProcessNode.getAssigneeKey());
        task.setGroupKey(targetProcessNode.getGroupKey());
        task.setVariables(JsonUtil.toJson(variables));
        DbHandler.updateTaskById(task);
    }

}
