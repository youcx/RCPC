package filedemo1;
import java.io.*;

public class filedomo {
	public static void main(String[] args)throws IOException
	{
		FileInputStream fis=new FileInputStream("g:/1.png");
		FileOutputStream fos=new FileOutputStream("g:/3.png");
		byte[] b=new byte[1024];
		int temp=0;
		int len=0;
		while((temp=fis.read(b))!=-1)
		{
			//b[len]=(byte)temp;
			fos.write(b);
			len++;
		}
		fis.close();
		fos.close();
		
	}

}
