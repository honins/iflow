package com.wolwo.iflow.domain.engine.delegate;

import com.wolwo.iflow.domain.entity.Execution;

/**
 * @author Created by hy
 * @date on 2021/1/26 10:11
 */
public class DemoDelegate implements JavaDelegate {

    @Override
    public void execute(Execution execution) {
        System.out.println(">>>>>>Thread.currentThread().getName(): " + Thread.currentThread().getName()
                + "DemoDelegate.execute: " + execution.getVariables());
    }

}
