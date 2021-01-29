package com.wolwo.iflow.domain.engine.service;

import com.wolwo.iflow.domain.engine.command.CommandExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:26
 */
@Component
public class CommonEngineService {

    @Autowired
    CommandExecutor commandExecutor;

}
