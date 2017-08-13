package pl.sda.hibernatetraining.repository;

import org.springframework.stereotype.Repository;
import pl.sda.hibernatetraining.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }

    public Optional<Book> findById(Long id) {
        return Optional.of(entityManager.find(Book.class, id));
    }

    public List<Book> findByPartialTitle(String title) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> books = criteriaQuery.from(Book.class);

        criteriaQuery.where(criteriaBuilder.like(books.get("title"), title + "%"));

        TypedQuery<Book> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    public List<Book> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> cq = cb.createQuery(Book.class);
        Root<Book> rootEntry = cq.from(Book.class);
        CriteriaQuery<Book> all = cq.select(rootEntry);
        TypedQuery<Book> allQuery = entityManager.createQuery(all);
        return allQuery.getResultList();
    }
}
