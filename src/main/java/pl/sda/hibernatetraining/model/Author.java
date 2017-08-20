package pl.sda.hibernatetraining.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Author implements Serializable {

    public final static String PERSONAL_DATA_PROPERTY = "personalData";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = true, length = 30)
    protected String nickName;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "firstName", column = @Column(name = "FIRST_NAME", nullable = false)),
            @AttributeOverride(name = "lastName", column = @Column(name = "LAST_NAME", nullable = false))})
    protected PersonalData personalData;

    @Version
    protected long version;

    // for hibernate
    public Author() {

    }

    public Author(PersonalData personalData) {
        this.personalData = personalData;
    }

    public Long getId() {

        return this.id;
    }

    public PersonalData getPersonalData() {

        return this.personalData;
    }

    public String getNickName() {

        return this.nickName;
    }

    public long getVersion() {

        return this.version;
    }

    public void setVersion(long version) {

        this.version = version;
    }
}
