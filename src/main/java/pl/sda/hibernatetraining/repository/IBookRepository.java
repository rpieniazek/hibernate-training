package pl.sda.hibernatetraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.hibernatetraining.model.Book;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthors_personalData_lastNameContaining(String name);
}
