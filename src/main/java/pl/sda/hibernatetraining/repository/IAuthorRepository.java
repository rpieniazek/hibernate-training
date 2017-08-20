package pl.sda.hibernatetraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.hibernatetraining.model.Author;

public interface IAuthorRepository extends JpaRepository<Author, Long> {
}
