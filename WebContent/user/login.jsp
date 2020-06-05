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

    <title>DBP 0204</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
    	tr, td {padding: 10px;}
    </style>
    
<script>
function login() {
	if (form.clientId.value == "") {
		alert("사용자 ID를 입력하십시오.");
		form.userId.focus(); 
		return false;
	} 
	if (form.password.value == "") {
		alert("비밀번호를 입력하십시오.");
		form.password.focus();
		return false;
	}
	form.submit();
}

function clientCreate(targetUri) {
	form.action = targetUri;
	form.submit();
}
</script>
</head>
<body>
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
           <li><a href="<c:url value='/missing/list'><c:param name='page' value='${currentPage}'/></c:url>">실종동물 신고</a></li>
           <li><a href="<c:url value='/season/top'></c:url>">키다리 집사</a></li>
        </ul>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
	
	<br><br><br>
	<!-- 로그인 폼 -->
	<div align="center">
	<h1 class="h3 mb-3 font-weight-normal">Sign in</h1>
	<br>
	
	<!-- 로그인이 실패한 경우 exception 객체에 저장된 오류 메시지를 출력 -->
        <c:if test="${loginFailed}">
	  	  <br><font color="red"><c:out value="${exception.getMessage()}" /></font><br>
	    </c:if>
	    
	<form name="form" class="form-horizontal" method="POST" action="<c:url value='/user/login' />">
		<div class="control-group">
			<div class="controls">
				<label for="inputId" class="control-label">ID</label>
				&nbsp; &nbsp;
				<input type="text" id="inputId" name="clientId" class="input-small" placeholder="아이디" required autofocus>
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<label for="inputPassword" class="control-label">PW</label>
				&nbsp; 
				<input type="Password" id="inputPassword" name="password" class="input-small" placeholder="비밀번호" required>
			</div>
		</div>
		<br>
		<div class="control-group">
			<div class="controls">
				<button class="btn btn-small btn-default" type="submit" onClick="login()">로그인</button> &nbsp;
				<button class="btn btn-small btn-default" type="submit" onClick="location.href='<c:url value='/user/join/form'></c:url>'">회원가입</button>
			</div>
		</div>
	</form>
	</div>
  </body>
</html>