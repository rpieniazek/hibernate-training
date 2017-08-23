package pl.sda.hibernatetraining.repository;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.DatabaseTest;
import pl.sda.hibernatetraining.dto.BookDto;
import pl.sda.hibernatetraining.model.Book;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;

public class BookJooqDaoTest extends DatabaseTest {

    @Autowired
    private BookJooqDao bookJooqDao;

    @Autowired
    private IBookRepository bookJPARepository;

    @Test
    public void shouldSaveBook() throws Exception {
        createAndSaveBook("Title", 1999);

        List<Book> all = bookJPARepository.findAll();
        assertEquals(1, all.size());
    }


    @Test
    public void shouldFindBookWithYearGreaterThan() throws Exception {


        String firstBookTitle = "bbb";
        String secondBookTitle = "ccc";

        createAndSaveBook("aaa", 1999);
        createAndSaveBook(firstBookTitle, 2000);
        createAndSaveBook(secondBookTitle, 2001);

        //when
        List<BookDto> books = bookJooqDao.findWithBookGreaterThan(2000);

        //then
        assertEquals(2, books.size());
        List<String> titles = books.stream().map(BookDto::getTitle).collect(toList());

        Assert.assertThat(titles, hasItem(firstBookTitle));
        Assert.assertThat(titles, hasItem(secondBookTitle));
    }

    @Test
    public void shouldFindByAuthorLastName() throws Exception {
        bookJooqDao.findByAuthorLastName();
    }

    private void createAndSaveBook(String title, int year) {
        Book book = new Book();
        book.setTitle(title);
        book.setYear(year);
        bookJPARepository.save(book);
    }
}