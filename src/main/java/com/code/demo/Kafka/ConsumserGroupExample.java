package com.code.demo.Kafka;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumserGroupExample {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerExample.class);
	
	private List<KafkaConsumerExample> consumers;

	public ConsumserGroupExample(int consumerNum, String brokerList, String topic, String groupId) {
		logger.info("consumerGroup: {}, numbers: {}, topic: {}", groupId , consumerNum, topic);
		consumers = new ArrayList<KafkaConsumerExample>(consumerNum);
		for (int i = 0; i < consumerNum; ++i) {
			KafkaConsumerExample consumerThread = new KafkaConsumerExample(brokerList, topic, groupId, true);
			consumers.add(consumerThread);
		}
	}

	public void execute() {
		for (KafkaConsumerExample task : consumers) {
			new Thread(task).start();
		}
	}
}
