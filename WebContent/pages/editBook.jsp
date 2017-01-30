<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript" src="/ELibrary/resources/jquery.js"></script>
<script type="text/javascript">
 $(document).ready(function() {
	$("#saveButton").click(function(){
		$.ajax({
			 url: "controller?ajax=check&name="+document.getElementById("name").value+"&author="+document.getElementById("author").value+"&year="+document.getElementById("year").value+"&description="+document.getElementById("description").value,
			    success: function(bln){
			    	if (bln == "true") {
			   		 $.ajax({
						 url: "controller?ajax=editBook&id="+document.getElementById("id").value+"&name="+document.getElementById("name").value+"&author="+document.getElementById("author").value+"&year="+document.getElementById("year").value+"&description="+document.getElementById("description").value,
						    success: function(){
					    		document.getElementById("buttonNote").innerHTML = "Изменения сохранены";
					    		document.getElementById("buttonNote").style.color="#86d56e";
						    }
					 });
			    	} else if (bln == "false") {
			    		document.getElementById("buttonNote").innerHTML = "Изменения не сохранены";
			    		document.getElementById("buttonNote").style.color="#c95e72";
			    	}
			    }
		 });
	 });
	$("#deleteButton").click(function(){
		 $.ajax({
			 url: "controller?ajax=deleteBook&id="+document.getElementById("id").value,
			    success: function(){
			    	window.location = "controller?page=list";
			    	alert("Книга была успешно удалена")
			    }
		 });
	 });
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
<h3>Изменить книгу:</h3>
<input type="hidden" id="id" value="${book.id}">
<div class="input-group">
  <span class="input-group-addon">Название книги</span>
  <input type="text" class="form-control" id="name" value="${book.name}">
</div>
<p id="nameNote" align="center"></p>
<div class="input-group">
  <span class="input-group-addon">Автор</span>
  <input type="text" class="form-control" id="author" value="${book.author}">
</div>
<p id="authorNote" align="center"></p>
<div class="input-group">
  <span class="input-group-addon">Год издания</span>
  <input type="text" class="form-control" id="year" value="${book.year}">
</div>
<p id="yearNote" align="center"></p>
<div class="input-group">
  <span class="input-group-addon">Краткое описание</span>
  <textarea class="form-control" rows="3" id="description">${book.description}</textarea>
</div>
<p id="descriptionNote" align="center"></p>
<input type="submit" class="btn btn-primary" value="Сохранить" id="saveButton"/> 
<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#myModal">
  Удалить
</button>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Вы уверены что хотите удалить данную книгу</h4>
      </div>
      <div class="modal-body">
        "${book.name}" ${book.author}
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Отмена</button>
        <button type="button" class="btn btn-danger" id="deleteButton">Удалить книгу</button>
      </div>
    </div>
  </div>
</div>
<p id="buttonNote"></p>
</div>
</body>
</html>