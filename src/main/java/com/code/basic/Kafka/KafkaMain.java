package com.code.basic.Kafka;

public class KafkaMain {
	public static void main(String[] args) {
		String brokerList = "10.60.1.246:9092,10.60.1.248:9092,10.60.1.249:9092";
		String groupId = "consumer-group4";
		String topic = "topic3";
		int workerNum = 5;

		ConsumerHandler consumers = new ConsumerHandler(workerNum, brokerList, groupId, topic);
		consumers.execute(workerNum);
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		consumers.shutdown();
	}
}
