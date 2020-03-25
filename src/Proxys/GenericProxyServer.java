package Proxys;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;

import Commons.Address;
import Commons.ConcreteAddress;
import MessageMarshaller.Marshaller;
import MessageMarshaller.ReplyMessage;
import MessageMarshaller.RequestMessage;
import RequestReply.ByteStreamTransformer;
import RequestReply.Replyer;

public class GenericProxyServer implements ByteStreamTransformer {

	//private HashMap<String,Object> objects = new HashMap<String, Object>();
	private String name;
	private Object object;
	Replyer replyer;
	private static int port = 0;
	//private static GenericProxyServer srv;
	
	/*public static GenericProxyServer getInstance() {
		if(srv == null)
			srv = new GenericProxyServer();
		return srv;
	}*/
	public GenericProxyServer(String n, Object o) {
		name = n;
		object = o;
		Address a = new ConcreteAddress("localhost", port);
		final GenericProxyServer tis = this;
		replyer = new Replyer(a);
		Thread t = new Thread(new Runnable() {
			public void run() {
				while(true) {
					replyer.receive_transform_and_send_feedback(tis);
				}
			}
		});
		t.start();
	}
	
	
	public void startServer() {
		//replyer.receive_transform_and_send_feedback(this);
	}
	
	public Address getAddress() {
		if (replyer != null)
			return replyer.getAddress();
		return null;
	}
	
	@Override
	public byte[] transform(byte[] in) {
		
		RequestMessage rqm = (RequestMessage)Marshaller.unmarshal(in);
		ReplyMessage rpm = null;
		if (rqm.getType){
			rpm = new ReplyMessage(this.getType());	
		}
		else {
			rpm = new ReplyMessage(this.call(rqm.name, rqm.method, rqm.args));
		}
		return Marshaller.marshal(rpm);
	}
	
	/*
	public void register(String n, Object o) {
		objects.put(n, o);
	}
	*/
	
	private String getType(){
		try {
		return object.getClass().getInterfaces()[0].getSimpleName();
				}
		catch(Exception e) {
			System.out.println("fmm");
		}
		return null;
	}
	
	
	private Serializable call(String name, String method, Serializable[] args) {
		
		Serializable ret = null;
		Object o = object;
		if (o == null) {
			System.out.println("Object does not exist.");
			return null;
		}
		
		//get parameters types in order to be able to get the method
		Class<?>[] types = new Class[args.length];
		for(int i = 0; i < args.length; i++) {
			try {
				//try getting the base class type
				types[i] = (Class<?>) args[i].getClass().getDeclaredField("TYPE").get(null);
			}
			catch (Exception e) {
				//already base class type
				types[i] = args[i].getClass();
			}
		}
		
		//get the method with name method taking the same parameter types
		Method m = null;
		try {
			m = o.getClass().getMethod(method, types);
			ret = (Serializable)m.invoke(o, (Object[])args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return ret;
	}

	
}
