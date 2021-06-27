package com.mpy.activiti.day5;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class VariableById {
    public ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
    //Activiti流程变量-通过流程实例ID设置
    //在完成某个任务时添加 分支条件
    @Test
    public void startinit2(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key="holiday4";
        ProcessInstance processInstance = runtimeService
                .startProcessInstanceByKey(key);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());//holiday4:1:4
        System.out.println(processInstance.getId()); //35001
    }
    //设置流程变量 利用runtimeService
    @Test
    public void variable2(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String instanceid="35001";//processInstance.getId()
//        Map<String,Object> map=new HashMap<>();
        Holiday holiday=new Holiday();
        holiday.setNum(6F);//num=<3天 分支测试 zhangsan->lisi->zhaoliu  num>3 分支测试 zhangsan->lisi->wangwu->zhaoliu
        //多个流程变量可以用setVariables()
        runtimeService.setVariable(instanceid,"holiday",holiday);
    }

    //设置流程变量 利用taskService
    @Test
    public void variable3(){
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday4")
                .taskAssignee("zhangsan").singleResult();
//        Map<String,Object> map=new HashMap<>();
        Holiday holiday=new Holiday();
        holiday.setNum(6F);//num=<3天 分支测试 zhangsan->lisi->zhaoliu  num>3 分支测试 zhangsan->lisi->wangwu->zhaoliu
        //多个流程变量可以用setVariables()
        taskService.setVariable(task.getId(),"holiday",holiday);
    }
}
