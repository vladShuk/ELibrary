package by.ELibrary;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@SuppressWarnings("serial")
@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet {
	// Кэш недавно просмотренных книг
	static Map<Integer, Book> booksCash = new HashMap< Integer, Book>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Запрос страницы web-приложения
		if (request.getParameter("page") != null) {
			switch (request.getParameter("page")) {
			case "list":
				// Получение списка книг
				try {
					// Получение определенной страницы книг
					if (request.getParameter("listPage") != null && !request.getParameter("listPage").equals("")) {
						request.setAttribute("list", DAO.listBooks(Integer.parseInt(request.getParameter("listPage"))));
						request.setAttribute("listPage", request.getParameter("listPage"));
						if (Integer.parseInt(request.getParameter("listPage")) > 1)
							request.setAttribute("prevListPage",
									Integer.parseInt(request.getParameter("listPage")) - 1);
						else
							request.setAttribute("prevListPage", 1);
						if (DAO.countOfPages() > Integer.parseInt(request.getParameter("listPage")))
							request.setAttribute("nextListPage",
									Integer.parseInt(request.getParameter("listPage")) + 1);
						else
							request.setAttribute("nextListPage", Integer.parseInt(request.getParameter("listPage")));
					// Получение списка книг для поиска
					} else if (request.getParameter("search") != null) {
						request.setAttribute("prevListPage", 1);
						request.setAttribute("nextListPage", 1);
						request.setAttribute("list", DAO.searchBooks(request.getParameter("search")));
					// Первая страница книг
					} else {
						request.setAttribute("prevListPage", 1);
						if (DAO.countOfPages() > 1)
							request.setAttribute("nextListPage", 2);
						else
							request.setAttribute("nextListPage", 1);
						request.setAttribute("list", DAO.listBooks());
					}
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				break;
			case "book":
				// Просмотр книги
				try {
					// При наличии книги в кэша - получение ее из оного
					if (booksCash.containsKey(Integer.parseInt(request.getParameter("booksId")))) {
						request.setAttribute("book", booksCash.get(Integer.parseInt(request.getParameter("booksId"))));
					// Иначе получение из БД
					} else {
						Book book = DAO.getBook(Integer.parseInt(request.getParameter("booksId")));
						booksCash.put(book.getId(), book);
						CashCleaner cc = new CashCleaner(book.getId());
						Thread thread = new Thread(cc);
						thread.start();
						request.setAttribute("book", book);
					}
					request.setAttribute("book", DAO.getBook(Integer.parseInt(request.getParameter("booksId"))));
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case "editBook":
				// Страница редактирования книги
				try {
					request.setAttribute("book", DAO.getBook(Integer.parseInt(request.getParameter("booksId"))));
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;

			default:
				break;
			}
			request.getRequestDispatcher("/pages/" + request.getParameter("page") + ".jsp").forward(request, response);
		} else if (request.getParameter("ajax") != null) {
			switch (request.getParameter("ajax")) {
			case "validate":
				// Валидация данных при добавлении/редактировании книги посредством ajax
				if (request.getParameter("name") != null) {
					PrintWriter out = response.getWriter();
					out.print(Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("name")));
					out.flush();
				} else if (request.getParameter("year") != null) {
					PrintWriter out = response.getWriter();
					out.print(Pattern.matches("[0-9]{1,4}", request.getParameter("year")));
					out.flush();
				} else if (request.getParameter("description") != null) {
					PrintWriter out = response.getWriter();
					out.print(request.getParameter("description").length() < 256);
					out.flush();
				}
				break;
				
			case "check":
				// Комплексная проверка вводимых данных книги
				PrintWriter out = response.getWriter();
				out.print(Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("name")) && Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("author")) && Pattern.matches("[0-9]{1,4}", request.getParameter("year")) && request.getParameter("description").length() < 256);
				out.flush();
				break;

			case "editBook":
				// Изменение книги
				try {
					DAO.editBook(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("author"), Integer.parseInt(request.getParameter("year")), request.getParameter("description"));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}

				// Валидация имени
