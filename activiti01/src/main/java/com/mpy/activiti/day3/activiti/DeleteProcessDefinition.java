package com.mpy.activiti.day3.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;

import java.util.List;
/*
* 删除流程*/
public class DeleteProcessDefinition {

    public static void main(String[] args) {
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //repositoryService.deleteDeployment("1");//流程已完成删除
repositoryService.deleteDeployment("147501",true);
    }
}
