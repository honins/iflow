package com.wolwo.iflow.api.controller;

import com.wolwo.iflow.domain.engine.FlowOperator;
import com.wolwo.iflow.domain.engine.service.HistoryService;
import com.wolwo.iflow.domain.engine.service.RepositoryService;
import com.wolwo.iflow.domain.engine.service.RuntimeService;
import com.wolwo.iflow.domain.engine.service.TaskService;
import com.wolwo.iflow.domain.entity.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:33
 */
@RestController
@RequestMapping("/flow/demo")
public class FlowDemoController {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    FlowOperator flowOperator;

    @ApiOperation("新增流程定义")
    @PostMapping(value = "/define/create")
    public void createDefine(@RequestBody ProcessDefine processDefine) {
        repositoryService.saveProcessDefine(processDefine);
    }

    @ApiOperation("新增流程节点")
    @PostMapping(value = "/node/create")
    public void createNode(@RequestBody ProcessNode processNode) {
        repositoryService.saveProcessNode(processNode);
    }

    @ApiOperation("列出流程节点")
    @GetMapping(value = "/node/list")
    public List<ProcessNode> listNodes(@RequestParam String procDefKey) {
        return repositoryService.listProcessNode(procDefKey);
    }

    @ApiOperation("获得流程节点示例")
    @GetMapping(value = "/instance/get")
    public ProcessInstance getInstance(@RequestParam String procDefKey,
                                       @RequestParam String businessKey) {
        return runtimeService.getInstance(procDefKey, businessKey);
    }

    @ApiOperation("启动流程")
    @PostMapping(value = "/process/start")
    public ProcessInstance startProcess(@RequestParam String defineKey,
                                        @RequestParam String businessKey,
                                        @RequestParam String assigneeName,
                                        @RequestParam String remark) {
        return runtimeService.startProcessInstanceByKey(defineKey, null, businessKey, assigneeName, remark);
    }

    @ApiOperation("终止流程")
    @PostMapping(value = "/process/end")
    public void endProcess(@RequestParam String defineKey,
                           @RequestParam String businessKey,
                           @RequestParam String assigneeName,
                           @RequestParam String remark) {
        runtimeService.stopProcess(defineKey, businessKey, assigneeName, remark);
    }

    @ApiOperation("通过流程任务")
    @PostMapping(value = "/task/complete")
    public void completeTask(@RequestParam String procDefKey,
                             @RequestParam String businessKey,
                             @RequestParam String assigneeName,
                             @RequestParam String remark) {
        Task task = taskService.queryTask(procDefKey, businessKey);
        if (task != null) {
            taskService.completeTask(task.getId(), assigneeName, remark);
        }
//        taskService.completeTask(procDefKey, businessKey, assigneeName, remark);
    }

    @ApiOperation("拒绝流程任务")
    @PostMapping(value = "/task/reject")
    public void rejectTask(@RequestParam String procDefKey,
                           @RequestParam String businessKey,
                           @RequestParam String assigneeName,
                           @RequestParam String remark) {
        Task task = taskService.queryTask(procDefKey, businessKey);
        if (task != null) {
            taskService.rejectTask(task.getId(), assigneeName, remark);
        }
    }

    @ApiOperation("列出流程历史记录")
    @GetMapping(value = "/history/list")
    public List<ProcessHistory> listHistory(@RequestParam String procDefKey,
                                            @RequestParam String businessKey) {
        return flowOperator.listHistory(procDefKey, businessKey);
    }
}

