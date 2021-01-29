package com.wolwo.iflow.domain.engine.command;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.engine.EngineHandler;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.entity.ProcessNode;
import com.wolwo.iflow.domain.entity.Task;

import java.util.Map;

/**
 * @author Created by hy
 * @date on 2021/1/27 9:38
 */
public class RejectTaskCmd implements Command<Void> {

    private Long taskId;
    private String assigneeName;
    private Map<String, Object> variables;
    private String remark;

    public RejectTaskCmd(Long taskId, String assigneeName, Map<String, Object> variables, String remark) {
        this.taskId = taskId;
        this.assigneeName = assigneeName;
        this.variables = variables;
        this.remark = remark;
    }

    @Override
    public Void execute(Command command) {
        //获得任务对象
        Task task = DbHandler.getTaskById(taskId);

        ProcessNode currentProcNode = DbHandler.getProcessNode(task.getCurrentProcNodeId());
        if (currentProcNode.getPreProcNodeId() == -1) {
            return null;
        }

        //驳回流程
        rejectTask(task, currentProcNode);
        return null;
    }

    private void rejectTask(Task task, ProcessNode currentProcNode) {
        //获得目标节点
        ProcessNode preProcessNode = DbHandler.getProcessNode(currentProcNode.getPreProcNodeId());

        EngineHandler engineHandler = new EngineHandler();
        //执行task行为
        engineHandler.doTaskAction(task, currentProcNode, preProcessNode, assigneeName, variables,
                ProcessEngineConfig.REJECT_TASK_FIXED_TEXT + remark);
    }

}
