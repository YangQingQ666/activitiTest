package com.mpy.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * 流程定义的部署
 */
public class AcitivitiDeployment {
    public static void main(String[] args) {
        //创建
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //部署
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn")
                .addClasspathResource("diagram/holiday.png")
                .name("请假申请流程")
                .deploy();
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
        System.out.println(deployment.getDeploymentTime());
    }

    /*public static void main(String[] args) {
        //创建
        ProcessEngine processEngine= ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //转化zip
        InputStream is=AcitivitiDeployment.class.getClassLoader().getResourceAsStream("diagram/holidayBPMN.zip");
        ZipInputStream zipInputStream=new ZipInputStream(is);

        //部署
        Deployment deployment = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .name("请假申请流程")
                .deploy();
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());
        System.out.println(deployment.getDeploymentTime());
    }*/
}
