package pl.sda.hibernatetraining;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.model.Author;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.model.PersonalData;
import pl.sda.hibernatetraining.repository.BookRepository;
import pl.sda.hibernatetraining.repository.IAuthorRepository;

import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;

public class BookRepositoryTest extends DatabaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

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

    @Test
    public void shouldFindByAuthorLastName() throws Exception {
        //given
        saveBookWithAuthor();
        //when
        List<Book> books = bookRepository.findByAuthorLastName(AUTHOR_LAST_NAME);

        //then
        assertEquals(1, books.size());
        Book book = books.iterator().next();

        assertEquals(book.getTitle(),BOOK_TITLE);
    }

    private void saveBookWithAuthor() {
        Book b = new Book(BOOK_TITLE);
        PersonalData pd = new PersonalData("Bob", AUTHOR_LAST_NAME, new Date());
        Author a = new Author(pd);
        authorRepository.save(a);
        b.addAuthor(a);
        bookRepository.save(b);
    }

    private void createAndSaveBook(String title, int year) {
        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);
        bookRepository.save(book);
    }
}
