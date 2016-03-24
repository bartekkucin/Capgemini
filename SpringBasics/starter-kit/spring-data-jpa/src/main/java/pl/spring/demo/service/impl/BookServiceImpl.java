package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BookMapper bookMapper;

	@Override
	@Cacheable("booksCache")
	public List<BookTo> findAllBooks() {
		return bookMapper.rebuildBookEntityListInBookToList(bookDao.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return bookMapper.rebuildBookEntityListInBookToList(bookDao.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return bookMapper.rebuildBookEntityListInBookToList(bookDao.findBooksByAuthor(author));
	}

	@Override
	public BookTo saveBook(BookTo book) {
		return bookMapper.rebulidBookEntityInBookTo(bookDao.save(bookMapper.rebuildBookToInBookEntity(book)));
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}
}
