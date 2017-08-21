package pl.sda.hibernatetraining.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.hibernatetraining.model.Book;

import java.util.List;

public interface IBookRepository extends JpaRepository<Book, Long> {

    List<Book> findByAuthors_personalData_lastNameContaining(String name);

    //znalezc ksiazki, z biblioteki o podanej nazwie
    List<Book> findByLibrary_nameContaining(String name);

    //wypisac ilosc ksiazek, ktorych tytul zawiera podany fragment’
    Long countByTitleContaining(String title);
}
