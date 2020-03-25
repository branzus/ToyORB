
package RequestReply;

import Commons.Address;

import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;


public class Requestor
{

	private Socket s;
	private OutputStream oStr;
	private InputStream iStr;
	//private String myName;	
	public Requestor(/*String theName*/) { 
		//myName = theName; 
	}


	public byte[] deliver_and_wait_feedback(Address theDest, byte[] data)
	{

		byte[] buffer = null;
		int val;
		try
		{
			
			//create socket to the address we want to deliver(the connection is made here)
			s = new Socket(theDest.dest(), theDest.port());
			
			System.out.println("Requestor: Socket"+s);
			
			//we got the connection established, send data
			oStr = s.getOutputStream();
			//first 4 bytes are the length, rest are the message
			val = data.length;
			byte len[] = ByteBuffer.allocate(4).putInt(val).array();
			oStr.write(len);
			oStr.write(data);
			oStr.flush();
			
			//get the reply by reading from the read part of the socket
			iStr = s.getInputStream();
			//read 4 bytes(the length)
			len = new byte[4];
			iStr.read(len);
			val = ByteBuffer.wrap(len).getInt();
			
			//read length bytes(the actual message)
			buffer = new byte[val];
			iStr.read(buffer);
			iStr.close();
			oStr.close();
			s.close();
			
			}
		catch (IOException e) { 
                       System.out.println("IOException in deliver_and_wait_feedback"); }
		return buffer;
	}

}

