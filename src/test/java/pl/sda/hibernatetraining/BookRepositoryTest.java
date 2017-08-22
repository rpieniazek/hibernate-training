package pl.sda.hibernatetraining;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.repository.BookRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;

public class BookRepositoryTest extends DatabaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldFindAllBooks() {
        // when
        List<Book> allBooks = bookRepository.findAll();
        // then
        assertEquals(allBooks.size(), 0);
    }

    @Test
    public void shouldFindByPartialTitle() throws Exception {
        //given
        String bookTitle = "book title";
        Book book = new Book(bookTitle);
        this.bookRepository.save(book);

        //when
        List<Book> books = bookRepository.findByPartialTitle(bookTitle.substring(0, 4));

        //then
        assertEquals(1, books.size());
        assertEquals(bookTitle, books.iterator().next().getTitle());
    }

    @Test
    public void shouldFindWithYearBiggerThan() throws Exception {
        //given

        String firstBookTitle = "bbb";
        String secondBookTitle = "ccc";

        createAndSaveBook("aaa", 1999);
        createAndSaveBook(firstBookTitle, 2000);
        createAndSaveBook(secondBookTitle, 2001);

        //when
        List<Book> books = bookRepository.findWithYearBiggerThan(2000L);

        //then
        assertEquals(2, books.size());
        List<String> titles = books.stream().map(Book::getTitle).collect(toList());

        Assert.assertThat(titles, hasItem(firstBookTitle));
        Assert.assertThat(titles, hasItem(secondBookTitle));
    }

    private void createAndSaveBook(String title, int year) {
        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);
        bookRepository.save(book);
    }
}
