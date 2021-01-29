package com.wolwo.iflow.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolwo.iflow.app.service.ProcessInstanceService;
import com.wolwo.iflow.domain.entity.ProcessInstance;
import com.wolwo.iflow.infra.mapper.FlwRuProcessInstanceMapper;
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
public class ProcessInstanceServiceImpl extends ServiceImpl<FlwRuProcessInstanceMapper, ProcessInstance> implements ProcessInstanceService {


}
