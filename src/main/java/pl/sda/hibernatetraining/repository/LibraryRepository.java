package pl.sda.hibernatetraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.hibernatetraining.model.Library;

public interface LibraryRepository extends JpaRepository<Library, Long> {

}
