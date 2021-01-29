package com.wolwo.iflow.domain.engine.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.engine.command.CompleteTaskCmd;
import com.wolwo.iflow.domain.engine.command.RejectTaskCmd;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.entity.ProcessInstance;
import com.wolwo.iflow.domain.entity.Task;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:26
 */
@Component
public class TaskService extends CommonEngineService {

    public Task queryTask(String procDefKey, String businessKey) {
        ProcessInstance processInstance = DbHandler.getProcessInstance(procDefKey, businessKey);
        if (processInstance == null || !processInstance.getStatus().equals(ProcessEngineConfig.PROC_INST_STATUES_RUN)) {
            throw new RuntimeException("无任务");
        }

        QueryWrapper<Task> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(Task.PROC_DEF_KEY, procDefKey).eq(Task.BUSINESS_KEY, businessKey);
        List<Task> tasks = DbHandler.queryTask(queryWrapper);
        if (tasks.size() == 0) {
            throw new RuntimeException("无任务");
        }

        return tasks.get(0);
    }

    /**
     * 通过任务
     *
     * @param procDefKey
     * @param businessKey
     * @param assigneeName
     * @param remark
     */
    public void completeTask(String procDefKey, String businessKey, String assigneeName, String remark) {
        Task task = queryTask(procDefKey, businessKey);
        if (task == null) {
            throw new RuntimeException("无任务");
        }

        completeTask(task.getId(), assigneeName, remark);
    }

    public void completeTask(Long taskId, String assigneeName, String remark) {
        CompleteTaskCmd completeTaskCmd = new CompleteTaskCmd(taskId, assigneeName, null, remark);
        commandExecutor.execute(completeTaskCmd);
    }

    public void completeTask(Long taskId, String assigneeName, HashMap<String, Object> variables, String remark) {
        CompleteTaskCmd completeTaskCmd = new CompleteTaskCmd(taskId, assigneeName, variables, remark);
        commandExecutor.execute(completeTaskCmd);
    }

    /**
     * 驳回
     */
    public void rejectTask(Long taskId, String assigneeName, String remark) {
        RejectTaskCmd rejectTaskCmd = new RejectTaskCmd(taskId, assigneeName, null, remark);
        commandExecutor.execute(rejectTaskCmd);
    }

    /**
     * 驳回
     */
    public void rejectTask(Long taskId, String assigneeName, HashMap<String, Object> variables, String remark) {
        RejectTaskCmd rejectTaskCmd = new RejectTaskCmd(taskId, assigneeName, variables, remark);
        commandExecutor.execute(rejectTaskCmd);
    }

    public void rejectTask(String procDefKey, String businessKey, String assigneeName, String remark) {
        Task task = queryTask(procDefKey, businessKey);
        if (task != null) {
            rejectTask(task.getId(), assigneeName, remark);
        }
    }
}
