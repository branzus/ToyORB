package Commons;

public class ConcreteAddress implements Address {

	private String dest;
	private int port;
	
	public ConcreteAddress(String dest, int port) {
		this.dest = dest;
		this.port = port;
	}
	
	@Override
	public String dest() {
		return dest;
	}

	@Override
	public int port() {
		return port;
	}

}
