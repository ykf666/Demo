package com.code.demo.kafka;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.consumer.OffsetCommitCallback;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumerExample implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerExample.class);

	private final Properties properties = new Properties();

	private final KafkaConsumer<String, String> consumer;

	private final String topic;

	public KafkaConsumerExample(String brokerList, String topic, String groupId, boolean autoCommit) {
		properties.put("bootstrap.servers", brokerList);
		properties.put("group.id", groupId);
		properties.put("enable.auto.commit", autoCommit);
		properties.put("auto.commit.interval.ms", "60000");
		properties.put("session.timeout.ms", "30000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

		consumer = new KafkaConsumer<String, String>(properties);
		this.topic = topic;
	}

	// 自动提交偏移量
	public void consumerAuto() {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList(topic));
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(10);
				for (ConsumerRecord<String, String> record : records) {
					logger.info("thread = {}, partition = {}, offset = {}, key = {}, value = {}",
							Thread.currentThread().getId(), record.partition(), record.offset(), record.key(),
							record.value());
				}
			}
		} finally {
			consumer.close();
		}
	}

	/**
	 * 手动控制偏移量
	 */
	public void consumerManual() {
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList(topic));
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(10);
				for (ConsumerRecord<String, String> record : records) {
					logger.info("partition = {}, offset = {}, key = {}, value = {}", record.partition(),
							record.offset(), record.key(), record.value());
				}
				if (!records.isEmpty()) {
					// 异步提交offset
					consumer.commitAsync(new OffsetCommitCallback() {
						public void onComplete(Map<TopicPartition, OffsetAndMetadata> offsets, Exception exception) {
							for (Entry<TopicPartition, OffsetAndMetadata> offset : offsets.entrySet()) {
								logger.info("commit offset: partition = {}, offset = {}", offset.getKey(),
										offset.getValue().offset());
							}
						}
					});
				}
			}
		} finally {
			consumer.close();
		}
	}

	/**
	 * 手工精确控制每个分区的偏移量
	 */
	public void consumerExactly() {
		consumer.subscribe(Arrays.asList(topic));
		try {
			while (true) {
				ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
				for (TopicPartition partition : records.partitions()) {
					List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
					for (ConsumerRecord<String, String> record : partitionRecords) {
						logger.info("partition = {}, offset = {}, key = {}, value = {}", record.partition(),
								record.offset(), record.key(), record.value());
					}
					long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
					consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
				}
			}
		} finally {
			consumer.close();
		}
	}

	public void run() {
		consumerAuto();
	}

}
