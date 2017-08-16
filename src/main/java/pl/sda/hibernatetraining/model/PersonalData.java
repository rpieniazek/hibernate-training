package pl.sda.hibernatetraining.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class PersonalData {

    public static final String LAST_NAME_PARAMETER = "lastName";

    private String firstName;

    private String lastName;

    @Column(columnDefinition="DATE", nullable = false)
    private Date birthDate;

    // for hibernate
    protected PersonalData() {

    }
    
    public PersonalData(String firstName, String lastName, Date birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
