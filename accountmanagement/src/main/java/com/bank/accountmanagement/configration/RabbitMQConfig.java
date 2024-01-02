package com.bank.accountmanagement.configration;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
	@Value("${rabbitmq.queue.name}")
	private String queueName;

	@Value("${rabbitmq.exchange.name}")
	private String exchange;

	@Value("${rabbitmq.deadLetterExchange.name}")
	private String deadLetterExchange;

	@Value("${rabbitmq.routing.key}")
	private String routingkey;
	@Value("${rabbitmq.dead.routing.key}")
	private String deadRoutingkey;
	@Value("${rabbitmq.deadLetter.queue.name}")
	private String deadLetterQueue;


	@Bean
	DirectExchange deadLetterExchange() {
		return new DirectExchange(deadLetterExchange);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Queue dlq() {
		return QueueBuilder.durable(deadLetterQueue).build();
	}

	@Bean
	Queue queue() {
		return QueueBuilder.durable(queueName).withArgument("x-dead-letter-exchange", deadLetterExchange)
				.withArgument("x-dead-letter-routing-key", deadRoutingkey).build();
	}

	@Bean
	Binding DLQbinding() {
		return BindingBuilder.bind(dlq()).to(deadLetterExchange()).with(deadRoutingkey);
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(queue()).to(exchange()).with(routingkey);
	}


	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
}}
