package com.wolwo.iflow.domain.engine.service;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.engine.command.StartProcessInstanceCmd;
import com.wolwo.iflow.domain.engine.command.StopProcessCmd;
import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.entity.ProcessInstance;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:26
 */
@Component
public class RuntimeService extends CommonEngineService {

    public ProcessInstance startProcessInstanceByKey(String defineKey, Map<String, Object> variables, String businessKey, String assigneeName, String remark) {
        StartProcessInstanceCmd<ProcessInstance> processInstanceStartProcessInstanceCmd = new StartProcessInstanceCmd<>(defineKey, variables, businessKey, assigneeName, remark);
        return commandExecutor.execute(processInstanceStartProcessInstanceCmd);
    }

    public ProcessInstance getInstance(String procDefKey, String businessKey) {
        return DbHandler.getProcessInstance(procDefKey, businessKey);
    }

    public void stopProcess(String procDefKey, String businessKey, String assigneeName, String remark) {
        ProcessInstance processInstance = DbHandler.getProcessInstance(procDefKey, businessKey);
        if (processInstance == null || !processInstance.getStatus().equals(ProcessEngineConfig.PROC_INST_STATUES_RUN)) {
            throw new RuntimeException("无任务");
        }
        stopProcess(processInstance.getId(), assigneeName, remark);
    }

    public void stopProcess(Long processInstanceId, String assigneeName, String remark) {
        StopProcessCmd stopProcessCmd = new StopProcessCmd(processInstanceId, assigneeName, remark);
        commandExecutor.execute(stopProcessCmd);
    }
}
