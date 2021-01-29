package com.wolwo.iflow.domain.engine.service;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.entity.ProcessDefine;
import com.wolwo.iflow.domain.entity.ProcessNode;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:26
 */
@Component
public class RepositoryService {

    public void saveProcessDefine(ProcessDefine processDefine){
        DbHandler.saveProcessDefine(processDefine);
    }

    public void saveProcessNode(ProcessNode processNode){
        DbHandler.saveProcessNode(processNode);
    }

    public List<ProcessNode> listProcessNode(String procDefKey) {
        return DbHandler.listProcessNode(procDefKey);
    }
}
