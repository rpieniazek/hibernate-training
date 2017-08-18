package pl.sda.hibernatetraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.sda.hibernatetraining.service.BookService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class HibernateTrainingApplication {

    @Autowired
    private BookService bookService;

    @PostConstruct
    private void init() {
//       bookService.findByLibraryName("Sie").forEach(book -> System.out.println(book.getTitle()));
        System.out.println(bookService.countWithTitleLike("W pustyni"));
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateTrainingApplication.class, args);
    }
}
