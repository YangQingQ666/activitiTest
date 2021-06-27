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
 * 并行网关 丰富了请假流程  改变了排他网关的条件
 */
public class ParallelGateWayTest1 {
    public ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/bingxinggateway1.bpmn")
                // .addClasspathResource("diagram/holiday1.png")//可以不用添加
                .name("请假流程-流程变量")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    @Test
    public void startinit() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "parallel";
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
                .processDefinitionKey("parallel")
                .taskAssignee("xiaowu")
                .singleResult();
        if (task != null) {
            taskService.complete(task.getId());

            System.out.println("用户任务执行完毕。。。。。");
        }

    }
}

