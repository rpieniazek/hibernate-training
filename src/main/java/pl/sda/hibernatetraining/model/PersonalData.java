package pl.sda.hibernatetraining.model;

import java.util.Date;

public class PersonalData {

    public static final String LAST_NAME_PARAMETER = "lastName";

    private String firstName;

    private String lastName;

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
}
