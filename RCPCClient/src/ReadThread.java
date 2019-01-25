import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

class ReadThread extends Thread
{
	Socket client;
	OutputStream ops;
	InputStream ips;
	Robot myRobot;//Robotʵ��������������
	ReadThread(Socket client)
	{
		this.client=client;
		try
		{
		this.ops=client.getOutputStream();
		this.ips=client.getInputStream();
		this.myRobot=new Robot();
		}catch(Exception e)
		{
			System.out.println("wrong");
		}
		
		
	}
	public void run()
	{
		try
		{
			while(true)
			{
				//��ȡ8�ֽڰ�ͷ
				byte[] header=new byte[8];
				ips.read(header,0,8);
				//1 7 ����ָ��
				if(header[0]==1&&header[1]==7)
				{
					byte[] data=new byte[9];
					ips.read(data,0,9);
					if(data[0]==2)
					{
						//�������
						byte[] xx=new byte[4];
						byte[] yy=new byte[4];
						System.arraycopy(data, 1, xx, 0, 4);
						System.arraycopy(data, 5, yy, 0, 4);
						int x=Tools.byteToInt(xx);
						int y=Tools.byteToInt(yy);
						myRobot.mouseMove(x, y);                // �ƶ���굽���꣨x,y����  
						myRobot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);     // ģ�ⰴ��������  
						myRobot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);   // ģ���ͷ������� 
					}else if(data[0]==3)
					{
						//�����Ҽ�
						byte[] xx=new byte[4];
						byte[] yy=new byte[4];
						System.arraycopy(data, 1, xx, 0, 4);
						System.arraycopy(data, 5, yy, 0, 4);
						int x=Tools.byteToInt(xx);
						int y=Tools.byteToInt(yy);
						myRobot.mouseMove(x, y);                // �ƶ���굽���꣨x,y����  
						myRobot.mousePress(KeyEvent.BUTTON3_DOWN_MASK);     // ģ�ⰴ������Ҽ�
						myRobot.mouseRelease(KeyEvent.BUTTON3_DOWN_MASK);   // ģ���ͷ�����Ҽ�
					
					}else if(data[0]==4)
					{
						//˫�����
						byte[] xx=new byte[4];
						byte[] yy=new byte[4];
						System.arraycopy(data, 1, xx, 0, 4);
						System.arraycopy(data, 5, yy, 0, 4);
						int x=Tools.byteToInt(xx);
						int y=Tools.byteToInt(yy);
						myRobot.mouseMove(x, y);                // �ƶ���굽���꣨x,y����  
						myRobot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);     // ģ�ⰴ��������  
						myRobot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);   // ģ���ͷ�������
						myRobot.mousePress(KeyEvent.BUTTON1_DOWN_MASK);     // ģ�ⰴ��������  
						myRobot.mouseRelease(KeyEvent.BUTTON1_DOWN_MASK);
					}else if(data[0]==5)
					{
						System.out.println(data[1]);
						
								myRobot.keyPress(data[1]);
								myRobot.keyRelease(data[1]);
						
						/*
						switch(data[1])
						{
						case 48:
							myRobot.keyPress(KeyEvent.VK_0);
							myRobot.keyRelease(KeyEvent.VK_0);
						case 49:
							myRobot.keyPress(KeyEvent.VK_1);
							myRobot.keyRelease(KeyEvent.VK_1);
							break;
						case 50:
							myRobot.keyPress(KeyEvent.VK_2);
							myRobot.keyRelease(KeyEvent.VK_2);
							break;
						case 51:
							myRobot.keyPress(KeyEvent.VK_3);
							myRobot.keyRelease(KeyEvent.VK_3);
							break;
						case 52:
							myRobot.keyPress(KeyEvent.VK_4);
							myRobot.keyRelease(KeyEvent.VK_4);
							break;
						case 53:
							myRobot.keyPress(KeyEvent.VK_5);
							myRobot.keyRelease(KeyEvent.VK_5);
							break;
						case 54:
							myRobot.keyPress(KeyEvent.VK_6);
							myRobot.keyRelease(KeyEvent.VK_6);
							break;
						case 55:
							myRobot.keyPress(KeyEvent.VK_7);
							myRobot.keyRelease(KeyEvent.VK_7);
							break;
						case 56:
							myRobot.keyPress(KeyEvent.VK_8);
							myRobot.keyRelease(KeyEvent.VK_8);
							break;
						case 57:
							myRobot.keyPress(KeyEvent.VK_9);
							myRobot.keyRelease(KeyEvent.VK_9);
							break;
						
							
						case 97:
							myRobot.keyPress(KeyEvent.VK_A);
							myRobot.keyRelease(KeyEvent.VK_A);
							break;
						case 98:
							myRobot.keyPress(KeyEvent.VK_B);
							myRobot.keyRelease(KeyEvent.VK_B);
							break;
						case 99:
							myRobot.keyPress(KeyEvent.VK_C);
							myRobot.keyRelease(KeyEvent.VK_C);
							break;
						default:
							break;
						}*/
					}
				}
			}
		}catch(Exception e)
		{
			System.out.println("read err!!");
		}
	}
}
