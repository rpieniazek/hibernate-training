package pl.sda.hibernatetraining.model;

import java.io.Serializable;

public class Author implements Serializable {

    protected Long id;
    protected String nickName;

    protected PersonalData personalData;

    protected long version;

    // for hibernate
    public Author() {

    }

    public Author(Long id, PersonalData personalData, String nickName, long version) {
        this.id = id;
        this.version = version;
        this.personalData = personalData;
        this.nickName = nickName;
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
