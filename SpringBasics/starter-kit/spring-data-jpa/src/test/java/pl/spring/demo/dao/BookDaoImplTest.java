package pl.spring.demo.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.common.Sequence;
import pl.spring.demo.exception.BookNotNullIdException;
import pl.spring.demo.to.BookEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"BookDaoImplTest-context.xml"})

public class BookDaoImplTest {

	@Autowired
	private BookDao bookDao;


	@Test
	public void testShouldFindAllBooks() {
		// when
		List<BookEntity> allBooks = bookDao.findAll();
		// then
		assertNotNull(allBooks);
		assertFalse(allBooks.isEmpty());
	}
	
	@Test
	public void testShouldFindBooksByAuthorFirstNameLastName() {
		// given
		String author = "Aleksander Fredro";
		// when
		List<BookEntity> books = bookDao.findBooksByAuthor(author);
		// then
		assertEquals(1, books.size());
		assertEquals(books.get(0).getAuthor(), "6 "+ author);
	}
	
	@Test
	public void testShouldFindAllBooksByTitle() {
		// given
		final String title = "Zemsta";
		// when
		List<BookEntity> books = bookDao.findBookByTitle(title);
		// then
		assertNotNull(books);
		assertEquals(1, books.size());
	}
	
	@Test
	public void testShouldntFindBooksByWringTitle() {
		// given
		final String title = "Rzemsta";
		// when
		List<BookEntity> books = bookDao.findBookByTitle(title);
		// then
		assertNotNull(books);
		assertTrue(books.isEmpty());
	}
	
	@Test
	public void testShouldFindAllBooksByTitleWithLowerAndCapitalLetters() {
		// given
		final String title = "ZEmsTa";
		// when
		List<BookEntity> books = bookDao.findBookByTitle(title);
		// then
		assertNotNull(books);
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindAllBooksByFragmentTitle() {
		// given
		final String title = "Zems";
		// when
		List<BookEntity> books = bookDao.findBookByTitle(title);
		// then
		assertNotNull(books);
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindAllBooksByAuthorLastNameFirstName() {
		// given
		final String author = "Fredro Aleksander";
		// when
		List<BookEntity> books = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(books);
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindAllBooksByFragmentAuthorsLastName() {
		// given
		final String author = "Ni";
		// when
		List<BookEntity> books = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(books);
		assertEquals(2, books.size());
	}
	
	@Test
	public void testShouldFindAllBooksByFragmentAuthorsFirstAndLastName() {
		// given
		final String author = "Ed Ni";
		// when
		List<BookEntity> books = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(books);
		assertEquals(1, books.size());
	}

	@Test
	public void testShouldFindAllBooksByFragmentAuthorsFirstName() {
		// given
		final String author = "Alek";
		// when
		List<BookEntity> books = bookDao.findBooksByAuthor(author);
		// then
		assertNotNull(books);
		assertEquals(1, books.size());
	}

}
