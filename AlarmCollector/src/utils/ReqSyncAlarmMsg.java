package utils;

import java.io.DataOutputStream;
import java.util.Date;

public class ReqSyncAlarmMsg  implements Runnable {
	
	private DataOutputStream dos;
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}
	private long alarmSeq;
	public void setAlarmSeq(long alarmSeq) {
		this.alarmSeq = alarmSeq;
	}
	
	private long reqId;
	public void setReqId(long reqId) {
		this.reqId = reqId;
	}

	public ReqSyncAlarmMsg(DataOutputStream dos, long reqId, long alarmSeq ) {
		super();
		this.dos = dos;
		this.alarmSeq = alarmSeq;
		this.reqId = reqId;
	}



	@Override
	public void run() {
		long reqId = 1;
		try{
			while(true){
				dos.writeShort(65535);
			    dos.write(3);
			    long t = new Date().getTime()/1000;
			    dos.writeInt(Integer.parseInt(String.valueOf(t)));
			    dos.writeShort(("reqSyncAlarmMsg;reqId="+reqId+";alarmSeq="+alarmSeq).length());
			    dos.writeBytes(("reqSyncAlarmMsg;reqId="+reqId+";alarmSeq="+alarmSeq));
			    dos.flush();
			    reqId++;
			    try {
					Thread.sleep(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}
