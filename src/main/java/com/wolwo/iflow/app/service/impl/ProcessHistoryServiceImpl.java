package com.wolwo.iflow.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolwo.iflow.app.service.ProcessHistoryService;
import com.wolwo.iflow.domain.entity.ProcessHistory;
import com.wolwo.iflow.infra.mapper.FlwHisProcessHistoryMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Summer
 * @since 2021-01-22
 */
@Service
public class ProcessHistoryServiceImpl extends ServiceImpl<FlwHisProcessHistoryMapper, ProcessHistory> implements ProcessHistoryService {


}
