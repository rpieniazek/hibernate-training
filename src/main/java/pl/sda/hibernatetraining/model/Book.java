package pl.sda.hibernatetraining.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @OneToOne(cascade = CascadeType.ALL)
    private Author author;

//    private BookReview bookReview;
//
//    private Library library;
//
//    private long version;

    // for hibernate
    protected Book() {
    }

    public Book(String title) {
        this.title = title;
    }

    public Book(Long id, String title) {
        this(title);
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Author getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
