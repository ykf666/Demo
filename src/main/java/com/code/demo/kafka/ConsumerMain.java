package com.code.demo.kafka;

public class ConsumerMain {
    
    public static void main(String[] args) {
        System.setProperty("log4j.configuration", "log4j.properties");
        KafkaConsumerExample consumer = new KafkaConsumerExample("10.60.1.246:9092,10.60.1.248:9092,10.60.1.249:9092","topic1","consumer-group1", true);
        consumer.consumerAuto();
        // consumer.consumerManual();
        // consumer.consumerExactly();
    }

}
