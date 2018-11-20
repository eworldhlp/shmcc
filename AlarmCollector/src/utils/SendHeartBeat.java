package utils;

import java.io.DataOutputStream;
import java.util.Date;

public class SendHeartBeat implements Runnable {
	
	private DataOutputStream dos;
	public void setDos(DataOutputStream dos) {
		this.dos = dos;
	}

	public SendHeartBeat(DataOutputStream dos) {
		super();
		this.dos = dos;
	}

	@Override
	public void run() {
		long heatBeatSeqId = 1;
		try{
			while(true){
				dos.writeShort(65535);
			    dos.write(8);
			    long t = new Date().getTime()/1000;
			    dos.writeInt(Integer.parseInt(String.valueOf(t)));
			    dos.writeShort(("reqHeartBeat;reqId="+heatBeatSeqId).length());
			    dos.writeBytes(("reqHeartBeat;reqId="+heatBeatSeqId));
			    dos.flush();
			    heatBeatSeqId++;
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
