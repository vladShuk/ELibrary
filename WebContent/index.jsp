<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include><br>
<div class="col-sm-1 col-md-2 col-lg-3"></div>
<div class="col-sm-10 col-md-8 col-lg-6">
<div class="jumbotron">
<div class="container-fluid">  
  <h1>Добро пожаловать!</h1>
  <p>На этом сайте вы можете скачать интересующую вас книгу. При желании можно даже добавить новую :)</p>
  <p><a class="btn btn-primary btn-lg" href="controller?page=list" role="button">Список книг</a> <a class="btn btn-default btn-lg" href="/ELibrary/controller?page=addBook" role="button">Добавить книгу</a></p>
  </div>
</div>
</div>
</body>
</html>