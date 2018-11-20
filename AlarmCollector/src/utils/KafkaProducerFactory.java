package utils;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;
import kafka.serializer.StringEncoder;

public class KafkaProducerFactory  {
	
	/*private String topic;
	private String msg;
	
	public KafkaProducerFactory(String topic, String msg) {
		super();
		this.topic = topic;
		this.msg = msg;
	}
	*/
	/*private static KafkaProducer kafkaProducer;

	private void createProducer(){
		Properties properties = new Properties();
		//properties.setProperty("serializer.class", StringEncoder.class.getName());
		properties.setProperty("bootstrap.servers", "resource.shgcp.cmcc.com:9092");
		properties.setProperty("metadata.broker.list", "resource.shgcp.cmcc.com:9092");
		properties.setProperty("producer.type", "sync");
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		kafkaProducer = new KafkaProducer(properties);
	}
	
	@Override
	public void run(){
		this.createProducer();
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, msg);
		kafkaProducer.send(producerRecord);	
	}
	*/
	public static Producer createProducer(){
		Properties properties = new Properties();
		//properties.setProperty("zookeeper.connect", "resource.shgcp.cmcc.com:2181");
		properties.setProperty("serializer.class", StringEncoder.class.getName());
		properties.setProperty("metadata.broker.list", "resource.shgcp.cmcc.com:9092,mrs.shgcp.cmcc.com:9092,aggregator.shgcp.cmcc.com:9092");
		properties.setProperty("producer.type", "async");
		return  new Producer(new ProducerConfig(properties));
	}
	
	/*@Override
	public void run(){
		createProducer();
		producer.send(new KeyedMessage<String,String>(topic, msg));
	}*/

}
