package pl.sda.hibernatetraining.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String nickName;

//    protected PersonalData personalData;

//    protected long version;

    // for hibernate
    public Author() {

    }

    public Author(String nickName) {
        this.nickName = nickName;
    }

    public Author(Long id, String nickName) {
        this.id = id;
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
