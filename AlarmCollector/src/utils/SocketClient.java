package utils;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kafka.javaapi.producer.Producer;
import service.impl.NokiaLteOrigAlarmCollectImpl;

public class SocketClient implements Runnable{
	
	static Logger logger = LoggerFactory.getLogger(SocketClient.class.getSimpleName());
	static Producer producer = null;
	
	private static long heartBeatSeqIdAcked ;   //心跳响应id
	private static long alarmSeqId; //告警序列号id
	private static long reqSyncAlarmSeqId=1;  //告警同步请求seqId

	private String address;
	private int port;
	private String username;
	private String password;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

	public SocketClient(String address, int port, String username, String password) {
		super();
		this.address = address;
		this.port = port;
		this.username = username;
		this.password = password;
		producer = KafkaProducerFactory.createProducer();
	}

	public void client() {
        Socket client = null;
        BufferedReader is = null;
        DataOutputStream dos = null;
        DataInputStream dis = null;
        try {
            client = new Socket(address, port);
            dos = new DataOutputStream(client.getOutputStream());
            dos.writeShort(65535);
            dos.write(1);
            long timestamp = new Date().getTime()/1000;
            dos.writeInt(Integer.parseInt(String.valueOf(timestamp)));
            dos.writeShort(("reqLoginAlarm;user="+username+";key="+password+";type=msg").length());
            dos.writeBytes("reqLoginAlarm;user="+username+";key="+password+";type=msg");
            dos.flush();
            
            dis = new DataInputStream(client.getInputStream());
            Short readShort ;
			while((readShort = dis.readShort())!=null){
            	if(readShort == -1){
            		int messageType = dis.read();
            		if(messageType==2){
            			int time = dis.readInt();
                		int messageLength = dis.readShort();
                		byte[] bytes = new byte[messageLength];
                		for (int i = 0; i < bytes.length; i++) {
    						bytes[i] =  dis.readByte();
    					}
                		String messageBody = new String(bytes);
                		if(messageBody.equals("ackLoginAlarm;result=succ;resDesc=null")){
                			logger.info(address+"认证成功！");
                			SendHeartBeat sendHeartBeat = new SendHeartBeat(dos);
                			new Thread(sendHeartBeat).start();
                			
                		} else{
                			client.close();
                			break;
                		}
            		}
            		if(messageType==9){
            			int time = dis.readInt();
            			int messageLength = dis.readShort();
            			byte[] bytes = new byte[messageLength];
                		for (int i = 0; i < bytes.length; i++) {
    						bytes[i] =  dis.readByte();
    					}
                		String messageBody = new String(bytes);
                		try{        //心跳响应丢失超过3次释放连接
                			long currentheartBeatSeqIdAcked = Long.parseLong(messageBody.substring(messageBody.indexOf("reqId=")+6));
                			if(currentheartBeatSeqIdAcked-heartBeatSeqIdAcked>3){
                				logger.error("心跳超时，释放TCP！");
                				client.close();
                			} else{
                				heartBeatSeqIdAcked = currentheartBeatSeqIdAcked;
                			}
                		} catch(Exception e){
                			
                		}
                		logger.info("收到"+address+"心跳响应！"+messageBody);
            		} 
            		if(messageType==0){
            			int time = dis.readInt();
            			int messageLength = dis.readShort();
            			byte[] bytes = new byte[messageLength];
                		for (int i = 0; i < bytes.length; i++) {
    						bytes[i] =  dis.readByte();
    					}
                		String messageBody = new String(bytes);
                		logger.info("开始接收"+address+"实时告警......");
                		ExecutorService service = Executors.newFixedThreadPool(1);
                		NokiaLteOrigAlarmCollectImpl nokiaLteOrigAlarmCollectImpl = new NokiaLteOrigAlarmCollectImpl(messageBody, producer);
                		Future<Long> task = service.submit(nokiaLteOrigAlarmCollectImpl);
                		service.shutdown();
                		try {
							long currentAlarmSeq = task.get();
							if(Math.abs(currentAlarmSeq-alarmSeqId)>1&&Math.abs(currentAlarmSeq-alarmSeqId)<1000){   //发现缺失告警，申请消息同步
								new Thread(new ReqSyncAlarmMsg(dos,reqSyncAlarmSeqId,alarmSeqId+1)).start();
							} else{
								if(currentAlarmSeq>alarmSeqId)  //正常接收到的序列号肯定大于上一次序列号，更新已接收序列号；如果是同步后接收的补的告警，序列号小于当前序列号，因此不更新当前序列号
									alarmSeqId = currentAlarmSeq;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
            		}        
            		if(messageType==4){   //ackSyncAlarmMsg
            			int time = dis.readInt();
            			int messageLength = dis.readShort();
            			byte[] bytes = new byte[messageLength];
                		for (int i = 0; i < bytes.length; i++) {
    						bytes[i] =  dis.readByte();
    					}
                		String messageBody = new String(bytes);
                		try{
                			String tmp[] = messageBody.split(";");
                			boolean ackResult = false;
                			if(tmp[2].endsWith("succ")){
                				ackResult = true;
                			}
                			reqSyncAlarmSeqId = Long.parseLong(tmp[1].substring(tmp[1].indexOf("reqId=")+6));
                			reqSyncAlarmSeqId++;
                			if(ackResult){   //同步成功
                				
                			}
                		} catch (Exception e) {
						}
            		}
            	}
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (dos != null) {
                    dos.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                logger.error("Close the IO session error: ", e);
            }
        }
 
    }


	@Override
	public void run() {
		this.client();
	}
}
