import java.io.*;
import java.net.*;
public class myFile {
	public static void main(String[] args)throws IOException
	{
		InetAddress address = InetAddress.getLocalHost(); 
		System.out.println(address.getHostAddress());
		ServerSocket server=new ServerSocket(12345);
		Socket client=server.accept();
		System.out.println("connect successfully");
		DataOutputStream send;
		try{
		FileInputStream getpic=new FileInputStream("d:/123.jpg");
		byte data[]=new byte[getpic.available()];
		System.out.println(getpic.available());
		getpic.read(data);
		send=new DataOutputStream(client.getOutputStream());
		send.write(data);
		send.flush();
		System.out.println("finish");
		getpic.close();
		}
		catch(Exception e) 
		{
			System.out.println("wrong");
		}
		
	}
}
