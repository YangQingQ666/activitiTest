package com.mpy.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 包含网关 排他网关和并行网关的结合体
 */
public class InclusiveGateWayTest1 {
    public ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/baohangateway.bpmn")
                // .addClasspathResource("diagram/holiday1.png")//可以不用添加
                .name("体检流程实例")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void startinit() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "examine";
        Integer userType=1;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("userType",userType);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }
    //任务处理
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("examine")
                .taskAssignee("xiaowang")
                .singleResult();
        if (task != null) {
                taskService.complete(task.getId());

            System.out.println("用户任务执行完毕。。。。。");
        }

    }
}

