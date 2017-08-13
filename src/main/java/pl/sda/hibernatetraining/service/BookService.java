package pl.sda.hibernatetraining.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.repository.BookRepository;

import javax.transaction.Transactional;

@Transactional
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book) {
        bookRepository.save(book);
    }
}
