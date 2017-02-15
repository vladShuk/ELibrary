package by.ELibrary;

// Сущность "книга"
public class Book {
	private int id;
	private String name;
	private String author;
	private int year;
	private String description;
	// Путь к архиву для скачивания
	private String content;
	
	public Book(int id, String name, String author, int year, String description, String content) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.year = year;
		this.description = description;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}	
}
