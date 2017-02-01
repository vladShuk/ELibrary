package by.ELibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	private static final String path = "jdbc:mysql://localhost:3306/ELibrary";
	private static final String pathLogin = "root";
	private static final String pathPassword = "1091";

	static int addBook(String name, String author, int year, String description) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		st.execute("INSERT INTO books (name, author, year, description) VALUES('" + name + "', '" + author + "', " + year + ", '" + description + "')");
		ResultSet rs = st.executeQuery("select last_insert_id() AS id1 from books");
		int count = 0;
		if (rs.next())
			count = rs.getInt("id1");
		cn.close();
		
		return count;
	}
	
	static void setContent(int id, String content) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		st.execute("UPDATE books SET content = '" + content + "' WHERE id = '" + id + "'");
		cn.close();
	}
	
	static Book getBook(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
	
		ResultSet rs = st.executeQuery("SELECT * FROM books WHERE id = " + id);
		Book book = null;
		while(rs.next()) {
			int rsId = rs.getInt("id");
			String rsName = rs.getString("name");
			String rsAuthor = rs.getString("author");
			int rsYear = rs.getInt("year");
			String rsDescription = rs.getString("description");
			String rsContent = rs.getString("content");
			book = new Book(rsId, rsName, rsAuthor, rsYear, rsDescription, rsContent);
		}	
		cn.close();
		
		return book;
	}
	
	static List<Book> listBooks() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
	
		ResultSet rs = st.executeQuery("SELECT * FROM books ORDER BY id DESC LIMIT 5");
		List<Book> list = new ArrayList<Book>();
		while(rs.next()) {
			int rsId = rs.getInt("id");
			String rsName = rs.getString("name");
			String rsAuthor = rs.getString("author");
			int rsYear = rs.getInt("year");
			String rsDescription = rs.getString("description");
			String rsContent = rs.getString("content");
			list.add(new Book(rsId, rsName, rsAuthor, rsYear, rsDescription, rsContent));
		}	
		cn.close();
		
		return list;
	}
	
	static List<Book> listBooks(int page) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
	
		ResultSet rs = st.executeQuery("SELECT * FROM books ORDER BY id DESC LIMIT " + (page-1)*5 + ", " + page*5);
		List<Book> list = new ArrayList<Book>();
		while(rs.next()) {

public class DAO {
	private static final String path = "jdbc:mysql://localhost:3306/ELibrary";
	private static final String pathLogin = "root";
	private static final String pathPassword = "1091";

	// Добавление новой книги
	static int addBook(String name, String author, int year, String description) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		st.execute("INSERT INTO books (name, author, year, description) VALUES('" + name + "', '" + author + "', " + year + ", '" + description + "')");
		ResultSet rs = st.executeQuery("select last_insert_id() AS id1 from books");
		int count = 0;
		if (rs.next())
			count = rs.getInt("id1");
		cn.close();
		
		return count;
	}
	
	// Установка контента для новой книги
	static void setContent(int id, String content) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		st.execute("UPDATE books SET content = '" + content + "' WHERE id = '" + id + "'");
		cn.close();
	}
	
	// Получение книги
	static Book getBook(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
	
		ResultSet rs = st.executeQuery("SELECT * FROM books WHERE id = " + id);
		Book book = null;
		while(rs.next()) {
			int rsId = rs.getInt("id");
			String rsName = rs.getString("name");
			String rsAuthor = rs.getString("author");
			int rsYear = rs.getInt("year");
			String rsDescription = rs.getString("description");
			String rsContent = rs.getString("content");
			book = new Book(rsId, rsName, rsAuthor, rsYear, rsDescription, rsContent);
		}	
		cn.close();
		
		return book;
	}
	
	// Получение последних 5 книг
	static List<Book> listBooks() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
	
		ResultSet rs = st.executeQuery("SELECT * FROM books ORDER BY id DESC LIMIT 5");
		List<Book> list = new ArrayList<Book>();
		while(rs.next()) {
			int rsId = rs.getInt("id");
			String rsName = rs.getString("name");
			String rsAuthor = rs.getString("author");
			int rsYear = rs.getInt("year");
			String rsDescription = rs.getString("description");
			String rsContent = rs.getString("content");
			list.add(new Book(rsId, rsName, rsAuthor, rsYear, rsDescription, rsContent));
		}	
		cn.close();
		
		return list;
	}
	
	// Получение 5 книг для определенной страницы
	static List<Book> listBooks(int page) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
	
		ResultSet rs = st.executeQuery("SELECT * FROM books ORDER BY id DESC LIMIT " + (page-1)*5 + ", " + page*5);
		List<Book> list = new ArrayList<Book>();
		while(rs.next()) {
			int rsId = rs.getInt("id");
			String rsName = rs.getString("name");
			String rsAuthor = rs.getString("author");
			int rsYear = rs.getInt("year");
			String rsDescription = rs.getString("description");
			String rsContent = rs.getString("content");
			list.add(new Book(rsId, rsName, rsAuthor, rsYear, rsDescription, rsContent));
		}	
		cn.close();
		
		return list;
	}
	
	// Получение количества страниц (для пагинации)
	static int countOfPages() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM books;");
		int count = 0;
		if (rs.next())
			count = rs.getInt(1);
		cn.close();
		
		return count/5 + ((count%5==0)?0:1);
	}
	
	// Поиск строки среди названий книг и авторов
	static List<Book> searchBooks(String string) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM books WHERE name LIKE '%" + string + "%' OR author LIKE '%" + string + "%'");
		List<Book> list = new ArrayList<Book>();
		while(rs.next()) {
			int rsId = rs.getInt("id");
			String rsName = rs.getString("name");
			String rsAuthor = rs.getString("author");
			int rsYear = rs.getInt("year");
			String rsDescription = rs.getString("description");
			String rsContent = rs.getString("content");
			list.add(new Book(rsId, rsName, rsAuthor, rsYear, rsDescription, rsContent));
		}	
		cn.close();
		
		return list;
	}
	
	// Изменение книги
	static void editBook(int id, String name, String author, int year, String description) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		st.execute("UPDATE books SET name = '" + name + "', author = '" + author + "', year = " + year + ", description = '" + description + "' WHERE id = '" + id + "'");
		cn.close();
	}
	
	// Удаление книги
	static String deleteBook(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection cn = DriverManager.getConnection(path, pathLogin, pathPassword);
		Statement st = cn.createStatement();
		ResultSet rs = st.executeQuery("SELECT content FROM books WHERE id = " + id);
		String content = "";
		if (rs.next())
			content = rs.getString("content");
		st.execute("DELETE FROM books WHERE id = " + id);
		cn.close();
		return content;
	}
}
