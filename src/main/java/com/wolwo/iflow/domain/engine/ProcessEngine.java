package com.wolwo.iflow.domain.engine;

import com.wolwo.iflow.domain.engine.confing.ProcessEngineConfig;
import com.wolwo.iflow.domain.engine.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:26
 */
@Component
public class ProcessEngine {

    ProcessEngineConfig processEngineConfig;

    @Autowired
    CommonEngineService commonEngineService;

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    HistoryService historyService;

}
