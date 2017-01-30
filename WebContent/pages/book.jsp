<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/ELibrary/resources/jquery.js"></script>
<script type="text/javascript">
</script>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include><br>
<div class="col-sm-1 col-md-2 col-lg-3"></div>
<div class="col-sm-10 col-md-8 col-lg-6">
<h3>Детали книги:</h3>
<div class="container-fluid" style="border-bottom: thin; border-bottom-color: #DDDDFF; border-bottom-style: solid">
  <h3>"${book.name}" ${book.author}</h3>
  <p>${book.description}</p>
  <p style="color: gray;">Год издания: ${book.year}</p>
  <p><a class="btn btn-primary" href="/ELibrary/books/${book.content}.zip" role="button">Скачать</a> <a class="btn btn-default" href="controller?page=editBook&booksId=${book.id}" role="button">Редактировать</a></p>
</div>
</div>
</body>
</html>