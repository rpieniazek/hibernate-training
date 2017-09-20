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

    public void update() {
        //pobranie
        Book book = findById(1L).get();
        System.out.println(book.getTitle());
        System.out.println(book.getAuthor().getNickName());

        //modyfikacja
        book.setTitle("New title");

        book.getAuthor().setNickName("New NickName");

        //zapis
        save(book);
    }
}
