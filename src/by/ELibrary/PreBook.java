package by.ELibrary;

// Сущность для предварительного просмотра книги. Отличается от Book.class отсутствием ссылки к пути
public class PreBook {
	private String name;
	private String author;
	private String year;
	private String description;
	
	public PreBook(String name, String author, String year, String description) {
		this.name = name;
		this.author = author;
		this.year = year;
		this.description = description;
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
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
