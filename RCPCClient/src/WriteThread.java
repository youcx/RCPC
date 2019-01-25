import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.imageio.ImageIO;



class WriteThread extends Thread{
	Socket client;
	OutputStream ops;
	InputStream ips;
	public WriteThread(Socket client){
		this.client=client;
		try
		{
			this.ops=client.getOutputStream();
			this.ips=client.getInputStream();
		}catch(Exception e)
		{
			System.out.println("wrong");
		}
	}
	
	public void run(){
		while(true) {
			//��ͼ
			File file = screenShot();
	
	        //��ͼ��ȡ���ֽ���
	        FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//��ͼ��С
	        byte[] len = null;
			try {
				len = Tools.intToByte(fis.available());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	        //��װ��ͷ
	        byte[] header=new byte[8];
	        header[0]=2;
	        header[1]=2;
	        System.arraycopy(len, 0, header, 2, 4);
        
	        //����
		    byte[] body = null;
		    try {
				body = new byte[fis.available()];
				System.out.println(System.currentTimeMillis()+":"+fis.available());
				fis.read(body);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    
	        //����
	        try {
				ops.write(header);
				ops.write(body);
				 fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(System.currentTimeMillis()+"Send end!");
				break;
			}      	
		}			
	}
	/**
	 * ��ȡ��Ļ
	 * @return
	 */
	private File screenShot() {
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();  //Ҫ��ȡ�Ŀ��
        int height = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();  //Ҫ��ȡ�ĸ߶�
        //Robot
        Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //��ͼ����
        BufferedImage image = robot.createScreenCapture(new Rectangle(width,height));
        File file=new File("d:/123.png");
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            ImageIO.write (image, "png" , file);
        }catch(Exception e) {
        	e.printStackTrace();
        	System.out.println("��ͼʧ��");
        }
        return file;
	}
}