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
 * 并行网关 丰富了请假流程
 */
public class InspectTest {
    public ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/outtask.bpmn")
                // .addClasspathResource("diagram/holiday1.png")//可以不用添加
                .name("考评流程实例")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }
    //审核不通过
    @Test
    public void startinit() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "inspect";
        Integer status=2;
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("status",status);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }
    //审核通过
    @Test
    public void startinit1() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "inspect";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }
    //任务处理
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("inspect")
                .taskAssignee("lisi")
                .singleResult();
        if (task != null) {
            //操作员的给出意见后走流程 status 1 通过 2 不通过
            /*Integer status=1;
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("status",status);
            taskService.complete(task.getId(),map);*/
            taskService.complete(task.getId());
            System.out.println("用户任务执行完毕。。。。。");
        }

    }
}

