package MessageMarshaller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Marshaller
{
	public static byte[] marshal(Message theMsg)
	{
		/*
		String m = "  " + theMsg.sender + ":" + theMsg.data;
		byte b[] = new byte[m.length()];
		b = m.getBytes();
		b[0] = (byte)m.length();
		return b;
		*/
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		ObjectOutputStream obj = null;
		try {
			obj = new ObjectOutputStream(bytes);
			obj.writeObject(theMsg);
			obj.flush();
			obj.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return bytes.toByteArray();
	}
	public static Message unmarshal(byte[] byteArray)
	{
		/*
		String msg = new String(byteArray);
		String sender = msg.substring(1, msg.indexOf(":"));
		String m = msg.substring(msg.indexOf(":")+1, msg.length()-1);
		return new Message(sender, m);
		*/
		ByteArrayInputStream bytes = new ByteArrayInputStream(byteArray);
		ObjectInputStream obj = null;
		Message msg = null;
		try {
			obj = new ObjectInputStream(bytes);
			msg = (Message)obj.readObject();
			obj.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return msg;
	}

}





