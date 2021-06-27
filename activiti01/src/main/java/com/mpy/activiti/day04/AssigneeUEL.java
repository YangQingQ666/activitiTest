package com.mpy.activiti.day04;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;

import java.util.Map;
import java.util.HashMap;

/**
 * 动态设置assignee
 */
public class AssigneeUEL {
    public ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    @Test
    public void test01(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //设置assignee,用户在界面时选择执行人
        Map<String,Object> map=new HashMap<>();
        map.put("assignee0","zhangsan");
        map.put("assignee1","lisi");
        map.put("assignee2","wangwu");
        //启动流程实例，设置流程定义assignee的值
        ProcessInstance holiday = runtimeService.startProcessInstanceByKey("holiday", map);
        System.out.println(processEngine.getName());

    }
}
