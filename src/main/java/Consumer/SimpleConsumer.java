package Consumer;

import Producer.csvProducer;
import ReadCsv.PeopleInformationModel;
import Serdes.PersonModelDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

import static HelperFunctions.helperFunctions.loadConfig;

public class SimpleConsumer {
    private static final Logger logger = LoggerFactory.getLogger(SimpleConsumer.class);

    public static void main(String[] args) throws Exception {
        // Kafka consumr properties
        final Properties props = loadConfig("src/main/java/AppConfig/kafkaServer.properties");

        final String groupId = "simple-consumer";

        final String topicName = "simple-producer";

        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, PersonModelDeserializer.class.getName());
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Create the Consumer
        KafkaConsumer<Integer, PeopleInformationModel> consumer = new KafkaConsumer<Integer, PeopleInformationModel>(props);

        // Subscribe the consumer to a topic
        consumer.subscribe(Arrays.asList(topicName));

        // Poll for the messages
        while (true){
            logger.info("Polling messages ... ");
            ConsumerRecords<Integer, PeopleInformationModel> records = consumer.poll(Duration.ofMillis(10000));

            for (ConsumerRecord<Integer, PeopleInformationModel> record : records) {
                logger.info("Key : " + record.key() + " value : " + record.value().toString());
                logger.info("Partition : " + record.partition() + " Offset : " + record.offset());
            }

        }


    }
}
