package pl.sda.hibernatetraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.repository.BookRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

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

    public Long count() {
        return bookRepository.count();
    }

    public void updateBook() {
        Optional<Book> maybeBook = findById(2l);

        maybeBook.ifPresent(this::changeTitle);
    }

    private void changeTitle(Book book) {
        System.out.println(book.getTitle());
        book.setTitle("title changed");
        save(book);
    }

    public void printBooksAuthor(Long id){
        Optional<Book> maybeBook = findById(id);

        if(maybeBook.isPresent()){
            Book book = maybeBook.get();
            System.out.println("In the middle");
            book.getAuthors().forEach(author -> System.out.println(author.getPersonalData()));
        }
    }
}
