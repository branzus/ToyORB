package namingServer;

import Commons.Address;
import Commons.ConcreteAddress;
import RequestReply.ByteStreamTransformer;

public class NamingServerTransformer implements ByteStreamTransformer {

	@Override
	public byte[] transform(byte[] in) {
		
		String data = new String(in);
		System.out.println("Am primit: " + data);
		
		//formatul: register:numeserver:adresadest:port
		//sau query:numeserver
		String ret = null;
		String operation = data.substring(0, data.indexOf(":"));
		data = data.substring(data.indexOf(":")+1);
		if (operation.equalsIgnoreCase("register")) {
			String name = data.substring(0, data.indexOf(":"));
			data = data.substring(data.indexOf(":")+1);
			String addressname = data.substring(0, data.indexOf(":"));
			data = data.substring(data.indexOf(":")+1);
			String sport = data;
			System.out.println(sport);
			int port = Integer.parseInt(sport);
			/*System.out.println(operation);
			System.out.println(name);
			System.out.println(adname);
			System.out.println(sport);
			System.out.println(port);
			*/
			//inregistreaza serverul conform la ce primeste
			NamingServer.register(name, new ConcreteAddress(addressname, port));
			
			//trimite inapoi "ok"
			ret = "ok";
		}
		
		else if (operation.equalsIgnoreCase("query")) {
			String name = data;

			//ia adresa
			Address a = NamingServer.getAddress(name);
			if (a != null)
			
				ret = a.dest() + ":" + a.port();
			else ret = "0"; //0 la eroare
		}
		
		//returneaza raspunsul(ok, 0, sau adresa)
		byte[] b = ret.getBytes();
		
		return b;
		
	}

}
