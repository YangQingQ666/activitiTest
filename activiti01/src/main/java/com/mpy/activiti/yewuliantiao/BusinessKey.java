package com.mpy.activiti.yewuliantiao;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * businesskey
 */
public class BusinessKey {
    public static void main(String[] args) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance holiday = runtimeService.startProcessInstanceByKey("myProcess_1", "123");
        System.out.println(holiday.getBusinessKey());
        System.out.println(holiday.getName());

        System.out.println(holiday.getDeploymentId());
    }
}
