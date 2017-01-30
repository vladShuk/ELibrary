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
	static Map<Integer, Book> booksCash = new HashMap< Integer, Book>();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("page") != null) {
			switch (request.getParameter("page")) {
			case "list":
				try {
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
					} else if (request.getParameter("search") != null) {
						request.setAttribute("prevListPage", 1);
						request.setAttribute("nextListPage", 1);
						request.setAttribute("list", DAO.searchBooks(request.getParameter("search")));
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
				try {
					if (booksCash.containsKey(Integer.parseInt(request.getParameter("booksId")))) {
						request.setAttribute("book", booksCash.get(Integer.parseInt(request.getParameter("booksId"))));
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
				PrintWriter out = response.getWriter();
				out.print(Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("name")) && Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("author")) && Pattern.matches("[0-9]{1,4}", request.getParameter("year")) && request.getParameter("description").length() < 256);
				out.flush();
				break;

			case "editBook":
				try {
					DAO.editBook(Integer.parseInt(request.getParameter("id")), request.getParameter("name"), request.getParameter("author"), Integer.parseInt(request.getParameter("year")), request.getParameter("description"));
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			case "deleteBook":
				try {
					new File("/home/vladey/workspace/ELibrary/WebContent/books/" + DAO.deleteBook(Integer.parseInt(request.getParameter("id"))) + ".zip").delete();
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
				
			default:
				break;
			}
					
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		if (request.getParameter("action") != "loadContent") {
			Part part = request.getPart("content");
			boolean bln1, bln2;
			bln1 = part.getSize() < 20000000 && (part.getContentType().contains("pdf") || part.getContentType().contains("txt") || part.getContentType().contains("html") || part.getContentType().contains("fb2") || part.getContentType().contains("djvu"));
			bln2 = Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("name")) && Pattern.matches("[a-z,A-Z,а-я,А-Я,0-9, ]{1,45}", request.getParameter("author")) && Pattern.matches("[0-9]{1,4}", request.getParameter("year")) && request.getParameter("description").length() < 256;
			if (bln1 && bln2) {
				try {
					int id = DAO.addBook(request.getParameter("name"), request.getParameter("author"), Integer.parseInt(request.getParameter("year")), request.getParameter("description"));
					String hex = Integer.toString(id*3613231, 32).toUpperCase();
					DAO.setContent(id, hex);
					String fileType = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf('.')+1, part.getSubmittedFileName().length());
					String fileName = "/home/vladey/workspace/ELibrary/WebContent/books/" + hex + "." + fileType;
					part.write(fileName);
					
					
					try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream("/home/vladey/workspace/ELibrary/WebContent/books/" + hex + ".zip"));
			                FileInputStream fis= new FileInputStream(fileName);)
			        {
			        	zout.setLevel(9);
			            ZipEntry entry1=new ZipEntry(hex + "." + fileType);
			            zout.putNextEntry(entry1);
			            byte[] buffer = new byte[fis.available()];
			            fis.read(buffer);
			            zout.write(buffer);
			            zout.closeEntry();
			            new File(fileName).delete();
			            request.setAttribute("buttonNote", "Книга успешно добавлена");
			        }
			        catch(Exception ex){
			            System.out.println(ex.getMessage());
			        } 
					
					
				} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
			} else {
				request.setAttribute("preBook", new PreBook(request.getParameter("name"), request.getParameter("author"), request.getParameter("year"), request.getParameter("description")));
				if (!bln1)
					request.setAttribute("buttonNoteException", "Книга должна иметь размер менее 20 Мб и быть одного из следующих разрешений: pdf, txt, html, fb2, djvu");
			}
			
			request.getRequestDispatcher("/pages/addBook.jsp").forward(request, response);
		}
	}
}
