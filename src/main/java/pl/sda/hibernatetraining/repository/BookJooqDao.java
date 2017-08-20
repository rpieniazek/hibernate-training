package pl.sda.hibernatetraining.repository;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.sda.hibernatetraining.dto.BookDto;

import static pl.sda.hibernatetraining.tables.Tables.BOOK;


@Repository
public class BookJooqDao {

    @Autowired
    private DSLContext dsl;

    public BookDto findById(Long id) {
        return dsl.select()
                .from(BOOK)
                .where(BOOK.ID.eq(id))
                .fetchOneInto(BookDto.class);
    }
}
