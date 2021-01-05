package com.code.basic.Kafka;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerHandler {

	private static final Logger logger = LoggerFactory.getLogger(ConsumerHandler.class);

	private final KafkaConsumer<String, String> consumer;

	private final Properties properties = new Properties();

	private ExecutorService executors;

	public ConsumerHandler(int threadPoolNum, String brokerList, String groupId, String topic) {
		//初始化线程池
		executors = new ThreadPoolExecutor(threadPoolNum, threadPoolNum, 0L, TimeUnit.MILLISECONDS,
				new ArrayBlockingQueue<Runnable>(1000), new ThreadPoolExecutor.CallerRunsPolicy());

		properties.put("bootstrap.servers", brokerList);
		properties.put("group.id", groupId);
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList(topic));
	}

	public void execute(int workerNum) {
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (final ConsumerRecord<String, String> record : records) {
				executors.submit(new ConsumerWorker(record));
			}
		}
	}

	public void shutdown() {
		if (consumer != null) {
			consumer.close();
		}
		if (executors != null) {
			executors.shutdown();
		}
		try {
			if (!executors.awaitTermination(10, TimeUnit.SECONDS)) {
				logger.info("Timeout.... Ignore for this case");
			}
		} catch (InterruptedException ignored) {
			logger.info("Other thread interrupted this shutdown, ignore for this case.");
			Thread.currentThread().interrupt();
		}
	}

}
