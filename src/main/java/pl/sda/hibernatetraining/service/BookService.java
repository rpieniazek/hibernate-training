package pl.sda.hibernatetraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.repository.BookRepository;
import pl.sda.hibernatetraining.repository.IBookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IBookRepository jpaBookRepository;


    public void save(Book book) {
        bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findByPartialTitle(String title) {
        return bookRepository.findByPartialTitle(title);
    }

    public List<Book> findByAuthorLastName(String author) {
        return bookRepository.findByAuthorLastName(author);
    }

    public List<Book> findAll() {
        List<Book> all = jpaBookRepository.findAll();
        all.forEach(b -> b.getAuthors().forEach(author -> System.out.println(author.getNickName())));

        return all;
    }

    public List<Book> findAllByAuthorLastName(String name) {
        return jpaBookRepository.findByAuthors_personalData_lastNameContaining(name);
    }
}
