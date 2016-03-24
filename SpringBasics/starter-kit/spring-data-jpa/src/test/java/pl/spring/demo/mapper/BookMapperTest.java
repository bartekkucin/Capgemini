package pl.spring.demo.mapper;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookMapperTest {

	private BookMapper bookMapper;

	@Before
	public void setUp() {
		bookMapper = new BookMapper();
	}

	@Test
	public void testShouldRebuildBookToToBookEntity() {
		// given
		BookTo bookTo = new BookTo(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "lastName")));
		// when
		BookEntity bookEntity = bookMapper.rebuildBookToInBookEntity(bookTo);
		// then
		assertNotNull(bookEntity);
		assertNotNull(bookTo);
		assertEquals("title", bookEntity.getTitle());
		assertEquals("1 firstName lastName", bookEntity.getAuthor());
	}

	@Test
	public void testShouldRebuildBookToInBookEntityWithOnlyFirstName() {
		// given
		BookTo bookTo = new BookTo(1L, "title", Arrays.asList(new AuthorTo(1L, "firstName", "")));
		// when
		BookEntity bookEntity = bookMapper.rebuildBookToInBookEntity(bookTo);
		// then
		assertNotNull(bookEntity);
		assertNotNull(bookTo);
		assertEquals("title", bookEntity.getTitle());
		assertEquals("1 firstName ", bookEntity.getAuthor());
	}

	@Test
	public void testShouldRebuildBookEntityInBookTo() {
		// given
		BookEntity bookEntity = new BookEntity(1L, "title", "1 firstName lastName");
		// when
		BookTo bookTo = bookMapper.rebulidBookEntityInBookTo(bookEntity);
		// then
		assertNotNull(bookEntity);
		assertNotNull(bookTo);
		assertEquals("title", bookTo.getTitle());
		assertEquals("firstName", bookTo.getAuthors().get(0).getFirstName());
		assertEquals("lastName", bookTo.getAuthors().get(0).getLastName());

	}

	@Test
	public void testShouldRebuildBookEntitiesInBookToList() {
		// given
		List<BookEntity> bookEntities = new ArrayList<>();
		bookEntities.add(new BookEntity(1L, "title1", "1 firstName1 lastName1"));
		bookEntities.add(new BookEntity(2L, "title2", "2 firstName2 lastName2"));
		bookEntities.add(new BookEntity(3L, "title3", "3 firstName3 lastName3"));
		// when
		List<BookTo> booksTo = bookMapper.rebuildBookEntityListInBookToList(bookEntities);
		// then
		assertNotNull(bookEntities);
		assertNotNull(booksTo);
		assertEquals(3, booksTo.size());
	}

	@Test
	public void testShouldRebuildBookToListOnbookEntities() {
		// given
		List<BookTo> booksTo = Arrays.asList(new BookTo(1L, "title1", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))),
				new BookTo(2L, "title2", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))),
				new BookTo(3L, "title3", Arrays.asList(new AuthorTo(1L, "firstName", "lastName"))));
		// when
		List<BookEntity> bookEntities = bookMapper.rebuildBookToListOnBookEntityList(booksTo);
		// then
		assertNotNull(bookEntities);
		assertNotNull(booksTo);
		assertEquals(3, bookEntities.size());
	}

	@Test
	public void testShouldRebuildBookToAuthorToBookEntityAuthor() {
		// given
		String bookEntityAuthor = "1 firstName1 lastName1, 2 firstName2 lastName2, 3 firstName3 lastName3";
		// when
		List<AuthorTo> bookToAuthors = bookMapper.rebuildAuthorStringInAuthorList(bookEntityAuthor);
		// then
		assertNotNull(bookToAuthors);
		assertNotNull(bookEntityAuthor);
		assertEquals(3, bookToAuthors.size());
	}

	@Test
	public void testShouldRebuildBookEntityAuthorToBookToAuthor() {
		// given
		List<AuthorTo> bookEntityAuthors = Arrays.asList(new AuthorTo(1L, "firstName1", "lastName1"),
				new AuthorTo(2L, "firstName2", "lastName2"),
				new AuthorTo(3L, "firstName3", "lastName3"));
		// when
		String bookToAuthors = bookMapper.rebuildAuthorsListToAuthorString(bookEntityAuthors);
		// then
		assertNotNull(bookToAuthors);
		assertNotNull(bookEntityAuthors);
		assertEquals(3, bookToAuthors.split(",").length);
		assertEquals(bookToAuthors,
				"1 firstName1 lastName1, 2 firstName2 lastName2, 3 firstName3 lastName3");
	}
}
