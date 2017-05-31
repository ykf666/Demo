package com.code.demo.kafka;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerExample implements Runnable {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerExample.class);

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd-HHmmss");

	private final String topic;

	private final Properties properties = new Properties();

	private final Producer<String, String> producer;

	public KafkaProducerExample(String brokerList, String topic) {
		properties.put("bootstrap.servers", brokerList);
		properties.put("acks", "all");
		properties.put("retries", 0); // 消息发送请求失败重试次数
		properties.put("batch.size", 2000);
		properties.put("linger.ms", 1); // 消息逗留在缓冲区的时间，等待更多的消息进入缓冲区一起发送，减少请求发送次数
		properties.put("buffer.memory", 33554432); // 内存缓冲区的总量
		// 如果发送到不同分区，并且不想采用默认的Utils.abs(key.hashCode) %
		// numPartitions分区方式，则需要自己自定义分区逻辑
		properties.put("partitioner.class", "com.code.demo.kafka.SimplePartitioner");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		producer = new KafkaProducer<String, String>(properties);
		this.topic = topic;
	}

	public void run() {
		int i = 0;
		try {
			while (true) {
				TimeUnit.SECONDS.sleep(5);
				String key = Integer.toString(i);
				String value = "message-" + sdf.format(new Date());
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, key, value);
				producer.send(record, new Callback() {
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						if (exception != null) {
							logger.warn("send record error: {}", exception);
						}
						logger.info("offset: {}, partition: {}", metadata.offset(), metadata.partition());
					}
				});
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}
	}
}
