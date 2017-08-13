package pl.sda.hibernatetraining.repository;

import org.springframework.stereotype.Repository;
import pl.sda.hibernatetraining.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Book book) {
        entityManager.persist(book);
    }
}
