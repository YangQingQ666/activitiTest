package com.mpy.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * 查询当前用户的任务列表
 */
public class ActivitiTaskTest {
    public static void main(String[] args) {
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
//        List<Task> list = taskService.createTaskQuery()  //查询多项目
        Task task = taskService.createTaskQuery() //单项目
                .processDefinitionKey("myProcess_1")
                .taskAssignee("zhangsan")
                .singleResult();//单项目
//.list();多项目

        /*for (Task task:list
             ) {*/
            System.out.println(task.getAssignee());
            System.out.println(task.getId());
            System.out.println(task.getProcessInstanceId());
            System.out.println(task.getName());
        /*}*/

    }
}
