package com.mpy.activiti.day3.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;
/*
* 查询流程*/
public class QueryProcessDefinition {
    public static void main(String[] args) {
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        List<ProcessDefinition> holiday = processDefinitionQuery.processDefinitionKey("parallel")
                .orderByProcessDefinitionVersion().desc().list();
        for (ProcessDefinition p:holiday
             ) {
            System.out.println("name:"+p.getName());
            System.out.println(p.getId());
            System.out.println(p.getKey());
            System.out.println(p.getDeploymentId());
        }
    }
}
