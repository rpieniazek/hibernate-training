package pl.sda.hibernatetraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.service.BookService;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class HibernateTrainingApplication {

    @Autowired
    private BookService bookService;

    @PostConstruct
    private void init() {
        List<Book> books = bookService.findByPartialTitle("W pus");
        books.forEach(book -> System.out.println(book.getTitle()));
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateTrainingApplication.class, args);
    }
}
