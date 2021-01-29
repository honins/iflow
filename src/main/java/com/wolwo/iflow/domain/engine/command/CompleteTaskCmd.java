package com.wolwo.iflow.domain.engine.command;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.engine.EngineHandler;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.engine.utils.EntityUtil;
import com.wolwo.iflow.domain.entity.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:28
 */
public class CompleteTaskCmd implements Command<Void> {

    private Long taskId;
    private String assigneeName;
    private Map<String, Object> variables;
    private String remark;

    public CompleteTaskCmd(Long taskId, String assigneeName, Map<String, Object> variables, String remark) {
        this.taskId = taskId;
        this.assigneeName = assigneeName;
        this.variables = variables;
        this.remark = remark;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Void execute(Command command) {
        //获得任务对象
        Task task = DbHandler.getTaskById(taskId);

        ProcessNode currentProcNode = DbHandler.getProcessNode(task.getCurrentProcNodeId());
        if (currentProcNode.getNextProcNodeId() == -1) {
            //结束流程
            endProcess(task, currentProcNode);
            return null;
        }

        //继续流程
        continueTask(task, currentProcNode);
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void endProcess(Task task, ProcessNode currentProcNode) {

        ProcessInstance processInstance = DbHandler.getProcessInstanceById(task.getProcInstId());
        processInstance.setStatus(ProcessEngineConfig.PROC_INST_STATUES_END);
        DbHandler.updateProcessInstance(processInstance);

        // 移除当前指针
        Execution execution = DbHandler.getExecution(task.getProcInstId());
        DbHandler.removeExecution(execution.getId());

        //移除task
        DbHandler.removeTask(task.getId());

        //保存历史记录
        ProcessHistory processHistory = EntityUtil.createProcessHistory(currentProcNode, processInstance, assigneeName, task.getId(), variables, remark);
        DbHandler.saveProcessHistory(processHistory);
    }

    @Transactional(rollbackFor = Exception.class)
    public void continueTask(Task task, ProcessNode currentProcNode) {
        //获得目标节点
        ProcessNode nextProcessNode = DbHandler.getProcessNode(currentProcNode.getNextProcNodeId());

        EngineHandler engineHandler = new EngineHandler();
        //执行task行为
        engineHandler.doTaskAction(task, currentProcNode, nextProcessNode, assigneeName, variables, remark);

        //如果是最后一个节点
        if (nextProcessNode.getNextProcNodeId() == -1){
            //结束流程
            endProcess(task, nextProcessNode);
        }
    }

}
