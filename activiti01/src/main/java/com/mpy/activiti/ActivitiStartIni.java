package com.mpy.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * 流程实例启动
 */
public class ActivitiStartIni {
    public static void main(String[] args) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance myProcess_1 = runtimeService.startProcessInstanceByKey("myProcess_1");
        System.out.println(myProcess_1.getDeploymentId());
        System.out.println(myProcess_1.getId());
        System.out.println(myProcess_1.getBusinessKey());
        System.out.println(myProcess_1.getName());
        System.out.println(myProcess_1.getActivityId());


    }
}
