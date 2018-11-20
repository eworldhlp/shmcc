package job;

import utils.SocketClient;

public class NokiaLteAlarmCollectJob {
	
	public static void main(String[] args) {
		//OMC3
		SocketClient sc1 = new SocketClient("10.XXX.XXX.XXX", 31232, "XXX", "XXX");
        new Thread(sc1).start();
        //OMC8
        SocketClient sc2 = new SocketClient("10.XXX.XXX.XXX", 31232, "XXX", "XXX");
        new Thread(sc2).start();
        //OMC6
        SocketClient sc3 = new SocketClient("10.XXX.XXX.XXX", 31232, "XXX", "XXX");
        new Thread(sc3).start();
        //OMC7
        SocketClient sc4 = new SocketClient("10.XXX.XXX.XXX", 31232, "XXX", "XXX");
        new Thread(sc4).start();
	}

}
