package pl.sda.hibernatetraining;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.repository.BookRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookRepositoryTest extends DatabaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldFindAllBooks() {
        // when
        List<Book> allBooks = bookRepository.findAll();
        // then
        assertEquals(allBooks.size(), 8);
    }

    @Test
    public void shouldSaveBook() throws Exception {
        String bookTitle = "book title";
        this.bookRepository.save(new Book(bookTitle));
        List<Book> books = bookRepository.findByPartialTitle(bookTitle.substring(0, 4));
        assertEquals(1, books.size());
        assertEquals(bookTitle, books.iterator().next().getTitle());
    }
}
