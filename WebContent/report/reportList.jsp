<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
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
    	
    	#name {	
    	text-align:justify; 
    	margin-left: 300px;
    	}
    	tr {height: 40;}
    	td {width: 40%;}
    	th {width: 20%;}
    </style>
    <script>
		function reportRemove(id, writer) {
			return confirm("해당 보고서는 삭제됩니다.");		
		}
	</script>
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
          <a class="navbar-brand" href="<c:url value='/mainPage'></c:url>">DBP</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
           <li><a href="#">사이트 소개<span class="sr-only">(current)</span></a></li>
           <li><a href="<c:url value='/animal/animalList'><c:param name='page' value='${currentPage}'/></c:url>">후원동물 보기</a></li>
           <li class="active"><a href="<c:url value='/missing/list'><c:param name='page' value='${currentPage}'/></c:url>">실종동물 신고</a></li>
           <li><a href="<c:url value='/season/top'></c:url>">키다리 집사</a></li>
        </ul>
        <form class="navbar-form navbar-right">
        <c:if test="${client.clientID eq null}">
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/login/form'></c:url>'">Sign in</button>
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/join/form'></c:url>'">Join</button>
        </c:if>
        <c:if test="${client.clientID ne null}">
        	<span style="color:white">${client.clientName}후원자님 안녕하세요!</span>
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
 	<table class="table table-bordered">
 		<tr bgcolor="lightgray" align="center">
 			<td>보고 날짜</td>
 			<td>동물 상태</td>
 			<td>백신 여부</td>
 		</tr>
 		<c:forEach var="report" items="${report}">
 			<tr>
 			<c:set var="datevar" value='${report.reportDate}'/>
 			<td>${fn:substring(datevar,0,10)}</td>
 			<td>${report.condition}</td>
 			<td>${report.vaccined}</td>
 			<td><a href="<c:url value='/report/delete'>
 			<c:param name='reportId' value='${report.reportID}'/>
 			<c:param name='animalId' value='${animal.animalID}'/></c:url>" class="btn btn-default">삭제</a></td>
 		</tr>
 		</c:forEach>
 	</table>
 	<a href="<c:url value='/report/form'>
 	<c:param name='animalId' value='${animal.animalID}'/></c:url>" class="btn btn-default">리포트 등록</a>
 	
 	
 	</div>
  </body>
</html>