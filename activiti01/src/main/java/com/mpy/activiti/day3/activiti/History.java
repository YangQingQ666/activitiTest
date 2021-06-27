package com.mpy.activiti.day3.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;

import java.util.List;

/**
 * 历史数据查看
 */
public class History {
    public static void main(String[] args) {
        ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
        HistoryService historyService = defaultProcessEngine.getHistoryService();
        HistoricActivityInstanceQuery historicActivityInstanceQuery = historyService.createHistoricActivityInstanceQuery();
        historicActivityInstanceQuery.processInstanceId("12501");
        List<HistoricActivityInstance> list = historicActivityInstanceQuery.orderByHistoricActivityInstanceStartTime().asc().list();
        for (HistoricActivityInstance i:list
             ) {
            System.out.println(i.getId());
            System.out.println(i.getActivityId());
            System.out.println(i.getActivityName());
            System.out.println(i.getProcessDefinitionId());
            System.out.println(i.getProcessInstanceId());
        }

    }
}
