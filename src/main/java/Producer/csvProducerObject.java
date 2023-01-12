package Producer;

import ReadCsv.PeopleInformationModel;
import ReadCsv.ReadCsvFile;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

import static HelperFunctions.helperFunctions.loadConfig;

public class csvProducerObject {
    private static final Logger logger = LoggerFactory.getLogger(csvProducerObject.class);

    public static void main(String[] args) throws Exception{
        // Kafka producer properties
        final Properties props = loadConfig("src/main/java/AppConfig/kafkaServer.properties");

        props.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        props.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Serdes.ObjectSerializer.class.getName());

        final String topicName = "simple-producer";

        KafkaProducer<Integer, PeopleInformationModel> producer = new KafkaProducer<>(props);



        // The CSV file
        ReadCsvFile readCsvFile = new ReadCsvFile("data/peopleInformation.csv");
        List people = readCsvFile.readCsvFile();

        for(Object person:people){
            PeopleInformationModel personModel = (PeopleInformationModel) person;
            int key = personModel.getId();

            logger.info(String.valueOf(key) + " " + personModel.toString());


            ProducerRecord<Integer, PeopleInformationModel> producerRecord = new ProducerRecord<>(topicName, key,  personModel);

            producer.send(producerRecord, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if(e == null){
                        logger.info("Received new metadata : \n" +
                                "Topic : " + recordMetadata.topic() + "\n" +
                                "Partition : " + recordMetadata.partition() + "\n" +
                                "Offset : " + recordMetadata.offset() + "\n" +
                                "Timestamp : " + recordMetadata.timestamp() + "\n" +
                                "Key : " + key + " ; "
                        );
                    }
                    else {
                        logger.info("Error While Producing Message " + e);
                    }
                }
            });
        }
        producer.flush();
    }
}
