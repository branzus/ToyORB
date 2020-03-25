import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;


public class ProxyGenerator {

	public static void generateFile(String interfaceName) {
		
		try {
		String clientName = interfaceName + "ClientProxy";
		System.out.println("../src/Proxys/"+interfaceName+".java");
		File f = new File(clientName +".java");
		
		FileWriter fw = new FileWriter(f);
		BufferedWriter buf = new BufferedWriter(fw);
		
		buf.write("package Proxys;import java.io.Serializable;import Commons.Address;import MessageMarshaller.Marshaller;import MessageMarshaller.ReplyMessage;import MessageMarshaller.RequestMessage;import RequestReply.Requestor;");
		buf.write("import interfaces." + interfaceName +";");
		buf.write("\npublic class "+clientName+ " implements " + interfaceName);
		buf.write(" {\n private String name; private Address address;\n");
		buf.write("public " + clientName + "(String n, Address a) { name = n; address = a; }\n");
		
		Class<?> c = Class.forName("interfaces."+interfaceName);
		Method[] methods = c.getDeclaredMethods();
		
		for (Method m : methods) {
		
			String retType = m.getReturnType().getSimpleName();
			buf.write("public " + retType + " " + m.getName() +"(");
	
			Class<?> parameters[] = m.getParameterTypes();
			int i = 0;
			for (Class<?> p : parameters) {
				buf.write(p.getSimpleName());
				buf.write(" arg"+i);
				
				if (i!= parameters.length - 1)
					buf.write(",");
				i++;
			}
			
			buf.write(") {\n");
			/*
			Serializable[] args = new Serializable[]{road};
			
			RequestMessage msg = new RequestMessage(name, "get_road_info", args);
			Requestor r = new Requestor();
			byte[] repl = r.deliver_and_wait_feedback(address, Marshaller.marshal(msg));
			ReplyMessage rmsg = (ReplyMessage)Marshaller.unmarshal(repl);
			return (String) rmsg.reply;*/
			
			buf.write("Serializable[] args = new Serializable[]{");
			i = 0;
			for (Class<?> p : parameters) {
				buf.write(" arg"+i);
				if (i!= parameters.length - 1)
					buf.write(",");
				i++;
			}
			buf.write("};\n");
			buf.write("RequestMessage msg = new RequestMessage(name, \"" + m.getName() +"\", args);\n");
			buf.write("Requestor r = new Requestor();\n");
			buf.write("byte[] repl = r.deliver_and_wait_feedback(address, Marshaller.marshal(msg));\n");
			buf.write("ReplyMessage rmsg = (ReplyMessage)Marshaller.unmarshal(repl);\n");
			
			if (retType.equals("float"))
				retType = "Float";
			else if (retType.equals("int")) 
				retType = "Integer";
			buf.write("return ("+ retType + ") rmsg.reply;\n");
			buf.write("}\n");
		}
		buf.write("}\n");
		buf.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	public static void main(String[] args) {
		/*try {
	//generateFile("InfoMarket");
			File f = new File("test");
			BufferedWriter buf = new BufferedWriter(new FileWriter(f));
			buf.write("tetstdsgds");
			buf.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}*/
		generateFile("MathApp");
	}

}
