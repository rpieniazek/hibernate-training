package pl.sda.hibernatetraining.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.DatabaseTest;
import pl.sda.hibernatetraining.dto.BookDto;
import pl.sda.hibernatetraining.model.Book;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookJooqDaoTest extends DatabaseTest {

    @Autowired
    private BookJooqDao bookJooqDao;

    @Autowired
    private IBookRepository bookJPARepository;

    @Test
    public void shouldSaveBook() throws Exception {
        bookJooqDao.save(createBook());

        List<Book> all = bookJPARepository.findAll();
        assertEquals(1, all.size());
    }

    private BookDto createBook() {
        BookDto bookDto = new BookDto();
        bookDto.setTitle("Title");
        bookDto.setYear(2000);
        return bookDto;
    }
}