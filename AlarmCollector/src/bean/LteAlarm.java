package bean;

import java.util.Date;

public class LteAlarm {

	private String euqipmentAlarmSerial;  //
	private String networkSerial;  //网络流水号
	private String entityId;  //网元标识
	private String entityName;  //网元名称
	private String entityType;  //网元类型
	private String objectId;  //对象标识
	private String objectName;  //对象名称
	private String objectType;  //对象类型
	private String alarmId;
	private String alarmName;
	private String alarmCategory;   //告警种类
	private String alarmSeverity;    //告警级别
	private String alarmStatus;  //告警状态
	private String alarmType;  //告警类型
	private Date eventTime;  //发生时间
	private Date initTime;  //产生时间
	private Date ackTime;  //确实时间
	private Date clearTime;  //消除时间
	private String detail;
	
	
	public String getEuqipmentAlarmSerial() {
		return euqipmentAlarmSerial;
	}
	public void setEuqipmentAlarmSerial(String euqipmentAlarmSerial) {
		this.euqipmentAlarmSerial = euqipmentAlarmSerial;
	}
	public String getNetworkSerial() {
		return networkSerial;
	}
	public void setNetworkSerial(String networkSerial) {
		this.networkSerial = networkSerial;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public String getEntityName() {
		return entityName;
	}
	public void setEntityName(String entityName) {
		this.entityName = entityName;
	}
	public String getEntityType() {
		return entityType;
	}
	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}
	public String getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(String alarmId) {
		this.alarmId = alarmId;
	}
	public String getAlarmName() {
		return alarmName;
	}
	public void setAlarmName(String alarmName) {
		this.alarmName = alarmName;
	}
	public String getAlarmCategory() {
		return alarmCategory;
	}
	public void setAlarmCategory(String alarmCategory) {
		this.alarmCategory = alarmCategory;
	}
	public String getAlarmSeverity() {
		return alarmSeverity;
	}
	public void setAlarmSeverity(String alarmSeverity) {
		this.alarmSeverity = alarmSeverity;
	}
	public String getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(String alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public Date getInitTime() {
		return initTime;
	}
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	public Date getAckTime() {
		return ackTime;
	}
	public void setAckTime(Date ackTime) {
		this.ackTime = ackTime;
	}
	public Date getClearTime() {
		return clearTime;
	}
	public void setClearTime(Date clearTime) {
		this.clearTime = clearTime;
	}
	
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public String getObjectName() {
		return objectName;
	}
	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	public String getObjectType() {
		return objectType;
	}
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	@Override
	public String toString() {
		return "LteAlarm [euqipmentAlarmSerial=" + euqipmentAlarmSerial + ", networkSerial=" + networkSerial
				+ ", entityId=" + entityId + ", entityName=" + entityName + ", entityType=" + entityType + ", objectId="
				+ objectId + ", objectName=" + objectName + ", objectType=" + objectType + ", alarmId=" + alarmId
				+ ", alarmName=" + alarmName + ", alarmCategory=" + alarmCategory + ", alarmSeverity=" + alarmSeverity
				+ ", alarmStatus=" + alarmStatus + ", alarmType=" + alarmType + ", eventTime=" + eventTime
				+ ", initTime=" + initTime + ", ackTime=" + ackTime + ", clearTime=" + clearTime + "]";
	}
	
}
