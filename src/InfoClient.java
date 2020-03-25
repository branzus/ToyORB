
import interfaces.InfoMarket;


import toyOrb.ToyORB;



public class InfoClient {

	public static void main(String args[]) {
		/*Requestor r = new Requestor();
		Address a = new ConcreteAddress("localhost", 5000);
/*
		String msg = "register:nume:adnume:5469";
		byte b[] = msg.getBytes();

		
		System.out.println("Sending data: " + new String(b));
		byte[] resp = r.deliver_and_wait_feedback(a, b);
		System.out.println(new String(resp));
		
		msg = "query:nume";
		b = msg.getBytes();

		
		System.out.println("Sending data: " + new String(b));
		resp = r.deliver_and_wait_feedback(a, b);
		System.out.println(new String(resp));

		Serializable[] arg = new Serializable[3];
		arg[0] = "bucu";
		Marshaller marsh = new Marshaller();
		RequestMessage msg = new RequestMessage("petrica", "get_temp",arg);
		System.out.println(msg);
		byte[] resp = r.deliver_and_wait_feedback(a, marsh.marshal(msg));
		
		//System.out.println(new String(resp));
		ReplyMessage rep = (ReplyMessage)marsh.unmarshal(resp);
		System.out.println(rep.reply);
		
		InfoClientProxy ip = new InfoClientProxy("petrica", a);
		System.out.println(ip.get_road_info(1));
		System.out.println(ip.get_temp("bucu"));*/
		InfoMarket i = (InfoMarket)ToyORB.getObjectReference("infomarket");
		System.out.println(i.get_temp("bucu"));
		
	}
}
