package com.test.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;
import java.util.Properties;
import java.time.Duration;

public class SimpleKafkaConsumer {

	public static void main(String[] args) {
		Properties props = new Properties();
		// Bootstrap server configuration
		props.put("bootstrap.servers", "b-6.ttkafka.oyqrov.c7.kafka.us-east-1.amazonaws.com:9092,b-5.ttkafka.oyqrov.c7.kafka.us-east-1.amazonaws.com:9092,b-3.ttkafka.oyqrov.c7.kafka.us-east-1.amazonaws.com:9092");
		props.put("group.id", "consumer01");
		props.put("enable.auto.commit", true);
		props.put("auto.offset.reset", "latest");
		props.put("key.deserializer" , "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer" , "org.apache.kafka.common.serialization.StringDeserializer");
		KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList("demo"));

		try {
			while(true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("Topic : %s , Partition : %d , Offset : %d, Key : %s, Value :%s\n",
                            record.topic(),record.partition(),record.offset(),record.key(),record.value());
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			consumer.close();
		}
	}
}