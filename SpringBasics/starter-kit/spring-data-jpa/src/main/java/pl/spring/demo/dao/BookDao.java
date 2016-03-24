package pl.spring.demo.dao;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.List;

public interface BookDao extends Dao<BookEntity>{

    //List<BookEntity> findAll();

    List<BookEntity> findBookByTitle(String title);

    List<BookEntity> findBooksByAuthor(String author);

    //BookEntity save(BookEntity book);
}
