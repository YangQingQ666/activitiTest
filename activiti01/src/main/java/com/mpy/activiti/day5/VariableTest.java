package com.mpy.activiti.day5;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 流程测试 uel表达式设置分支条件 流程测试
 */
public class VariableTest {
    public ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    @Test
    public void deployment(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday4.bpmn")
                .addClasspathResource("diagram/holiday4.png")
                .name("请假流程-流程变量")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    //act_ru_variable
    @Test
    public void startinit(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key="holiday4";
        Map<String,Object> map=new HashMap<String, Object>();
        Holiday holiday=new Holiday();
        holiday.setNum(1F);//num=<3天 分支测试 zhangsan->lisi->zhaoliu  num>3 分支测试 zhangsan->lisi->wangwu->zhaoliu
        map.put("holiday",holiday);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key, map);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    //num<1天 分支测试 zhangsan->lisi->zhaoliu
    @Test
    public void testcomplet(){
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday4")
                .taskAssignee("zhangsan").singleResult();
        if (task!=null){
            taskService.complete(task.getId());
            System.out.println("任务执行完毕");
        }
    }

    //在完成某个任务时添加 分支条件
    @Test
    public void startinit2(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key="holiday4";
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(key);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }
    //在完成某个任务时添加 分支条件
    @Test
    public void variable2(){
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday4")
                .taskAssignee("zhangsan").singleResult();
        Map<String,Object> map=new HashMap<String, Object>();
        Holiday holiday=new Holiday();
        holiday.setNum(1F);//num=<3天 分支测试 zhangsan->lisi->zhaoliu  num>3 分支测试 zhangsan->lisi->wangwu->zhaoliu
        map.put("holiday",holiday);
        if (task!=null){
            //设置流程变量 num
            taskService.complete(task.getId(),map);
            System.out.println("任务执行完毕");
        }
    }
}
