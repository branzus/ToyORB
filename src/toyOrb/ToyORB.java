package toyOrb;

import java.lang.reflect.Constructor;

import Commons.Address;
import Commons.ConcreteAddress;
import MessageMarshaller.Marshaller;
import MessageMarshaller.ReplyMessage;
import MessageMarshaller.RequestMessage;
import Proxys.GenericProxyServer;
import Proxys.InfoMarketClientProxy;
import RequestReply.Replyer;
import RequestReply.Requestor;

public class ToyORB {

	private static String NamingServerAddress = "localhost";
	private static int NamingServerPort = 1234;
	private static Address NamingServer = new ConcreteAddress(NamingServerAddress, NamingServerPort);
	
	public static void register(String name, Object o) {
		
		Requestor r = new Requestor();
		GenericProxyServer proxy = new GenericProxyServer(name, o);
		
		Address a = proxy.getAddress();
		
		//String msg = "register:nume:adnume:5469";
		String msg = "register:" + name + ":" +a.dest() + ":" + a.port();
		byte[] answer = r.deliver_and_wait_feedback(NamingServer, msg.getBytes());
		
		if (!(new String(answer).equals("ok"))) 
			System.out.println("Couldn't register server.");
		proxy.startServer();
	}
	
	public static Object getObjectReference(String name) {
		Requestor r = new Requestor();
		//String msg = "query:nume";
		String msg = "query:" + name;
		
		byte[] answer = r.deliver_and_wait_feedback(NamingServer, msg.getBytes());
		String sanswer = new String(answer);
		if (sanswer.equals("0")) {
			System.out.println("Couldn't retrieve server for "+ name);
			return null;
		}
		String address = sanswer.substring(0, sanswer.indexOf(":"));
		int port = Integer.parseInt(sanswer.substring(sanswer.indexOf(":")+1));
		Address a = new ConcreteAddress(address, port);
		
		RequestMessage rqm =  new RequestMessage(true);
		byte[] repl = r.deliver_and_wait_feedback(a, Marshaller.marshal(rqm));
		ReplyMessage rmsg = (ReplyMessage)Marshaller.unmarshal(repl);
		String int_name = (String) rmsg.reply;
	
		String class_name = "Proxys." + int_name + "ClientProxy";
		//System.out.println(class_name);
		
		try {
			Constructor<?> c = Class.forName(class_name).getConstructor(
					new Class[] {String.class, Address.class});
			return c.newInstance(name, a);
		}
		catch (ClassNotFoundException e) {
			System.out.println("Class could not be found");
			e.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
