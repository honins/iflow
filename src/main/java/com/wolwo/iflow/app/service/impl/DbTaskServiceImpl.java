package com.wolwo.iflow.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wolwo.iflow.app.service.DbTaskService;
import com.wolwo.iflow.domain.entity.Task;
import com.wolwo.iflow.infra.mapper.FlwRuTaskMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Summer
 * @since 2021-01-22
 */
@Service
public class DbTaskServiceImpl extends ServiceImpl<FlwRuTaskMapper, Task> implements DbTaskService {


}
