import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class Main {
	public static void main(String[] args) {
		ServerSocket server = null;
		try {
			server = new ServerSocket(12346);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Socket client = null;
		//¼àÌý
		while(true) {
			try {
				client = server.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(client!=null) {
				System.out.println("connect successfully");
				WriteThread writethread=new WriteThread(client);
				writethread.start();
				ReadThread readthread=new ReadThread(client);
				readthread.start();
			}

		}

		
	}
}
