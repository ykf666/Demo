package com.code.basic.Kafka;

public class ConsumerGroupMain {

    private static final String brokerList = "10.60.1.246:9092,10.60.1.248:9092,10.60.1.249:9092";
    private static final String topic = "topic3";
    private static final String groupId = "consumer-group3";

    public static void main(String[] args) {
        System.setProperty("log4j.configuration", "log4j.properties");
        ConsumserGroupExample consumserGroup = new ConsumserGroupExample(3, brokerList, topic, groupId);
        consumserGroup.execute();
    }
}
