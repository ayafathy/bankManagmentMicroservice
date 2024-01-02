package com.bank.accountmanagement.service;

import org.springframework.stereotype.Service;

import com.bank.accountmanagement.model.EmailModel;


import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Service
public class RabbitMQSender {
	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.routing.key}")
	private String routingkey;

	public void sendEmail(EmailModel email) {
		
		rabbitTemplate.convertAndSend(exchange, routingkey, email);
		log.info("message pushed to the Queue :" + email);

	}
}
