package com.wolwo.iflow.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolwo.iflow.app.service.ProcessNodeService;
import com.wolwo.iflow.domain.entity.ProcessNode;
import com.wolwo.iflow.infra.mapper.FlwReProcessNodeMapper;
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
public class ProcessNodeServiceImpl extends ServiceImpl<FlwReProcessNodeMapper, ProcessNode> implements ProcessNodeService {


}
