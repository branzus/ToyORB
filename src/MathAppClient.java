import interfaces.InfoMarket;
import interfaces.MathApp;
import toyOrb.ToyORB;


public class MathAppClient {

	public static void main(String[] args) {
	MathApp i = (MathApp)ToyORB.getObjectReference("mate");
	System.out.println("Result: " + i.add(2,133));
	}
}
