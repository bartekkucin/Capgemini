package pl.spring.demo.dao.impl;

import pl.spring.demo.annotation.NullableId;
import pl.spring.demo.common.Sequence;
import pl.spring.demo.dao.BookDao;
import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.bytebuddy.implementation.bind.annotation.AllArguments;

@Repository
public class BookDaoImpl extends AbstractDaoImpl<BookEntity> implements BookDao {

	private final Set<BookEntity> ALL_BOOKS = new HashSet<>();

	@Autowired
	private Sequence sequence;

	public BookDaoImpl() {
		addTestBooks();
	}

//	@Override
//	public List<BookEntity> findAll() {
//		return new ArrayList<>(ALL_BOOKS);
//	}

	@Override
	public List<BookEntity> findBookByTitle(String title) {
		List<BookEntity> bookEntities = new ArrayList<>();
		for (Iterator<BookEntity> it = ALL_BOOKS.iterator(); it.hasNext();) {
			BookEntity bookEntity = it.next();
			if (bookEntity.getTitle().toLowerCase().startsWith(title.toLowerCase())) {
				bookEntities.add(bookEntity);
			}
		}
		return bookEntities;
	}

	@Override
	public List<BookEntity> findBooksByAuthor(String author) {

		List<BookEntity> result = new ArrayList<>();
		String[] authorName = author.split("\\s+");
		for (Iterator<BookEntity> it = ALL_BOOKS.iterator(); it.hasNext();) {
			BookEntity bookEntity = it.next();
			String[] authors = bookEntity.getAuthor().split(",");
			for (String authorData : authors) {
				String[] temp = authorData.trim().split("\\s+");
				if (checkAuthorDataWithFirstNameXORLastName(authorName, temp)) {
					result.add(bookEntity);
				}
				if (checkAuthorDataWithFirstNameANDLastName(authorName, temp)) {
					result.add(bookEntity);
				}
			}

		}
		return result;
	}

	private boolean checkAuthorDataWithFirstNameANDLastName(String[] authorName, String[] temp) {
		return authorName.length == 2 && (temp[1].toLowerCase().startsWith(authorName[0].toLowerCase())
				|| temp[2].toLowerCase().startsWith(authorName[0].toLowerCase()));
	}

	private boolean checkAuthorDataWithFirstNameXORLastName(String[] authorName, String[] temp) {
		return authorName.length == 1 && (temp[1].toLowerCase().startsWith(authorName[0].toLowerCase())
				|| temp[2].toLowerCase().startsWith(authorName[0].toLowerCase()));
	}

//	@Override
//	@NullableId
//	public BookEntity save(BookEntity book) {
//		ALL_BOOKS.add(book);
//		return book;
//	}

	@Override
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	@Override
	public Sequence getSequence() {
		return sequence;
	}

	private void addTestBooks() {
		ALL_BOOKS.add(new BookEntity(1L, "Romeo i Julia", "1 Wiliam Szekspir"));
		ALL_BOOKS.add(new BookEntity(2L, "Opium w rosole", "2 Hanna Ożogowska"));
		ALL_BOOKS.add(new BookEntity(3L, "Przygody Odyseusza", "3 Jan Parandowski"));
		ALL_BOOKS.add(new BookEntity(4L, "Awantura w Niekłaju", "4 Edmund Niziurski"));
		ALL_BOOKS.add(new BookEntity(5L, "Pan Samochodzik i Fantomas", "5 Zbigniew Nienacki"));
		ALL_BOOKS.add(new BookEntity(6L, "Zemsta", "6 Aleksander Fredro"));
	}

	@Override
	public Set<BookEntity> getEntities() {
		return ALL_BOOKS;
	}
	
}
