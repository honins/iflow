package com.wolwo.iflow.domain.engine.service;

import com.wolwo.iflow.domain.engine.DbHandler;
import com.wolwo.iflow.domain.entity.ProcessHistory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:26
 */
@Component
public class HistoryService {
    public List<ProcessHistory> list(String procDefKey, String businessKey) {
        return DbHandler.listProcessHistory(procDefKey, businessKey);
    }
}
