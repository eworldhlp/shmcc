package job;

import bean.OmcServer;
import config.LoadConfig;
import service.impl.HuaWeiLteOrigAlarmCollectImpl;

public class HuaWeiLteAlarmCollectJob {

	public static void main(String[] args) {
		//LoadConfig.loadConfig("omcServer.xml");
		//new HuaWeiLteOrigAlarmCollectImpl().collectHuaWeiLteOrigAlarm(new OmcServer());
		OmcServer server1 = new OmcServer();
		server1.setIpAddress("10.XXX.XXX.XXX");
		server1.setPort(8765);
		server1.setVendor("HUAWEI");
		server1.setOmcId("OMCXXX");
		OmcServer server2 = new OmcServer();
		server2.setIpAddress("10.XXX.XXX.XXX");
		server2.setPort(8765);
		server2.setVendor("HUAWEI");
		server2.setOmcId("OMCXXX");
		OmcServer server3 = new OmcServer();
		server3.setIpAddress("10.XXX.XXX.XXX");
		server3.setPort(8765);
		server3.setVendor("HUAWEI");
		server3.setOmcId("OMCXXX");
		OmcServer server4 = new OmcServer();
		server4.setIpAddress("10.XXX.XXX.XXX");
		server4.setPort(8765);
		server4.setVendor("HUAWEI");
		server4.setOmcId("OMCXXX");
		OmcServer server5 = new OmcServer();
		server5.setIpAddress("10.XXX.XXX.XXX");
		server5.setPort(8765);
		server5.setVendor("HUAWEI");
		server5.setOmcId("OMCXXX");
		
		Thread t1 = new Thread(new HuaWeiLteOrigAlarmCollectImpl(server1));
		t1.start();
		Thread t2 = new Thread(new HuaWeiLteOrigAlarmCollectImpl(server2));
		t2.start();
		Thread t3 = new Thread(new HuaWeiLteOrigAlarmCollectImpl(server3));
		t3.start();
		Thread t4 = new Thread(new HuaWeiLteOrigAlarmCollectImpl(server4));
		t4.start();
		Thread t5 = new Thread(new HuaWeiLteOrigAlarmCollectImpl(server5));
		t5.start();
	}

}
