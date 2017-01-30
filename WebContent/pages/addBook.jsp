<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/ELibrary/resources/jquery.js"></script>
<script type="text/javascript">
 $(document).ready(function() {

		$("#name").change(function() {
			 $.ajax({
				 url: "controller?ajax=validate&name="+document.getElementById("name").value,
				    success: function(bln){
				    	if (bln == "true") {
				    		document.getElementById("nameNote").innerHTML = "ok";
				    		document.getElementById("nameNote").style.color="#86d56e";
				    	} else if (bln == "false") {
				    		document.getElementById("nameNote").innerHTML = "Поле название может содержать только буквы цифры и пробелы, и не длиннее 45 символов";
				    		document.getElementById("nameNote").style.color="#c95e72";
				    	}
				    }
			 });
		 });
		$("#author").change(function() {
			 $.ajax({
				 url: "controller?ajax=validate&name="+document.getElementById("author").value,
				    success: function(bln){
				    	if (bln == "true") {
				    		document.getElementById("authorNote").innerHTML = "ok";
				    		document.getElementById("authorNote").style.color="#86d56e";
				    	} else if (bln == "false") {
				    		document.getElementById("authorNote").innerHTML = "Поле название может содержать только буквы цифры и пробелы, и не длиннее 45 символов";
				    		document.getElementById("authorNote").style.color="#c95e72";
				    	}
				    }
			 });
		 });
		$("#year").change(function() {
			 $.ajax({
				 url: "controller?ajax=validate&year="+document.getElementById("year").value,
				    success: function(bln){
				    	if (bln == "true") {
				    		document.getElementById("yearNote").innerHTML = "ok";
				    		document.getElementById("yearNote").style.color="#86d56e";
				    	} else if (bln == "false") {
				    		document.getElementById("yearNote").innerHTML = "Введите год в формате: ХХХХ";
				    		document.getElementById("yearNote").style.color="#c95e72";
				    	}
				    }
			 });
		 });
		$("#description").change(function() {
			 $.ajax({
				 url: "controller?ajax=validate&description="+document.getElementById("description").value,
				    success: function(bln){
				    	if (bln == "true") {
				    		document.getElementById("descriptionNote").innerHTML = "ok";
				    		document.getElementById("descriptionNote").style.color="#86d56e";
				    	} else if (bln == "false") {
				    		document.getElementById("descriptionNote").innerHTML = "Поле описание может быть не длиннее 255 символов";
				    		document.getElementById("descriptionNote").style.color="#c95e72";
				    	}
				    }
			 });
		 });
 });
</script>
<body>
<jsp:include page="/pages/header.jsp"></jsp:include><br>
<div class="col-sm-1 col-md-2 col-lg-3"></div>
<div class="col-sm-10 col-md-8 col-lg-6">
<h3>Добавить новую книгу:</h3>
<form action="controller?action=loadContent" method="POST" enctype="multipart/form-data" style="padding-top: 5px">
<div class="input-group">
  <span class="input-group-addon">Название книги</span>
  <input type="text" class="form-control" name="name" id="name" value="${preBook.name}">
</div>
<p id="nameNote" align="center"></p>
<div class="input-group">
  <span class="input-group-addon">Автор</span>
  <input type="text" class="form-control" name="author" id="author" value="${preBook.author}">
</div>
<p id="authorNote" align="center"></p>
<div class="input-group">
  <span class="input-group-addon">Год издания</span>
  <input type="text" class="form-control" name="year" id="year" value="${preBook.year}">
</div>
<p id="yearNote" align="center"></p>
<div class="input-group">
  <span class="input-group-addon">Краткое описание</span>
  <textarea class="form-control" rows="3" name="description" id="description" value="${preBook.description}"></textarea>
</div>
<p id="descriptionNote" align="center"></p>
<div class="input-group" style="padding-bottom: 5px">
  <span class="input-group-addon"><span class="glyphicon glyphicon-file" aria-hidden="true"></span></span>
  <span class="input-group-addon"><input id="content" type="file" class="file" name="content" accept="application/pdf,application/txt,text/html,application/fb2,application/djvu,application/epab,application/mobi,application/rtf,application/doc"></span>
</div>
<p id="buttonNote" style="color: #86d56e">${buttonNote}</p>
<p id="buttonNoteException" style="color: #c95e72">${buttonNoteException}</p>
<input type="submit" class="btn btn-primary" value="Добавить" id="loadAva"/>
</form>
</div>
</body>
</html>