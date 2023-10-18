public class SimplaKafkaProducer {
	private static final String topicName = "demo";
	public static void main(String[] args) {
		Properties props = new Properties();

		// Bootstrap server configuration
		props.put("bootstrap.servers", "b-6.ttkafka.oyqrov.c7.kafka.us-east-1.amazonaws.com:9092,b-5.ttkafka.oyqrov.c7.kafka.us-east-1.amazonaws.com:9092,b-3.ttkafka.oyqrov.c7.kafka.us-east-1.amazonaws.com:9092");
		// Serialize key and value for convert string to byte
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		// Create a producer with these settings
		KafkaProducer<String, String> producer = new KafkaProducer<>(props);
		try {
			for (int i = 0; i < 3; i++) {
				// Create records that the producer sends
				ProducerRecord<String, String> record = new ProducerRecord<>(topicName, 
					"Hello This is kafka record - " + String.valueOf(i));
				producer.send(record);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.close();
		}


	}
}