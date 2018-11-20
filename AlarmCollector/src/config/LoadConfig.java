package config;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import bean.OmcServer;

public class LoadConfig {
	
	final static String configPath = LoadConfig.class.getResource("").toString().replace("bin", "src");
	
	public static Map<String,OmcServer> loadConfig(String configFileName) {
		Map<String, OmcServer> map=new LinkedHashMap<String, OmcServer>();
		File configFile = new File("");
		SAXReader reader = null;
		Document document=null;
		try{
			
			reader=new SAXReader();
			document=reader.read(configFile);
		} catch(Exception ex){
			configFile = new File(configPath+File.separator+configFileName);
			//configFile = new File("D:/Workspaces/MyEclipse 10/SHGCP/src/config/nrm_enbfunction.xml");
			reader=new SAXReader();
			try {
				document=reader.read(configFile);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}	
		
		Element root=document.getRootElement();
		Iterator<Element> pmitr=root.elementIterator("cm");
		while(pmitr.hasNext()){
			Element pm=pmitr.next();
			OmcServer pa=new OmcServer();
			pa.setOmcId(pm.element("omcId").getStringValue());
			pa.setIpAddress(pm.element("ip").getStringValue());
			pa.setPort(Integer.parseInt(pm.element("port").getStringValue()));
			pa.setVendor(pm.element("vendor").getStringValue());
			System.out.println(pa.toString());
			map.put(pa.getIpAddress(), pa);
		}		
		return map;	
	}
	
	public static void main(String[] args) {
		System.out.println(configPath);
	}

}
