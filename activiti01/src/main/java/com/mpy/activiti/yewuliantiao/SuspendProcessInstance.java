package com.mpy.activiti.yewuliantiao;

import org.activiti.engine.*;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

/**
 * 实现全部流程实例的激活与挂起
 */
public class SuspendProcessInstance {
    /**
     * 制度更改
     */
    @Test
    public void test1(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = defaultProcessEngine.getRepositoryService();
        //查询流程定义的对象
        ProcessDefinition myProcess_1 = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("myProcess_1").singleResult();
        //流程实例的id
        String id = myProcess_1.getId();
        //得到当前流程定义的实例是否都为暂停状态
        boolean suspended = myProcess_1.isSuspended();
        if (suspended){//挂起,就可以激活
            repositoryService.activateProcessDefinitionById(id,true,null);
            System.out.println("实例"+id+"激活");
        }else{
            repositoryService.suspendProcessDefinitionById(id,true,null);
            System.out.println("实例"+id+"挂起");
        }
    }
    /**
     * 单个激活和挂起
     */
    @Test
    public void test2(){
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = defaultProcessEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceBusinessKey("123").singleResult();
        String id = processInstance.getId();
        boolean suspended = processInstance.isSuspended();
        if (suspended){//挂起,就可以激活
            runtimeService.activateProcessInstanceById(id);
            System.out.println("实例"+id+"激活");
        }else{
            runtimeService.suspendProcessInstanceById(id);
            System.out.println("实例"+id+"挂起");
        }
    }
    /**
     * 测试任务挂起是不是还能完成
     * 如果不能成功是否会抛异常  ActivitiException Cannot complete a suspended task
     */
    @Test
    public void  test3(){
        ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("myProcess_1")
                .taskAssignee("zhangsan")
                .singleResult();
        taskService.complete(task.getId());

    }
}
