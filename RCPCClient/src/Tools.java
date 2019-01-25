
public class Tools {
	public static byte[] intToByte(int n)
	{
		byte[] data=new byte[4];
		data[3]=(byte) (0xff&n);
		data[2]=(byte) ((n&0xff00)>>8);
		data[1]=(byte) ((n&0xff0000)>>16);
		data[0]=(byte) ((n&0xff000000)>>24);
		return data;
	}
	public static int byteToInt(byte[]ary)
    {
        int value;
        value = (int) ((ary[3]&0xFF)
                | ((ary[2]<<8) & 0xFF00)
                | ((ary[1]<<16)& 0xFF0000)
                | ((ary[0]<<24) & 0xFF000000));
        return value;
    }
}
