package pl.sda.hibernatetraining.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(name = "book_year")
    private int year;

    // for hibernate
    public Book() {
    }

    public Book(String title) {
        this.title = title;
    }
}
