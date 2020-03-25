package namingServer;
import java.util.HashMap;

import Commons.Address;
import Commons.ConcreteAddress;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;


public class NamingServer {


	private static HashMap<String, Address> servers = new HashMap<String, Address>();
	
	public synchronized static void register(String name, Address a) {
		System.out.println("Registering "+ name + " at "+a.dest()+ ":"+a.port());
		servers.put(name, a);
	}
	
	public static Address getAddress(String name) {
		System.out.println("Querying for "+name);
		return servers.get(name);
	}
	
	public static void main(String[] args) {
		
		Replyer r = new Replyer(new ConcreteAddress("NamingServer", 1234)); /*adresa hardcodata (numele(destinatina) nu conteaza pentru replyer, o sa se faca un server socket
		 																	pe calculatorul curent, deci cu ip-ul curent*/
		System.out.println("Naming server started.");
		
		
		ByteStreamTransformer nameTransform = new NamingServerTransformer();
				
		//accept connections forever
		while(true) {
			r.receive_transform_and_send_feedback(nameTransform);		
		}

	}

}
