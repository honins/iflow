package com.wolwo.iflow.domain.engine.delegate;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.entity.Execution;
import com.wolwo.iflow.domain.entity.ProcessNode;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author Created by hy
 * @date on 2021/1/26 9:36
 */
public class ProcessDelegate {

    public void continueProcessDelegate(Execution execution) {
        ProcessNode processNode = DbHandler.getProcessNode(execution.getCurrentProcNodeId());

        if (StringUtils.hasText(processNode.getDelegateClass())) {
            executeJavaDelegate(processNode.getDelegateClass(), execution);
        }
    }

    private void executeJavaDelegate(String delegateClass, Execution execution) {

        try {
            Class<?> aClass = Class.forName(delegateClass);
            Method method = aClass.getMethod("execute", Execution.class);
            Object newInstance = aClass.newInstance();

            method.invoke(newInstance, execution);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
