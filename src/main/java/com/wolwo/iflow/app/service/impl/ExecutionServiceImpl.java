package com.wolwo.iflow.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolwo.iflow.app.service.ExecutionService;
import com.wolwo.iflow.domain.entity.Execution;
import com.wolwo.iflow.infra.mapper.FlwRuExecutionMapper;
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
public class ExecutionServiceImpl extends ServiceImpl<FlwRuExecutionMapper, Execution> implements ExecutionService {


}
