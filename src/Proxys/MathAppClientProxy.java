package Proxys;import java.io.Serializable;import Commons.Address;import MessageMarshaller.Marshaller;import MessageMarshaller.ReplyMessage;import MessageMarshaller.RequestMessage;import RequestReply.Requestor;import interfaces.MathApp;
public class MathAppClientProxy implements MathApp {
 private String name; private Address address;
public MathAppClientProxy(String n, Address a) { name = n; address = a; }
public int add(int arg0,int arg1) {
Serializable[] args = new Serializable[]{ arg0, arg1};
RequestMessage msg = new RequestMessage(name, "add", args);
Requestor r = new Requestor();
byte[] repl = r.deliver_and_wait_feedback(address, Marshaller.marshal(msg));
ReplyMessage rmsg = (ReplyMessage)Marshaller.unmarshal(repl);
return (Integer) rmsg.reply;
}
}
