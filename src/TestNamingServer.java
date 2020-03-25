import Commons.Address;
import Commons.ConcreteAddress;
import RequestReply.Requestor;




public class TestNamingServer {

	
	public static void main(String args[]) {
	
		Address a = new ConcreteAddress("localhost", 1234);
		Requestor r = new Requestor();
		byte [] response = r.deliver_and_wait_feedback(a, "register:sugus:adresa:8893".getBytes());
		System.out.println("Am primit: " + new String(response));
		
		response = r.deliver_and_wait_feedback(a, "query:sugus".getBytes());
		System.out.println("Am primit: " + new String(response));
		
	}
}
