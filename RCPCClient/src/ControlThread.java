import java.net.Socket;

public class ControlThread extends Thread{
	Socket client;
	public ControlThread() {}
	public ControlThread(Socket client) {
		this.client = client;
	}
	public void run() {
		
	}
}
