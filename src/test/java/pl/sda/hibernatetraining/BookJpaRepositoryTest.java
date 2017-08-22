package pl.sda.hibernatetraining;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.model.Author;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.model.PersonalData;
import pl.sda.hibernatetraining.repository.IAuthorRepository;
import pl.sda.hibernatetraining.repository.IBookRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookJpaRepositoryTest extends DatabaseTest {

    @Autowired
    private IBookRepository bookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Before
    public void setUp() throws Exception {
        Book b = new Book(BOOK_TITLE);
        PersonalData pd = new PersonalData("Bob", AUTHOR_LAST_NAME, new Date());
        Author a = new Author(pd);
        authorRepository.save(a);
        HashSet<Author> authors = new HashSet<>();
        authors.add(a);
        b.setAuthors(authors);
        bookRepository.save(b);
    }

    @Test
    public void shouldFindByAuthorName() throws Exception {
        List<Book> books = bookRepository.findByAuthors_personalData_lastNameContaining(AUTHOR_LAST_NAME.substring(0, 2));
        assertEquals(1, books.size());
        assertEquals(BOOK_TITLE, books.iterator().next().getTitle());
    }

    @Test
    public void shouldCountBooks() throws Exception {
        //todo implement
    }
}
