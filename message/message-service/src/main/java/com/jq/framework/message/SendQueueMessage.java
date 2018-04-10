package com.jq.framework.message;

import java.time.Duration;

import com.microsoft.azure.servicebus.Message;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;

public class SendQueueMessage {
	
	
	public static void main(String[] args) throws InterruptedException, ServiceBusException {
		  //命名空间连接字符串
	    String connectionString = "Endpoint=sb://test123.servicebus.chinacloudapi.cn/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=/tmCk7x0oYviR5OnYB2Ofqyb9+340h9XKMBoHbUdNiY=";

	    // Create a QueueClient instance for receiving using the connection string builder
	    // We set the receive mode to "PeekLock", meaning the message is delivered
	    // under a lock and must be acknowledged ("completed") to be removed from the queue

	//yuqueue为对应队列的名称
	    QueueClient sendClient = new QueueClient(new ConnectionStringBuilder(connectionString, "test"), ReceiveMode.PEEKLOCK);
	    String body="消息总线测试";
	    Message msg=new Message();
	    msg.setBody(body.getBytes());
	    msg.setLabel("Scientist");
	    msg.setMessageId("1");
	    msg.setTimeToLive(Duration.ofMinutes(2));
	    sendClient.send(msg);
	}

	
}
