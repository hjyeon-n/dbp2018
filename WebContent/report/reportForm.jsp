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
    	
    	#name {	
    	text-align:justify; 
    	margin-left: 300px;
    	}
    	tr {height: 40;}
    	td {width: 43%;}
    	th {width: 20%;}
    </style>
    
    <script>
function report() {
	var dateExp = /^\d{4}-\d[01-12]{2}-\d[01-31]{2}$/;
	if(form.reportDate.value=="") {
		alert("날짜 형식이 올바르지 않습니다.");
		form.reportDate.focus();
		return false;
	}
	if (form.condition.value == "") {
		alert("동물 상태를 입력하십시오.");
		form.condition.focus();
		return false;
	} 
	if (form.vaccined.value == "") {
		alert("백신 여부를 입력하십시오.");
		form.vaccined.focus();
		return false;
	}	
	form.submit();
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
          <a class="navbar-brand" href="<c:url value='/mainPage'></c:url>">키다리 집사</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
           <li><a href="<c:url value='/introduction'></c:url>">사이트 소개</a></li>
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
        </c:if>
         </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
  <body>
  <br>
  <br>
  <br>
      <div class="container">
      <form name="reportForm" method="POST" action="<c:url value='/report/write'><c:param name='animalId' value='${animalId}'/></c:url>">
  <div class="form-group">
    <label for="reportDate">보고 날짜</label>
    <input type="text" class="form-control" name="reportDate" id="reportDate"
    placeholder="날짜를 입력하세요(ex.yyyymmdd)" size="10">
  </div>
  <div class="form-group">
    <label for="condition">상태</label>
    <input type="text" class="form-control" name="condition" id="condition"
    placeholder="동물 상태를 입력하세요" size="15" >
  </div>
   <div class="form-group">
    <label for="vaccined">백신 여부</label>
    <input type="text" class="form-control" name="vaccined" id="vaccined"
    placeholder="백신 여부를 입력하세요" >
  </div>
  <button type="submit" class="btn btn-default" onClick="report()">등록</button>
</form>
	  </div>
  </body>
</html>