package bean;

import java.util.Date;

public class NokiaLteAlarm {
	
	private long alarmSeq;
	private String alarmTitle;
	private int alarmStatus;
	private String alarmType;
	private int origSeverity;
	private Date eventTime;
	private long alaramId;
	private int specificProblemID;
	private String specificProblem;
	private String neUID;
	private String neName;
	private String neType;
	private String objectUID;
	private String objectName;
	private String objectType;
	private String locationInfo;
	private String addInfo;
	private String rNeUID;
	private String rNeName;
	private String rNeType;
	
	public long getAlarmSeq() {
		return alarmSeq;
	}
	public void setAlarmSeq(long alarmSeq) {
		this.alarmSeq = alarmSeq;
	}
	public String getAlarmTitle() {
		return alarmTitle;
	}
	public void setAlarmTitle(String alarmTitle) {
		this.alarmTitle = alarmTitle;
	}
	public int getAlarmStatus() {
		return alarmStatus;
	}
	public void setAlarmStatus(int alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public int getOrigSeverity() {
		return origSeverity;
	}
	public void setOrigSeverity(int origSeverity) {
		this.origSeverity = origSeverity;
	}
	public Date getEventTime() {
		return eventTime;
	}
	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}
	public long getAlaramId() {
		return alaramId;
	}
	public void setAlaramId(long alaramId) {
		this.alaramId = alaramId;
	}
	public int getSpecificProblemID() {
		return specificProblemID;
	}
	public void setSpecificProblemID(int specificProblemID) {
		this.specificProblemID = specificProblemID;
	}
	public String getSpecificProblem() {
		return specificProblem;
	}
	public void setSpecificProblem(String specificProblem) {
		this.specificProblem = specificProblem;
	}
	public String getNeUID() {
		return neUID;
	}
	public void setNeUID(String neUID) {
		this.neUID = neUID;
	}
	public String getNeName() {
		return neName;
	}
	public void setNeName(String neName) {
		this.neName = neName;
	}
	public String getNeType() {
		return neType;
	}
	public void setNeType(String neType) {
		this.neType = neType;
	}
	public String getObjectUID() {
		return objectUID;
	}
	public void setObjectUID(String objectUID) {
		this.objectUID = objectUID;
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
	public String getLocationInfo() {
		return locationInfo;
	}
	public void setLocationInfo(String locationInfo) {
		this.locationInfo = locationInfo;
	}
	public String getAddInfo() {
		return addInfo;
	}
	public void setAddInfo(String addInfo) {
		this.addInfo = addInfo;
	}
	public String getrNeUID() {
		return rNeUID;
	}
	public void setrNeUID(String rNeUID) {
		this.rNeUID = rNeUID;
	}
	public String getrNeName() {
		return rNeName;
	}
	public void setrNeName(String rNeName) {
		this.rNeName = rNeName;
	}
	public String getrNeType() {
		return rNeType;
	}
	public void setrNeType(String rNeType) {
		this.rNeType = rNeType;
	}
	@Override
	public String toString() {
		return "NokiaLteAlarm [alarmSeq=" + alarmSeq + ", alarmTitle=" + alarmTitle + ", alarmStatus=" + alarmStatus
				+ ", alarmType=" + alarmType + ", origSeverity=" + origSeverity + ", eventTime=" + eventTime
				+ ", alaramId=" + alaramId + ", specificProblemID=" + specificProblemID + ", specificProblem="
				+ specificProblem + ", neUID=" + neUID + ", neName=" + neName + ", neType=" + neType + ", objectUID="
				+ objectUID + ", objectName=" + objectName + ", objectType=" + objectType + ", locationInfo="
				+ locationInfo + ", addInfo=" + addInfo + ", rNeUID=" + rNeUID + ", rNeName=" + rNeName + ", rNeType="
				+ rNeType + "]";
	}
	
	

}
