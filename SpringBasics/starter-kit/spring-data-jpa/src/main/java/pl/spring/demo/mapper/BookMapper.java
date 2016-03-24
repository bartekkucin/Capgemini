package pl.spring.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import pl.spring.demo.to.AuthorTo;
import pl.spring.demo.to.BookEntity;
import pl.spring.demo.to.BookTo;

@Component
public class BookMapper {

	public BookTo rebulidBookEntityInBookTo(BookEntity bookEntity) {
		List<AuthorTo> authorList = null;
		if (bookEntity.getAuthor() != null && !(bookEntity.getAuthor().isEmpty())) {
			authorList = rebuildAuthorStringInAuthorList(bookEntity.getAuthor());
		}
		return new BookTo(bookEntity.getId(), bookEntity.getTitle(), authorList);
	}

	public BookEntity rebuildBookToInBookEntity(BookTo bookTo) {
		String author = null;
		if (bookTo.getAuthors() != null) {
			author = rebuildAuthorsListToAuthorString((bookTo.getAuthors()));
		}
		return new BookEntity(bookTo.getId(), bookTo.getTitle(), author);
	}

	public List<BookTo> rebuildBookEntityListInBookToList(List<BookEntity> bookEntities) {
		List<BookTo> booksTo = new ArrayList<>();

		for (BookEntity bookEntity : bookEntities) {

			booksTo.add(rebulidBookEntityInBookTo(bookEntity));
		}

		return booksTo;

	}

	public List<BookEntity> rebuildBookToListOnBookEntityList(List<BookTo> booksTo) {
		List<BookEntity> bookEntities = new ArrayList<>();

		for (BookTo bookTo : booksTo) {

			bookEntities.add(rebuildBookToInBookEntity(bookTo));
		}

		return bookEntities;
	}

	public List<AuthorTo> rebuildAuthorStringInAuthorList(String author) {
		List<AuthorTo> authors = new ArrayList<>();
		String[] authorAsArray = author.split(",");

		for (String authorStr : authorAsArray) {
			String[] temp = authorStr.trim().split("\\s+");
			Long id = null;
			String firstName = "";
			String lastName = "";
			AuthorTo authorTo = createAuthorToFromAuthorString(id, firstName, lastName, temp);
			authors.add(authorTo);
		}
		return authors;

	}

	public String rebuildAuthorsListToAuthorString(List<AuthorTo> authors) {
		StringBuffer buffer = new StringBuffer();
		for (AuthorTo author : authors) {
			buffer.append(author.getId() + " " + author.getFirstName() + " " + author.getLastName() + ", ");
		}

		return buffer.substring(0, buffer.length() - 2);
	}

	public AuthorTo createAuthorToFromAuthorString(Long id, String firstName, String lastName, String[] temp) {
		if (temp[0] != null || !(temp[0].isEmpty())) {
			id = Long.valueOf(temp[0]);
		}
		if (temp.length > 1 && (temp[1] != null || !(temp[1].isEmpty()))) {
			firstName = temp[1];
		}
		if (temp.length > 2 && (temp[2] != null || !(temp[2].isEmpty()))) {
			lastName = temp[2];
		}

		return new AuthorTo(id, firstName, lastName);
	}

}
