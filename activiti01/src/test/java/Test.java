import org.activiti.engine.*;

public class Test {
    @org.junit.Test
    public void testGenTable(){
        ProcessEngineConfiguration configuration=ProcessEngineConfiguration
                .createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        ProcessEngine processEngine=configuration.buildProcessEngine();
        System.out.println(processEngine);
        HistoryService historyService = processEngine.getHistoryService();
    }

    @org.junit.Test
    public void testActitab(){
        //一次性创建 配置文件必须为activiti.cfg.xml  bean的id必须为processEngineConfiguration
        ProcessEngine processEngine=ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

}
