package MessageMarshaller;

import java.io.Serializable;

public class ReplyMessage implements Message {

	
	public Serializable reply;
	
	public ReplyMessage(Serializable reply) {
		this.reply = reply;
	}
}
