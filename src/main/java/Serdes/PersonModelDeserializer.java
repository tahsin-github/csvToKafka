package Serdes;

import ReadCsv.PeopleInformationModel;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class PersonModelDeserializer implements Deserializer<PeopleInformationModel> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public PeopleInformationModel deserialize(String topic, byte[] data) {
        try {
            if (data == null) {
                System.out.println("Null recieved at deserialize");
                return null;
            }
            ByteBuffer buf = ByteBuffer.wrap(data);

            // get the id from the bytebuffer
            int sizeOfId = buf.getInt();
            byte[] IdBytes = new byte[sizeOfId];
            buf.get(IdBytes);
            String idString = new String(IdBytes);
            int id = Integer.parseInt(idString);


            // get the firstName from the bytebuffer
            int sizeOfFirstname = buf.getInt();
            byte[] FirstnameBytes = new byte[sizeOfFirstname];
            buf.get(FirstnameBytes);
            String firstname = new String(FirstnameBytes);


            // get the lastName from the bytebuffer
            int sizeOfLastName = buf.getInt();
            byte[] lastnameBytes = new byte[sizeOfLastName];
            buf.get(lastnameBytes);
            String lastname = new String(lastnameBytes);

            // get the Email from the bytebuffer
            int sizeOfEmail = buf.getInt();
            byte[] emailBytes = new byte[sizeOfEmail];
            buf.get(emailBytes);
            String email = new String(emailBytes);

            // get the Email2 from the bytebuffer
            int sizeOfEmail2 = buf.getInt();
            byte[] email2Bytes = new byte[sizeOfEmail2];
            buf.get(email2Bytes);
            String email2 = new String(email2Bytes);

            // get the Email2 from the bytebuffer
            int sizeOfProfession = buf.getInt();
            byte[] professionBytes = new byte[sizeOfProfession];
            buf.get(professionBytes);
            String profession = new String(professionBytes);

            // get the FieldName from the bytebuffer
            int sizeOfFieldName = buf.getInt();
            byte[] fieldNameBytes = new byte[sizeOfFieldName];
            buf.get(fieldNameBytes);
            String fieldName = new String(fieldNameBytes);


            // Make the PersonInformationModel Object

            PeopleInformationModel person = new PeopleInformationModel(id, firstname, lastname, email, email2, profession, fieldName);

            return person;

        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Supplier");
        }

    }

    @Override
    public void close() {

    }
}
