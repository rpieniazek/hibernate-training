package pl.sda.hibernatetraining.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BOOK_REVIEW")
public class BookReview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Lob
    @Column(nullable = false)
    private String content;

    @OneToOne
    @JoinColumn(name = "BOOK_FK", nullable = false, updatable = false)
    private Book book;

    // for hibernate
    protected BookReview() {
    }

    public BookReview(String content) {
        this.content = content;
    }

    public BookReview(Long id, String content) {
        this(content);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
