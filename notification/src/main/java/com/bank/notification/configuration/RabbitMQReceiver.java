package com.bank.notification.configuration;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.stereotype.Component;

import com.bank.notification.model.EmailModel;

import lombok.extern.slf4j.Slf4j;
@Component
@Slf4j
public class RabbitMQReceiver {
	@RabbitListener(queues = "${rabbitmq.queue.name}")
	public void recievedMessage(EmailModel rabbitmq) {
	    log.info("message recieved successfully "+rabbitmq);
	    log.info("to do send email to  "+ rabbitmq.email() +" with body :" +rabbitmq.Body());	
	}
}
