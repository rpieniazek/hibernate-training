package pl.sda.hibernatetraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import pl.sda.hibernatetraining.model.*;
import pl.sda.hibernatetraining.repository.BookRepository;
import pl.sda.hibernatetraining.repository.IAuthorRepository;
import pl.sda.hibernatetraining.repository.IBookRepository;
import pl.sda.hibernatetraining.repository.LibraryRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IBookRepository jpaBookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    public void save(Book book) {
        jpaBookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public List<Book> findByPartialTitle(String title) {
        return bookRepository.findByPartialTitle(title);
    }

    public List<Book> findByAuthorLastName(String author) {
        return bookRepository.findByAuthorLastName(author);
    }

    public List<Book> findByBookReviewIsNull() {
        return jpaBookRepository.findByBookReviewIsNull();
    }

    public List<Book> findAllPaginated(int page, int size) {
        return jpaBookRepository.findAll(new PageRequest(page, size)).getContent();
    }

    public List<Book> findAllByAuthorLastName(String name) {
        return jpaBookRepository.findByAuthors_personalData_lastNameContaining(name);
    }

    public Long count() {
        return bookRepository.count();
    }

    public List<Book> findWithYearGreaterThan(Long year) {
        return bookRepository.findWithYearBiggerThan(year);
    }


    public Long countWithTitleLike(String titlePrefix) {
        return bookRepository.countWithTitleLike(titlePrefix);
    }

    public List<Book> findByLibraryName(String libraryName) {
        return jpaBookRepository.findByLibrary_nameContaining(libraryName);
    }

    public Long countWithNameContaing(String title) {
        return jpaBookRepository.countByTitleContaining(title);
    }

    public void saveTest() {
        Book book = new Book("Clean Code");
        PersonalData pd = new PersonalData("Bob", "Martin", new Date());
        Author author = new Author(pd);
        authorRepository.save(author);
        HashSet<Author> authors = new HashSet<>();
        authors.add(author);
        book.setAuthors(authors);

        BookReview bookReview = new BookReview("Great boook! ");
        book.setBookReview(bookReview);

        jpaBookRepository.save(book);

        Library library = new Library("Dubois2");
        library.addBook(book);
        libraryRepository.save(library);

    }
}
