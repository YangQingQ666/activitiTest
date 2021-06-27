package com.mpy.activiti;

import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

import java.util.List;

/**
 * 组分任务测试类
 */
public class GroupTaskTest {
    public ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    //  部署
    @Test
    public void deployment() {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday1.bpmn")
                // .addClasspathResource("diagram/holiday1.png")//可以不用添加
                .name("请假流程-流程变量")
                .deploy();
        System.out.println(deploy.getId());
        System.out.println(deploy.getName());
    }

    //启动
    //act_ru_variable
    @Test
    public void startinit() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        String key = "holiday1";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(key);
        System.out.println(processInstance.getName());
        System.out.println(processInstance.getProcessDefinitionId());
    }

    //任务处理
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday1")
                .taskAssignee("xiaozhang")
                .singleResult();
        if (task != null) {
            taskService.complete(task.getId());
            System.out.println("用户任务执行完毕。。。。。");
        }

    }

    //查询候选用户的主任务
    @Test
    public void findGroupUserTask() {
        String uer = "zhangsan";
        String key = "holiday1";
        TaskService taskService = processEngine.getTaskService();
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskCandidateUser(uer)
                .list();

        for (Task t : list
        ) {
            System.out.println(t.getName()); //部门经理审批
            System.out.println(t.getId());//57502
            System.out.println(t.getAssignee());//null //为null说明当前zhangsan只是候选人还不是任务的执行人
            System.out.println(t.getProcessInstanceId()); //55001

        }
    }
    //拾取任务 将候选人转为负责人
    @Test
    public void claimTask(){
        TaskService taskService = processEngine.getTaskService();
        String candidate_user = "zhangsan";
        String key = "holiday1";
        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskCandidateUser(candidate_user)
                .singleResult();
        if (task!=null){
            taskService.claim(task.getId(),candidate_user);
            System.out.println("任务拾取完毕。。。。");
        }
    }
    //当前用户查询任务
    @Test
    public void findTask(){
        TaskService taskService = processEngine.getTaskService();
        String assignee = "zhangsan";
        String key = "holiday1";
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee)
                .list();
        for (Task t : list
        ) {
            System.out.println(t.getName()); //部门经理审批
            System.out.println(t.getId());//57502
            System.out.println(t.getAssignee());//zhangsan
            System.out.println(t.getProcessInstanceId()); //55001

        }
    }

    //完成任务
    @Test
    public void completTask(){
        TaskService taskService = processEngine.getTaskService();
        String assignee="lisi";
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday1")
                .taskAssignee(assignee)
                .singleResult();
        if (task != null) {
            taskService.complete(task.getId());
            System.out.println("用户任务执行完毕。。。。。");
        }
    }
    //归还任务
    @Test
    public void returnTask(){
        TaskService taskService = processEngine.getTaskService();
        String userId="zhangsan";
        Task task = taskService.createTaskQuery()
                .taskId("70002")
                .taskAssignee(userId)
                .singleResult();
        if (task != null) {
            taskService.setAssignee(task.getId(),null);
            System.out.println("用户任务归还完毕。。。。。");
        }
    }
    //交接任务
    @Test
    public void changeTask(){
        TaskService taskService = processEngine.getTaskService();
        String assignee="zhangsan";
        String candidate_user="lisi";
        Task task = taskService.createTaskQuery()
                .processDefinitionKey("holiday1")
                .taskAssignee(assignee)
                .singleResult();
        if (task != null) {
            taskService.setAssignee(task.getId(),candidate_user);
            System.out.println("用户任务交接完毕。。。。。");
        }
    }

}
