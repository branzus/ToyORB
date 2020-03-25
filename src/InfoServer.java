import implementations.InfoMarketImpl;
import interfaces.InfoMarket;
import toyOrb.ToyORB;
import Commons.Address;
import Commons.ConcreteAddress;
import Proxys.InfoServerProxy;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;



public class InfoServer {

	
	public static void main(String args[]) {
		/*Address a = new ConcreteAddress("serveraddress", 5000);
		Replyer r = new Replyer(a);
		ByteStreamTransformer t = new InfoServerProxy();
		System.out.println("Starting server");
		while(true){
		r.receive_transform_and_send_feedback(t);
		}*/
		InfoMarket inf = new InfoMarketImpl();
		ToyORB.register("infomarket", inf);
		//System.out.println("exitting");
			
	}
}
