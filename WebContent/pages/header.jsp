<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Библиотека ELibrary</title>
<link href="/ELibrary/resources/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>	
<script type="text/javascript" src="/ELibrary/resources/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
$("#searchButton").click(function(){
	window.location = "controller?page=list&search="+document.getElementById("searchString").value;
});
});
</script>
<script type="text/javascript" src="/ELibrary/resources/bootstrap/js/bootstrap.min.js"></script>
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="col-md-2 col-lg-3"></div>
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header" >
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="/ELibrary"><span class="glyphicon glyphicon-book" aria-hidden="true">ELibrary.by</span></a>
    </div>

    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-left">
        <li><a href="/ELibrary/controller?page=list">Список книг<span class="sr-only">(current)</span></a></li>
        <li><a href="/ELibrary/controller?page=addBook">Добавить книгу</a></li>
      </ul>
      <form class="navbar-form navbar-left">
        <div class="form-group">
          <input type="text" class="form-control" id="searchString" placeholder="Поиск книги">
        </div>
        <button type="button" class="btn btn-default" id="searchButton">Искать!</button>
      </form>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<div style="padding-top: 50px"></div>
</body>
</html>