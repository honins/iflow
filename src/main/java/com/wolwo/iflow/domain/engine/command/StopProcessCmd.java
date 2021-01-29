package com.wolwo.iflow.domain.engine.command;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.engine.utils.EntityUtil;
import com.wolwo.iflow.domain.entity.*;

/**
 * @author Created by hy
 * @date on 2021/1/28 10:24
 */
public class StopProcessCmd implements Command<Void> {

    private Long processInstanceId;

    private String assigneeName;

    private String remark;

    public StopProcessCmd(Long processInstanceId, String assigneeName, String remark) {
        this.processInstanceId = processInstanceId;
        this.assigneeName = assigneeName;
        this.remark = remark;
    }

    @Override
    public Void execute(Command command) {

        // 移除当前指针
        Execution execution = DbHandler.getExecution(processInstanceId);
        DbHandler.removeExecution(execution.getId());

        ProcessNode currentProcessNode = DbHandler.getProcessNode(execution.getCurrentProcNodeId());

        //移除task
        Task task = DbHandler.getTaskByProcInstId(processInstanceId);
        DbHandler.removeTask(task.getId());

        //更新实例的当前节点
        ProcessInstance processInstance = DbHandler.getProcessInstanceById(processInstanceId);
        processInstance.setStatus(ProcessEngineConfig.PROC_INST_STATUES_STOP);
        DbHandler.updateProcessInstance(processInstance);

        //保存历史记录
        ProcessHistory processHistory = EntityUtil.createProcessHistory(currentProcessNode, processInstance, assigneeName,
                task.getId(), null, ProcessEngineConfig.STOP_PROCESS_FIXED_TEXT + remark);
        DbHandler.saveProcessHistory(processHistory);
        return null;
    }

}
