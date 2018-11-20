package service.impl;

import java.text.SimpleDateFormat;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.NokiaLteAlarm;
import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import net.sf.json.JSONObject;
import service.NokiaLteOrigAlarmCollect;
import utils.KafkaProducerFactory;

public class NokiaLteOrigAlarmCollectImpl implements NokiaLteOrigAlarmCollect, Callable<Long> {
	
	static Logger logger = LoggerFactory.getLogger(NokiaLteOrigAlarmCollectImpl.class.getSimpleName());
	
	private String alarmMessage;
	public String getAlarmMessage() {
		return alarmMessage;
	}
	public void setAlarmMessage(String alarmMessage) {
		this.alarmMessage = alarmMessage;
	}
	private Producer producer;
	public void setProducer(Producer producer) {
		this.producer = producer;
	}
	
	
	public NokiaLteOrigAlarmCollectImpl(String alarmMessage, Producer producer) {
		super();
		this.alarmMessage = alarmMessage;
		this.producer = producer;
	}
	@Override
	public long collectNokiaLteOrigAlarm() {
		JSONObject object = JSONObject.fromObject(alarmMessage);
		NokiaLteAlarm alarm = (NokiaLteAlarm) JSONObject.toBean(object, NokiaLteAlarm.class);
		StringBuffer sb  = new StringBuffer();
		sb.append(alarm.getAlarmSeq()+"##"+alarm.getAlarmTitle()+"##"+alarm.getAlarmStatus()+"##"+alarm.getAlarmType()+"##"
				+alarm.getOrigSeverity()+"##"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(alarm.getEventTime())+"##"
				+alarm.getAlaramId()+"##"+alarm.getSpecificProblemID()+"##"+alarm.getSpecificProblem()+"##"+alarm.getNeUID()+"##"
				+alarm.getNeName()+"##"+alarm.getNeType()+"##"+alarm.getObjectUID()+"##"+alarm.getObjectUID()+"##"
				+alarm.getObjectName()+"##"+alarm.getObjectType()+"##"+alarm.getLocationInfo()+"##"+alarm.getAddInfo()+"##"
				+alarm.getrNeUID()+"##"+alarm.getrNeName()+"##"+alarm.getrNeType());
		
		try{
			producer.send(new KeyedMessage<String,String>("com.cmcc.shgcp.alarmcollector.lte.nokia", sb.toString()));  //写入kafka
   	  	} catch(Exception e){
   		    logger.error(e.getMessage());
   	  	}
		return alarm.getAlarmSeq();
	}
	@Override
	public Long call() throws Exception {
		long alarmSeq = this.collectNokiaLteOrigAlarm();
		return alarmSeq;
	}
	
	
}
