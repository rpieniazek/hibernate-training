package pl.sda.hibernatetraining;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.sda.hibernatetraining.service.BookService;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableJpaRepositories
public class HibernateTrainingApplication {

    @Autowired
    private BookService bookService;

    @PostConstruct
    private void init() {
       bookService.saveTest();
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateTrainingApplication.class, args);
    }
}
