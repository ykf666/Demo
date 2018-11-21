package com.code.demo.Kafka;

import java.util.Map;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimplePartitioner implements Partitioner {

	private static final Logger logger = LoggerFactory.getLogger(SimplePartitioner.class);
	
	public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
		int partition = 0;
		int offset = Integer.valueOf((String) key);
		if (offset >= 0) {
			partition = Integer.valueOf((String) key) % cluster.partitionCountForTopic(topic);
			logger.info("key: {}, partition: {}", key, partition);
		}
		return partition;
	}

	public void configure(Map<String, ?> configs) {
		
	}

	public void close() {
		
	}

}
