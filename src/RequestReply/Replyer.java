package RequestReply;

import Commons.Address;
import Commons.ConcreteAddress;

import java.net.*;
import java.nio.ByteBuffer;
import java.io.*;

public class Replyer
{
	private ServerSocket srvS;
	private Socket s;
	private InputStream iStr;
	private OutputStream oStr;
	//private String myName;
        private Address myAddr;
	
	public Replyer(Address theAddr) {
				//        myName = theName; 
              myAddr=theAddr;
              try {
              	srvS = new ServerSocket(myAddr.port(), 1000);
              	System.out.println("Replyer Serversocket:"+srvS);
	      } catch (Exception e) { 
                 System.out.println("Error opening server socket");
		}
	}


	public void receive_transform_and_send_feedback(ByteStreamTransformer t)
	{
		int val;
		byte buffer[] = null;
		try
		{
			//accept the connection(blocking until a connection is made)
			s = srvS.accept();
			System.out.println("Replyer accept: Socket"+s);
			
			//get input stream
			iStr = s.getInputStream();
			
			//first 4 bytes are the message length;
			byte len[] = new byte[4];
			iStr.read(len);
			val = ByteBuffer.wrap(len).getInt(); //make an int from those bytes
			
			//read the actual message
			buffer = new byte[val];
			iStr.read(buffer);
	
			//send the message to the ByteStreamTransformer for processing
			byte[] data = t.transform(buffer);

			//get output stream
			oStr = s.getOutputStream();
			
			//first 4 byte make the length of the message
			len = ByteBuffer.allocate(4).putInt(data.length).array();
			//write length
			oStr.write(len);
			//write data
			oStr.write(data);
			oStr.flush();
			
			//close
			oStr.close();
			iStr.close();
			s.close();
			
		}
		catch (IOException e) { 
                      System.out.println("IOException in receive_transform_and_feedback"); }
		
	}

	protected void finalize() throws Throwable {
		super.finalize();
		srvS.close();
	}
	
	public Address getAddress() {
		return new ConcreteAddress(srvS.getInetAddress().getHostAddress(), srvS.getLocalPort());
	}
}

