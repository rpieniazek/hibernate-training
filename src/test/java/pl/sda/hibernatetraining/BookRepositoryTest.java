package pl.sda.hibernatetraining;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.sda.hibernatetraining.model.Author;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.model.Library;
import pl.sda.hibernatetraining.model.PersonalData;
import pl.sda.hibernatetraining.repository.BookRepository;
import pl.sda.hibernatetraining.repository.IAuthorRepository;
import pl.sda.hibernatetraining.repository.LibraryRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BookRepositoryTest extends DatabaseTest {

    public static final String LIBRARY_NAME = "Sienkiewicza";
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IAuthorRepository authorRepository;

    @Autowired
    private LibraryRepository libraryRepository;

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
        assertEquals(book.getTitle(), BOOK_TITLE);

        Optional<String> lastName = book.getAuthors().stream()
                .map(Author::getPersonalData)
                .map(PersonalData::getLastName)
                .findFirst();

        assertTrue(lastName.isPresent());
        assertEquals(AUTHOR_LAST_NAME, lastName.get());
    }

    @Test
    public void shouldFindByLibraryName() throws Exception {
        //given
        createAndSaveBookWithLibrary();
        createAndSaveBook("Title", 2000);

        //when
        List<Book> books = bookRepository.findByLibraryName(LIBRARY_NAME);

        //then
        assertEquals(1, books.size());
        Book book = books.get(0);
        assertEquals(LIBRARY_NAME, book.getLibrary().getName());
        assertEquals(BOOK_TITLE, book.getTitle());
    }


    @Test
    public void shouldCountWithTitleLike() throws Exception {
        //given
        createAndSaveBook(BOOK_TITLE, 2000);
        createAndSaveBook(BOOK_TITLE, 2000);
        createAndSaveBook("another", 2000);

        //when
        long count = bookRepository.countWithTitleLike(BOOK_TITLE);

        //then
        assertEquals(2L, count);
    }

    private void saveBookWithAuthor() {
        Book b = new Book(BOOK_TITLE);
        PersonalData pd = new PersonalData(AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME, new Date());
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

    private void createAndSaveBookWithLibrary() {
        Book book = new Book(BOOK_TITLE);
        bookRepository.save(book);

        Library library = new Library(LIBRARY_NAME);
        library.addBook(book);
        libraryRepository.save(library);
    }
}
