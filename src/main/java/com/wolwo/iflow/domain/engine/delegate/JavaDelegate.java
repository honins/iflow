package com.wolwo.iflow.domain.engine.delegate;

import com.wolwo.iflow.domain.entity.Execution;

/**
 * @author Created by hy
 * @date on 2021/1/26 10:10
 */
public interface JavaDelegate {

    /**
     * 执行委托类
     * @param execution
     */
    void execute(Execution execution);

}
