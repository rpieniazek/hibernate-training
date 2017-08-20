package pl.sda.hibernatetraining;

import org.jooq.ConnectionProvider;
import org.jooq.impl.DefaultDSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.sda.hibernatetraining.service.BookService;

import javax.annotation.PostConstruct;

import static org.jooq.SQLDialect.MYSQL;

@SpringBootApplication
@EnableJpaRepositories
public class HibernateTrainingApplication {

    @Autowired
    private BookService bookService;

    @Bean
    public DefaultDSLContext dslContext(ConnectionProvider connectionProvider) {
        return new DefaultDSLContext(connectionProvider, MYSQL);
    }

    @PostConstruct
    private void init() {
        bookService.findBookDtoById(2l);
    }

    public static void main(String[] args) {
        SpringApplication.run(HibernateTrainingApplication.class, args);
    }
}
