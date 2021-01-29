package com.wolwo.iflow.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Created by hy
 * @date on 2021/1/26 14:51
 */
@Configuration
@EnableAsync
public class ThreadPoolExecutor {

    private static ThreadPoolTaskExecutor threadPoolTaskExecutor;

    public static ThreadPoolTaskExecutor processThreadPoolExecutor() {
        if (threadPoolTaskExecutor == null) {
            threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
            threadPoolTaskExecutor.initialize();

            return threadPoolTaskExecutor;
        }

        return threadPoolTaskExecutor;
    }

}
