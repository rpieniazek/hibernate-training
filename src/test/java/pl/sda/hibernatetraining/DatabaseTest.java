package pl.sda.hibernatetraining;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateTrainingApplication.class)
@TestPropertySource(locations = "classpath:test.properties")
@Transactional
@Rollback
public abstract class DatabaseTest {
    protected static final String AUTHOR_LAST_NAME = "Martin";
    protected static final String BOOK_TITLE = "Clean Code";
    protected static final String AUTHOR_FIRST_NAME = "Bob";

}
