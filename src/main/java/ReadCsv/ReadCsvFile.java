package ReadCsv;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ReadCsvFile {

    private static final Logger logger = LoggerFactory.getLogger(ReadCsvFile.class);

    private String csvFileName;
    private List personList;

    public ReadCsvFile(String csvFileName) {
        this.csvFileName = csvFileName;
    }

    public List readCsvFile(){
        try{
            CSVReader csvReader = new CSVReader(new FileReader(csvFileName));

            CsvToBean csvToBean = new CsvToBeanBuilder(csvReader).withType(PeopleInformationModel.class).build();

            personList = csvToBean.parse();

            csvReader.close();
        }
        catch (Exception e) {
            logger.error(String.valueOf(e));
        }
        return personList;
    }
}
