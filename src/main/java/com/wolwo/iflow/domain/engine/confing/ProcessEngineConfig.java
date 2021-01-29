package com.wolwo.iflow.domain.engine.confing;

/**
 * @author Created by hy
 * @date on 2021/1/22 11:25
 */
public class ProcessEngineConfig {

    /**
     * 流程实例运转中
     */

    public static String PROC_INST_STATUES_RUN = "run";
    /**
     * 流程已结束
     */
    public static String PROC_INST_STATUES_END = "end";

    /**
     * 流程被终止
     */
    public static String PROC_INST_STATUES_STOP = "stop";

    /**
     * 拒绝任务固定文本
     */
    public static String REJECT_TASK_FIXED_TEXT = "驳回。";

    /**
     * 终止流程固定文本
     */
    public static String STOP_PROCESS_FIXED_TEXT = "终止。";

}
