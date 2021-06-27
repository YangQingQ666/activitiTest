package com.mpy.activiti.day3.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;

/*
* 查询bytearraybpmn png  并保存
* */
public class QueryBpmnFile {
    public static void main(String[] args) throws IOException {
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionKey("myProcess_1");
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        String deploymentId = processDefinition.getDeploymentId();
        /*参数 第一个 部署id 第二个 代表资源名称*/
        InputStream png=repositoryService.getResourceAsStream(deploymentId,processDefinition.getDiagramResourceName());
        InputStream bpmn=repositoryService.getResourceAsStream(deploymentId,processDefinition.getResourceName());

        OutputStream pngo=new FileOutputStream("E://company/"+processDefinition.getDiagramResourceName());
        OutputStream bpmno=new FileOutputStream("E://company/"+processDefinition.getResourceName());
        IOUtils.copy(png,pngo);
        IOUtils.copy(bpmn,bpmno);
        pngo.close();
        bpmno.close();
        png.close();
        bpmn.close();


    }
}
