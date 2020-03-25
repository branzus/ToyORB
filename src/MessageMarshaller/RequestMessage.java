package MessageMarshaller;

import java.io.Serializable;

public class RequestMessage implements Message{

	
	public boolean getType = false;
	public String name;
	public String method;
	public Serializable[] args;
	
	public RequestMessage(String name, String method, Serializable[] args)
	{
		this.name = name;
		this.method = method;
		this.args = args;
	}
	
	public RequestMessage(boolean t) {
		getType = t;
	}
	
	
	public String toString() {
		return name + " " + method + " " + args.toString();
	}
	
}
