package com.jq.framework.message;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;





import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;

import com.microsoft.azure.servicebus.ExceptionPhase;
import com.microsoft.azure.servicebus.IMessage;
import com.microsoft.azure.servicebus.IMessageHandler;
import com.microsoft.azure.servicebus.MessageHandlerOptions;
import com.microsoft.azure.servicebus.QueueClient;
import com.microsoft.azure.servicebus.ReceiveMode;
import com.microsoft.azure.servicebus.primitives.ConnectionStringBuilder;
import com.microsoft.azure.servicebus.primitives.ServiceBusException;
import com.microsoft.azure.servicebus.primitives.StringUtil;


public class ReceiveQueueMessage {
	public static void main(String[] args) throws ServiceBusException, InterruptedException {

        //命名空间连接字符串
        String connectionString = "Endpoint=sb://test123.servicebus.chinacloudapi.cn/;SharedAccessKeyName=RootManageSharedAccessKey;SharedAccessKey=/tmCk7x0oYviR5OnYB2Ofqyb9+340h9XKMBoHbUdNiY=";

        // Create a QueueClient instance for receiving using the connection string builder
        // We set the receive mode to "PeekLock", meaning the message is delivered
        // under a lock and must be acknowledged ("completed") to be removed from the queue

//yuqueue为对应队列的名称
        QueueClient receiveClient = new QueueClient(new ConnectionStringBuilder(connectionString, "test"), ReceiveMode.PEEKLOCK);



        receiveClient.registerMessageHandler(new IMessageHandler() {
                                                 // callback invoked when the message handler loop has obtained a message
                                                 public CompletableFuture<Void> onMessageAsync(IMessage message) {
                                                     // receives message is passed to callback
                                                     if (true) {

                                                         byte[] body = message.getBody();
                                                         System.out
																.println("__________________________"+new String(body));

                                                        System.out.printf(
                                                                 "\n\t\t\t\tMessage received: \n\t\t\t\t\t\tMessageId = %s, \n\t\t\t\t\t\tSequenceNumber = %s, \n\t\t\t\t\t\tEnqueuedTimeUtc = %s," +
                                                                         "\n\t\t\t\t\t\tExpiresAtUtc = %s, \n\t\t\t\t\t\tContentType = \"%s\", \n\t\t\t\t\t\tContent: [ firstName = %s, name = %s ]\n",
                                                                 message.getMessageId(),
                                                                 message.getSequenceNumber(),
                                                                 message.getEnqueuedTimeUtc(),
                                                                 message.getExpiresAtUtc(),
                                                                 message.getContentType(),
                                                                 new String(body),
                                                                 "");
                                                     }
                                                     return CompletableFuture.completedFuture(null);
                                                 }

                                                 // callback invoked when the message handler has an exception to report
                                                 public void notifyException(Throwable throwable, ExceptionPhase exceptionPhase) {
                                                     System.out.printf(exceptionPhase + "-" + throwable.getMessage());
                                                 }
                                             },
                // 1 concurrent call, messages are auto-completed, auto-renew duration
                new MessageHandlerOptions(1, true, Duration.ofMinutes(1)));
    }
	
}
