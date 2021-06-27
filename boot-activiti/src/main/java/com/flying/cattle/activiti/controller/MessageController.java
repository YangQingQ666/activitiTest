package com.flying.cattle.activiti.controller;

import com.flying.cattle.activiti.config.SecurityUtil;
import com.flying.cattle.activiti.entity.Message;
import com.flying.cattle.activiti.service.MessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (Message)表控制层
 *
 * @author makempy
 * @since 2021-05-21 15:55:45
 */
@RestController
@RequestMapping("message")
public class MessageController {
    /**
     * 服务对象
     */
    @Resource
    private MessageService messageService;
    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private RepositoryService repositoryService;
    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation("xinxi")
    @GetMapping("selectOne")
    public Map<String,Object> selectOne(Integer id) {
        Message message = this.messageService.queryById(id);
        securityUtil.logInAs("salaboy");
        List<HistoricProcessInstance> list = historyService.createHistoricProcessInstanceQuery().processInstanceBusinessKey(message.getId().toString()).list();
        Map<String,Object> map=new HashMap<>();
        map.put("me",message);
        map.put("process",list);
        return map;
    }
    @ApiOperation("查询任务")
    @GetMapping("selectTasks")
    public Object selectTasks() {
        securityUtil.logInAs("salaboy");
        Page<Task> page = taskRuntime.tasks(Pageable.of(0, 10));
        return page;
    }


    @ApiOperation("insert")
    @ApiImplicitParam(value = "申请信息",name = "message",dataTypeClass = Message.class,required = true)
    @PostMapping("insert")
    public Message insert(@RequestBody Message message) {
        Message insert = this.messageService.insert(message);
        securityUtil.logInAs("salaboy");
        ProcessInstance processInstance = processRuntime
                .start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1").withBusinessKey(insert.getId()+"").build());
        System.err.println("流程实例ID：" + processInstance.getId());
        return insert;
    }

    @ApiOperation("updatestatus")
    @GetMapping("updatestatus")
    public String updatestatus() {
        securityUtil.logInAs("salaboy");
        ProcessDefinition myProcess_1 = repositoryService.createProcessDefinitionQuery().processDefinitionKey("myProcess_1")
                .singleResult();
        boolean suspended = myProcess_1.isSuspended();
        if (suspended) {
            repositoryService.activateProcessDefinitionById(myProcess_1.getId(), true, null);
            System.out.println(myProcess_1.getId() + "已激活");

        } else {
            //7.如果是激活状态,则改为挂起状态
            //参数1是流程定义的id,参数2是是否要激活,参数3是激活时间
            repositoryService.suspendProcessDefinitionById(myProcess_1.getId(), true, null);
            System.out.println(myProcess_1.getId() + "已挂起");
        }
        return suspended+"2";
    }

    @ApiOperation("领取任务")
    @ApiImplicitParam(value = "String",name = "taskId",dataTypeClass = Message.class,required = true)
    @PostMapping("lingqutack")
    public Task lingqutack(@RequestBody String taskId) {
        securityUtil.logInAs("erdemedeiros");
        Task claim = taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(taskId).build());
        return claim;
    }

    @ApiOperation("完成任务")
    @ApiImplicitParam(value = "String",name = "taskId",dataTypeClass = Message.class,required = true)
    @PostMapping("completask")
    public Task completask(@RequestBody String taskId) {
        securityUtil.logInAs("erdemedeiros");
        Task claim = taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(taskId).build());
        return claim;
    }
}
