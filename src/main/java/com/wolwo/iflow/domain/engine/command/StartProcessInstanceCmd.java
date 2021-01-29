package com.wolwo.iflow.domain.engine.command;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.engine.utils.EntityUtil;
import com.wolwo.iflow.domain.entity.*;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:28
 */
public class StartProcessInstanceCmd<T> implements Command<ProcessInstance> {

    private String defKey;
    private Map<String, Object> variables;
    private String businessKey;
    private String assigneeName;
    private String remark;

    public StartProcessInstanceCmd(String defKey, Map<String, Object> variables, String businessKey, String assigneeName, String remark) {
        this.defKey = defKey;
        this.variables = variables;
        this.businessKey = businessKey;
        this.assigneeName = assigneeName;
        this.remark = remark;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ProcessInstance execute(Command command) {
        ProcessNode startProcessNode = DbHandler.getStartProcessNode(defKey);

        //生成流程实例
        ProcessInstance processInstance = new ProcessInstance();
        BeanUtils.copyProperties(startProcessNode, processInstance);
        processInstance.setBusinessKey(businessKey);
        processInstance.setCurrentProcNodeId(startProcessNode.getNextProcNodeId());
        processInstance.setStatus(ProcessEngineConfig.PROC_INST_STATUES_RUN);
        //保存流程实例
        DbHandler.saveProcessInstance(processInstance);

        ProcessNode nextProcessNode = DbHandler.getProcessNode(startProcessNode.getNextProcNodeId());

        //生成流程运行时执行指针
        Execution execution = new Execution();
        BeanUtils.copyProperties(processInstance, execution);
        BeanUtils.copyProperties(nextProcessNode, execution);
        execution.setProcInstId(processInstance.getId());
        //保存流程运行时执行指针
        DbHandler.saveExecution(execution);

        //生成任务
        Task task = new Task();
        BeanUtils.copyProperties(processInstance, task);
        BeanUtils.copyProperties(nextProcessNode, task);
        task.setProcInstId(processInstance.getId());
        //保存任务
        DbHandler.saveTask(task);

        //保存历史记录
        ProcessHistory processHistory = EntityUtil.createProcessHistory(startProcessNode, processInstance,
                assigneeName, null, variables, remark);
        DbHandler.saveProcessHistory(processHistory);

        //返回流程实例
        return processInstance;
    }

}
