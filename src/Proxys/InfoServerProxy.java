package Proxys;


import implementations.InfoMarketImpl;

import java.io.Serializable;

import MessageMarshaller.Marshaller;
import MessageMarshaller.ReplyMessage;
import MessageMarshaller.RequestMessage;
import RequestReply.ByteStreamTransformer;

public class InfoServerProxy implements ByteStreamTransformer{
	
	public byte[] transform(byte[] in) {
		RequestMessage req = (RequestMessage)Marshaller.unmarshal(in);
		Serializable ret = null;
		
		System.out.println(req.method);
		System.out.println(req);
		if (req.method.equals("get_road_info")){
			ret = new InfoMarketImpl().get_road_info((Integer)req.args[0]);
		}
		else if(req.method.equals("get_temp")){
			ret = new InfoMarketImpl().get_temp((String)req.args[0]);
		}
		
		//System.out.println(ret);
		ReplyMessage rep = new ReplyMessage(ret);
		return Marshaller.marshal(rep);
		
	}
	
	
}
