package Proxys;import java.io.Serializable;import Commons.Address;import MessageMarshaller.Marshaller;import MessageMarshaller.ReplyMessage;import MessageMarshaller.RequestMessage;import RequestReply.Requestor;import interfaces.InfoMarket;
public class InfoMarketClientProxy implements InfoMarket {
 private String name; private Address address;
public InfoMarketClientProxy(String n, Address a) { name = n; address = a; }
public String get_road_info(int arg0) {
Serializable[] args = new Serializable[]{ arg0};
RequestMessage msg = new RequestMessage(name, "get_road_info", args);
Requestor r = new Requestor();
byte[] repl = r.deliver_and_wait_feedback(address, Marshaller.marshal(msg));
ReplyMessage rmsg = (ReplyMessage)Marshaller.unmarshal(repl);
return (String) rmsg.reply;
}
public float get_temp(String arg0) {
Serializable[] args = new Serializable[]{ arg0};
RequestMessage msg = new RequestMessage(name, "get_temp", args);
Requestor r = new Requestor();
byte[] repl = r.deliver_and_wait_feedback(address, Marshaller.marshal(msg));
ReplyMessage rmsg = (ReplyMessage)Marshaller.unmarshal(repl);
return (Float) rmsg.reply;
}
}
