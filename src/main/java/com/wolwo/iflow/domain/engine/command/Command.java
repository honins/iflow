package com.wolwo.iflow.domain.engine.command;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:28
 */
public interface Command<T> {

    T execute(Command command);

}
