package com.code.demo.kafka;

public class ProducerMain {

	public static void main(String[] args) {
		System.setProperty("log4j.configuration", "log4j.properties");
		KafkaProducerExample producer = new KafkaProducerExample("topic3");
		producer.run();
	}
}
