package Serdes;

import ReadCsv.PeopleInformationModel;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.nio.ByteBuffer;



public class PersonModelSerializer implements Serializer<PeopleInformationModel> {
    private String encoding = "UTF8";

    /**
     * Configure this class.
     *
     * @param configs configs in key/value pairs
     * @param isKey   whether is for key or value
     */
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    /**
     * Convert {@code data} into a byte array.
     *
     * @param topic topic associated with data
     * @param data  typed data
     * @return serialized bytes
     */
    @Override
    public byte[] serialize(String topic, PeopleInformationModel data) {
        int sizeOfId;
        int sizeOfFirstname;
        int sizeOfLastName;
        int sizeOfEmail;
        int sizeOfEmail2;
        int sizeOfProfession;
        int sizeOfFieldName;

        byte[] serializedId;
        byte[] serializedFirstName;
        byte[] serializedLastName;
        byte[] serializedEmail;
        byte[] serializedEmail2;
        byte[] serializedProfession;
        byte[] serializedFieldName;

        try{
            if (data == null){
                return null;
            }

            serializedId = String.valueOf(data.getId()).getBytes(encoding);
            serializedFirstName = data.getFirstname().getBytes(encoding);
            serializedLastName = data.getLastname().getBytes(encoding);
            serializedEmail = data.getEmail().getBytes(encoding);
            serializedEmail2 = data.getEmail2().getBytes(encoding);
            serializedProfession = data.getProfession().getBytes(encoding);
            serializedFieldName = data.getFieldName().getBytes(encoding);

            sizeOfId = serializedId.length;
            sizeOfFirstname = serializedFirstName.length;
            sizeOfLastName = serializedLastName.length;
            sizeOfEmail = serializedEmail.length;
            sizeOfEmail2 = serializedEmail2.length;
            sizeOfProfession = serializedProfession.length;
            sizeOfFieldName = serializedFieldName.length;

            ByteBuffer buf = ByteBuffer.allocate(4 + sizeOfId +
                    4 + sizeOfFirstname +
                    4 + sizeOfLastName +
                    4 + sizeOfEmail +
                    4 + sizeOfEmail2 +
                    4 + sizeOfProfession +
                    4 + sizeOfFieldName);



            buf.putInt(sizeOfId);
            buf.put(serializedId);

            buf.putInt(sizeOfFirstname);
            buf.put(serializedFirstName);

            buf.putInt(sizeOfLastName);
            buf.put(serializedLastName);

            buf.putInt(sizeOfEmail);
            buf.put(serializedEmail);

            buf.putInt(sizeOfEmail2);
            buf.put(serializedEmail2);

            buf.putInt(sizeOfProfession);
            buf.put(serializedProfession);

            buf.putInt(sizeOfFieldName);
            buf.put(serializedFieldName);

            buf.flip();


            return buf.array();



        }
        catch (Exception e){
            throw new SerializationException("Error when serializing Supplier to byte[]");
        }



    }



    /**
     * Close this serializer.
     * <p>
     * This method must be idempotent as it may be called multiple times.
     */
    @Override
    public void close() {
        Serializer.super.close();
    }
}
