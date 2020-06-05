<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/bootstrap/img/paw.ico">

    <title>키다리 집사</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <style>
    	tr, td {padding: 10px;}
    </style>
  </head>
  <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="<c:url value='/mainPage'></c:url>">키다리 집사</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
           <li><a href="<c:url value='/animal/animalList'><c:param name='page' value='${currentPage}'/></c:url>">후원동물 보기</a></li>
           <li class="active"><a href="<c:url value='/missing/list'><c:param name='page' value='${currentPage}'/></c:url>">실종동물 신고</a></li>
           <li><a href="<c:url value='/season/top'></c:url>">키다리 집사</a></li>
        </ul>
        <form class="navbar-form navbar-right">
        <c:if test="${clientId eq null}">
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/login/form'></c:url>'">Sign in</button>
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/join/form'></c:url>'">Join</button>
        </c:if>
        <c:if test="${clientId ne null}">
        	<span style="color:white">${client.clientName} 후원자님 안녕하세요!</span>
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/logout'></c:url>'">Logout</button>
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/mypage'></c:url>'">MyPage</button>
        </c:if>
         </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
  <body>
  <br>
  <br>
  <br>
    <!-- Main jumbotron for a primary marketing message or call to action -->
      <div class="container">
      <!-- Example row of columns -->
	      <div class="row">
	      <c:forEach var="missing" items="${missingList}"> 
	        <div class="col-md-6">
	        	<table>
	          		<tr>
	          			<td rowspan ="5"> 
	          				<a href="<c:url value='/missing/detail'><c:param name='postId' value='${missing.postId}'/><c:param name='page' value='${currentPage}'/></c:url>">
	          					<img src="${pageContext.request.contextPath}/images/missing/${missing.missingImgPath}" width="200px" height="250px">
	          				</a>
	          			</td>
	          			<td>
	          				<a href="<c:url value='/missing/detail'><c:param name='postId' value='${missing.postId}'/><c:param name='page' value='${currentPage}'/></c:url>">
	          					${missing.missingName}
	          				</a>
	          			</td>
	          		</tr>
	          		<tr>
	          		<td>${missing.missingType}</td>
		          	</tr>
		          	<tr>
		          		<td>${missing.missingAddr}</td>
		          	</tr>
		          	<tr>
		          		<td>${missing.missingDate}</td>
		          	</tr>
		          	<tr>
		          		<td>${missing.missingDetail}</td>
		          	</tr>
	        	</table>
	        </div>
	        </c:forEach> 
	       </div>
	       <a href="<c:url value='/missing/write/form'></c:url>" class="btn btn-default">실종동물 등록</a>
	          	
 <nav aria-label="Page navigation example">
  <ul class="pagination justify-content-center">
  	<c:forEach var="i" begin="1" end="${totalPage}" step="1">
    	<li class="page-item"><a class="page-link" href="<c:url value='/missing/list'><c:param name='page' value='${i}'/></c:url>">${i}</a></li>
    </c:forEach>
  </ul>	      
</nav>
</div>
  </body>
</html>