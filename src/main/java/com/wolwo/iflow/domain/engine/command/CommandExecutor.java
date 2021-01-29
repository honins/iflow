package com.wolwo.iflow.domain.engine.command;

import org.springframework.stereotype.Component;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:28
 */
@Component
public class CommandExecutor {

    public<T> T execute(Command<T> command){
        return command.execute(command);
    }

}
