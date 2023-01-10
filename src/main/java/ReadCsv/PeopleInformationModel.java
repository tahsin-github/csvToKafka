package ReadCsv;

import com.opencsv.bean.CsvBindByName;

public class PeopleInformationModel {

    @CsvBindByName
    private int id;

    @CsvBindByName
    private String firstname;

    @CsvBindByName
    private String lastname;

    @CsvBindByName
    private String email;

    @CsvBindByName
    private String email2;

    @CsvBindByName
    private String profession;

    @CsvBindByName
    private String FieldName;

    public PeopleInformationModel(int id, String firstname, String lastname, String email, String email2, String profession, String fieldName) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.email2 = email2;
        this.profession = profession;
        FieldName = fieldName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getFieldName() {
        return FieldName;
    }

    public void setFieldName(String fieldName) {
        FieldName = fieldName;
    }
}
