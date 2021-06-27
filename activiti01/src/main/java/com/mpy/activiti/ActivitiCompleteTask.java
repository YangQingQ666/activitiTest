package com.mpy.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

public class ActivitiCompleteTask {
    /*public static void main(String[] args) {
        ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        taskService.complete("7502");
    }*/

    //任务处理
    public static void main(String[] args) {
        ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("zhangsan")
                .singleResult();
        taskService.complete(task.getId());
    }
}
