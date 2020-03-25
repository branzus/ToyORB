import toyOrb.ToyORB;
import implementations.MathAppImpl;
import interfaces.MathApp;


public class MathAppServer {
	
	
	public static void main(String[] args) {
		MathApp m = new MathAppImpl();
		ToyORB.register("mate", m);
	}

}
