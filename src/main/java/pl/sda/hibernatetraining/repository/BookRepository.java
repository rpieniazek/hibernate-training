package pl.sda.hibernatetraining.repository;

import org.springframework.stereotype.Repository;
import pl.sda.hibernatetraining.model.Author;
import pl.sda.hibernatetraining.model.Book;
import pl.sda.hibernatetraining.model.Library;
import pl.sda.hibernatetraining.model.PersonalData;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static pl.sda.hibernatetraining.model.Book.YEAR_PROPERTY;

@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public Optional<Book> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    public List<Book> findByPartialTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);

        Root<Book> books = criteriaQuery.from(Book.class);
        Predicate like = criteriaBuilder.like(books.get("title"), title + "%");
        criteriaQuery.where(like);

        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public List<Book> findWithYearBiggerThan(Long year) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);
        Root<Book> from = query.from(Book.class);

        Predicate yearGreaterThan = criteriaBuilder.ge(from.get(YEAR_PROPERTY), year);
        query.where(yearGreaterThan);

        return entityManager.createQuery(query).getResultList();
    }

    public List<Book> findByAuthorLastName(String authorName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> books = criteriaQuery.from(Book.class);

        Join<Book, Author> join = books.join(Book.AUTHORS);
        Path<String> authorLastNamePath = join.get(Author.PERSONAL_DATA_PROPERTY).get(PersonalData.LAST_NAME_PARAMETER);
        Expression<String> literal = criteriaBuilder.upper(criteriaBuilder.literal(authorName + "%"));
        Predicate predicate = criteriaBuilder.like(criteriaBuilder.upper(authorLastNamePath), literal);

        criteriaQuery.where(predicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public List<Book> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> rootEntry = cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);
        TypedQuery<Book> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }

    public Long count() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        CriteriaQuery<Long> result = query.select(criteriaBuilder.count(query.from(Book.class)));
        return entityManager.createQuery(result).getSingleResult();
    }


    //znalezc ksiazki, z biblioteki o podanej nazwie
    public List<Book> findByLibraryName(String libraryName) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);

        Root<Book> books = query.from(Book.class);
        Join<Book, Library> join = books.join("library");

        Predicate predicate = criteriaBuilder.like(join.get("name"), libraryName + "%");
        query.where(predicate);

        return entityManager.createQuery(query).getResultList();
    }

    //wypisac ilosc ksiazek, ktorych tytul zaczyna sie na litere ’S’
    public Long countWithTitleLike(String titlePrefix) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<Book> book = query.from(Book.class);

        query.select(criteriaBuilder.count(book));
        query.where(criteriaBuilder.like(book.get(Book.TITLE_PROPERTY), titlePrefix));
        return entityManager.createQuery(query).getSingleResult();
    }
}
