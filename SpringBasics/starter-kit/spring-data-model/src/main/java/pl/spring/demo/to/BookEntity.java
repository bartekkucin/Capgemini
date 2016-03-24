package pl.spring.demo.to;

import java.util.List;

public class BookEntity implements IdAware {

	private Long id;
	private String title;
	private String author;

	public BookEntity(Long id, String title, String author) {
		this.id = id;
		this.title = title;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getId() {

		return id;
	}

}
