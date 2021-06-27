package com.mpy.activiti;

import com.mpy.activiti.day5.Holiday;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 有排他网关的情况 holiday.num>=1   holiday.num>3
 * 排他网关 当多条件都成立只会走一条 如果 多条成立就走id小的那一个步骤
 */
public class ExclusiveGateWayTest {
    public ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/patagateway.bpmn")
                // .addClasspathResource("diagram/holiday1.png")//可以不用添加
                .name("请假流程-流程变量")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void startinit() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "netgate01";
        Map<String,Object> map=new HashMap<String, Object>();
        Holiday holiday=new Holiday();
        holiday.setNum(5F);
        map.put("holiday",holiday);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }
    //任务处理
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("netgate01")
                .taskAssignee("wangwu")
                .singleResult();
        if (task != null) {
                taskService.complete(task.getId());

            System.out.println("用户任务执行完毕。。。。。");
        }

    }
}

