package com.code.basic.Kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerWorker implements Runnable {

	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerExample.class);

	private ConsumerRecord<String, String> record;

	public ConsumerWorker(ConsumerRecord<String, String> record) {
		this.record = record;
	}

	public void run() {
		logger.info("thread = {}, partition = {}, offset = {}, key = {}, value = {}", Thread.currentThread().getId(),
				record.partition(), record.offset(), record.key(), record.value());
	}
}
