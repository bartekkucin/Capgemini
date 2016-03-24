package pl.spring.demo.mock;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import pl.spring.demo.dao.BookDao;
import pl.spring.demo.mapper.BookMapper;
import pl.spring.demo.mapper.BookMapperTest;
import pl.spring.demo.service.impl.BookServiceImpl;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

public class BookServiceImplTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	BookDao bookDao;
	@Mock
	BookMapper bookMapper;

	@Before
	public void setUp() {
		//bookMapper = new BookMapper();
		MockitoAnnotations.initMocks(this);
		//Whitebox.setInternalState(bookService, "bookMapper", bookMapper);
	}

	@Test
	public void testShouldSaveBook() {

		// given
		String author = "1 firstName lastName";
		List<AuthorTo> authorTos = new ArrayList<AuthorTo>();
    	authorTos.add(new AuthorTo(1L, "firstName", "lastName"));
    	
		BookTo book = new BookTo(null, "title", authorTos);
		BookEntity mappedBook = new BookEntity(null, "title", author);
		BookEntity savedBook = new BookEntity(1L, "title", author);
		
		Mockito.when(bookMapper.rebuildBookToInBookEntity(book)).thenReturn(mappedBook);
		Mockito.when(bookDao.save(mappedBook)).thenReturn(savedBook);
		Mockito.when(bookMapper.rebulidBookEntityInBookTo(savedBook)).thenReturn(new BookTo(savedBook.getId(), savedBook.getTitle(), authorTos));
		// when
		BookTo result = bookService.saveBook(book);
		// then
		Mockito.verify(bookDao).save(mappedBook);
		assertEquals(1L, result.getId().longValue());
	}
}
