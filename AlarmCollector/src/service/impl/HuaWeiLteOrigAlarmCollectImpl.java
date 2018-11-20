package service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.apache.commons.net.telnet.TelnetClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.LteAlarm;
import bean.OmcServer;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import service.HuaWeiLteOrigAlarmCollect;
import utils.KafkaProducerFactory;

public class HuaWeiLteOrigAlarmCollectImpl implements HuaWeiLteOrigAlarmCollect,Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(HuaWeiLteOrigAlarmCollectImpl.class.getSimpleName());
	
	private OmcServer omcServer;

	public void setOmcServer(OmcServer omcServer) {
		this.omcServer = omcServer;
	}
	

	public HuaWeiLteOrigAlarmCollectImpl(OmcServer omcServer) {
		super();
		this.omcServer = omcServer;
	}



	@Override
	public void collectHuaWeiLteOrigAlarm() {
		//StringBuffer sb = new StringBuffer();
		try {
            TelnetClient telnetClient = new TelnetClient("vt100");  //指明Telnet终端类型，否则会返回来的数据中文会乱码
            telnetClient.setDefaultTimeout(5000); //socket延迟时间：5000ms
            //telnetClient.setSoTimeout(5000);
            telnetClient.connect(omcServer.getIpAddress(),omcServer.getPort());  //建立一个连接,默认端口是23
            InputStream inputStream = telnetClient.getInputStream(); //读取命令的流
            //InputStreamReader inReader = new InputStreamReader(inputStream,"GB2312");
            BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream,"GB2312"));
            //BufferedOutputStream outputStream = new BufferedOutputStream(telnetClient.getOutputStream());  //写命令的流
            /*char[] b = new char[1024];
            int size;*/
            StringBuffer sBuffer = new StringBuffer();
            LteAlarm alarm = new LteAlarm();
            boolean startFlag=false;
            boolean endFlag=true;
            Producer producer = KafkaProducerFactory.createProducer();
            while(true) {  
            	try{
	               String line = inReader.readLine();
	              if(line.equals("<+++>")){  //告警开始标志
	            	  startFlag = true;
	            	  endFlag = false;
	               }
	              else if(line.equals("<--->")){   //告警结束标志
	            	  startFlag = false;
	            	  endFlag = true;
	               }
	              else{   //告警实际信息，加入sbuffer中
	            	  /*startFlag = true;
	            	  endFlag = false;*/
	            	  String[] split = line.split("  =  ");
	            	  if(split[0].equals("设备告警流水号")){
	            		  try{
	            			  alarm.setEuqipmentAlarmSerial(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setEuqipmentAlarmSerial(null);
	            		  }
	            	  } else if(split[0].equals("网络流水号")){
	            		  try{
	            			  alarm.setNetworkSerial(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setNetworkSerial(null);
	            		  }
	            	  } else if(split[0].equals("对象标识")){
	            		  try{
	            			  alarm.setObjectId(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setObjectId(null);
	            		  }
	            	  } else if(split[0].equals("对象名称")){
	            		  try{
	            			  alarm.setObjectName(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setObjectName(null);
	            		  }
	            	  } else if(split[0].equals("对象类型")){
	            		  try{
	            			  alarm.setObjectType(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setObjectType(null);
	            		  }
	            	  } else if(split[0].equals("网元标识")){
	            		  try{
	            			  alarm.setEntityId(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setEntityId(null);
	            		  }
	            	  } else if(split[0].equals("网元名称")){
	            		  try{
	            			  alarm.setEntityName(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setEntityName(null);
	            		  }
	            	  } else if(split[0].equals("网元类型")){
	            		  try{
	            			  alarm.setEntityType(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setEntityName(null);
	            		  }
	            	  } else if(split[0].equals("告警ID")){
	            		  try{
	            			  alarm.setAlarmId(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setEntityName(null);
	            		  }
	            	  } else if(split[0].equals("告警名称")){
	            		  try{
	            			  alarm.setAlarmName(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setAlarmName(null);
	            		  }
	            	  } else if(split[0].equals("告警种类")){
	            		  try{
	            			  alarm.setAlarmCategory(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setAlarmName(null);
	            		  }
	            	  } else if(split[0].equals("告警级别")){
	            		  try{
	            			  alarm.setAlarmSeverity(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setAlarmName(null);
	            		  }
	            	  } else if(split[0].equals("告警状态")){
	            		  try{
	            			  alarm.setAlarmStatus(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setAlarmName(null);
	            		  }
	            	  } else if(split[0].equals("告警类型")){
	            		  try{
	            			  alarm.setAlarmType(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setAlarmName(null);
	            		  }
	            	  } else if(split[0].indexOf("发生时间")!=-1){
	            		  try{
	            			  alarm.setEventTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(split[1]));
	            		  } catch(ParseException e){}
	            	  } else if(split[0].equals("定位信息")){
	            		  try{
	            			  alarm.setDetail(split[1]);
	            		  } catch(ArrayIndexOutOfBoundsException e){
	            			  alarm.setDetail(null);
	            		  }
	            	  } 
	              }
	              if(endFlag==true&&startFlag==false){   //1个完整告警采集完成
	            	  sBuffer.append((alarm.getEuqipmentAlarmSerial()==null?"":alarm.getEuqipmentAlarmSerial())+"##"+
	            			  (alarm.getNetworkSerial()==null?"":alarm.getNetworkSerial())+"##"+
	            			  (alarm.getEntityId()==null?"":alarm.getEntityId())+"##"+
	            			  (alarm.getEntityName()==null?"":alarm.getEntityName())+"##"+
	            			  (alarm.getEntityType()==null?"":alarm.getEntityType())+"##"+
	            			  (alarm.getObjectId()==null?"":alarm.getObjectId())+"##"+
	            			  (alarm.getObjectName()==null?"":alarm.getObjectName())+"##"+
	            			  (alarm.getObjectType()==null?"":alarm.getObjectType())+"##"+
	            			  (alarm.getAlarmId()==null?"":alarm.getAlarmId())+"##"+
	            			  (alarm.getAlarmName()==null?"":alarm.getAlarmName())+"##"+
	            			  (alarm.getAlarmCategory()==null?"":alarm.getAlarmCategory())+"##"+
	            			  (alarm.getAlarmSeverity()==null?"":alarm.getAlarmSeverity())+"##"+
	            			  (alarm.getAlarmType()==null?"":alarm.getAlarmType())+"##"+
	            			  (alarm.getAlarmStatus()==null?"":alarm.getAlarmStatus())+"##"+
	            			  (alarm.getEventTime()==null?"":new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(alarm.getEventTime()))+"##"+
	            			  (alarm.getDetail()==null?"":alarm.getDetail()));
	            	  //System.out.println(sBuffer.toString());
	            	  try{
		            	  if(alarm.getAlarmCategory().equals("故障")){
			            	  try{
			            		  producer.send(new KeyedMessage<String,String>("com.cmcc.shgcp.alarmcollector.lte.huawei", sBuffer.toString()));  //写入kafka
			            	  } catch(Exception e){
			            		  logger.error(e.getMessage());
			            	  }
		            	  }
	            	  } catch(NullPointerException ex){}
	            	  //重新初始化
	            	  alarm = new LteAlarm();  
	            	  sBuffer = new StringBuffer();
	            	  startFlag=false;
	                  endFlag=true;
	              }
	               
            	} catch(SocketTimeoutException e){
            		//logger.error(omcServer.getIpAddress()+e.getMessage());
            	} catch(NullPointerException e){
            		//logger.error(e.getMessage());
            	}            	
            }
           
            //telnetClient.disconnect();
        } catch (SocketException e) {
        	e.printStackTrace();
        	logger.error(e.getMessage());          
        } catch (IOException e) {
        	logger.error(e.getMessage());
        } 
		//return sb;
	}

	@Override
	public void run() {
		this.collectHuaWeiLteOrigAlarm(); 
	}

}
