<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include><br>
<div class="col-sm-1 col-md-2 col-lg-3">
<nav aria-label="...">
  <ul class="pager">
    <li class="previous"><a href="/ELibrary/controller?page=list&listPage=${prevListPage}"><span aria-hidden="true">&larr;</span> Предыдущая</a></li>
  </ul>
</nav>
</div>
<div class="col-sm-10 col-md-8 col-lg-6">
<h2>Список книг:</h2>
<input type="hidden" value="${listPage}"/>
<c:forEach items="${list }" var="book">
<div class="container-fluid" style="border-bottom: thin; border-bottom-color: #DDDDFF; border-bottom-style: solid">
  <h3>"${book.name}" ${book.author}</h3>
  <p>${book.description}</p>
  <p style="color: gray;">Год издания: ${book.year}</p>
  <p><a class="btn btn-default" href="controller?page=book&booksId=${book.id}" role="button">Подробнее</a></p>
</div>
</c:forEach>
</div>
<div class="col-sm-1 col-md-2 col-lg-3">
<nav aria-label="...">
  <ul class="pager">
    <li class="next"><a href="/ELibrary/controller?page=list&listPage=${nextListPage}">Следующая <span aria-hidden="true">&rarr;</span></a></li>
  </ul>
</nav>
</div>
</body>
</html>