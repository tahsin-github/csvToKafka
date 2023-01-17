package ReadCsv;

import com.opencsv.bean.CsvBindByName;

import java.io.Serializable;

public class PeopleInformationModel {

    @CsvBindByName(column = "id")
    private int id;

    @CsvBindByName(column = "firstname")
    private String firstname;

    @CsvBindByName(column = "lastname")
    private String lastname;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "email2")
    private String email2;

    @CsvBindByName(column = "profession")
    private String profession;

    @CsvBindByName(column = "FieldName")
    private String FieldName;

    public PeopleInformationModel() {
    }

    public PeopleInformationModel(int id, String firstname, String lastname, String email, String email2, String profession, String fieldName) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.email2 = email2;
        this.profession = profession;
        this.FieldName = fieldName;
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

    @Override
    public String toString() {
        return "PeopleInformationModel{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", email2='" + email2 + '\'' +
                ", profession='" + profession + '\'' +
                ", FieldName='" + FieldName + '\'' +
                '}';
    }
}
