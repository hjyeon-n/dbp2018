<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${pageContext.request.contextPath}/bootstrap/img/paw.ico">

    <title>키다리 집사</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <style>
    	#joinForm {	
    	text-align:justify; 
    	}
    	#join {
    		width: 80%;
    		margin: auto;
    	}
    </style>
    <script>
    	function join() {
    		if(joinForm.id.value=="") {
    			alert("사용자 아이디를 입력하십시오.");
    			joinForm.id.focus();
    			return false;
    		}
    		var idEx = /^[A-za-z0-9]{1,15}/g;
    		if(idEx.test(joinForm.id.value)==false){
    			alert("아이디는 15자까지 입력할 수 있습니다.");
    			form.id.focus();
    			return false;
    		}
    		if(joinForm.pw.value=="") {
    			alert("비밀번호를 입력하십시오.");
    			joinForm.pw.focus();
    			return false;
    		}
    		var passEx = /^[A-za-z0-9]{1,20}/g;
    		if(passEx.test(joinForm.pw.value)==false){
    			alert("비밀번호는 20자까지 입력할 수 있습니다.");
    			joinForm.pw.focus();
    			return false;
    		}
    		var pass1 = joinForm.pw.value;
    		var pass2 = joinForm.pw2.value;
    		if(pass1 != pass2) {
    			alert("비밀번호가 일치하지 않습니다.");
    			joinForm.pw2.focus();
    			return false;
    		} 
    		if(joinForm.name.value=="") {
    			alert("이름을 입력하십시오.");
    			joinForm.name.focus();
    			return false;
    		}
    		var phoneEx = /^\d{2,3}-\d{3,4}-\d{4}$/;
    		if(phoneEx.test(joinForm.tel.value)==false) {
    			alert("전화번호 형식이 올바르지 않습니다.");
    			joinForm.tel.focus();
    			return false;
    		}
    		if(joinForm.addr.value=="") {
    			alert("주소를 입력하십시오.");
    			joinForm.addr.focus();
    			return false;
    		}
    		if(joinForm.account.value=="") {
    			alert("계좌번호를 입력하십시오.");
    			joinForm.account.focus();
    			return false;
    		}
    		joinForm.submit();
    	}
    </script>
  </head>
  
	<!-- 네이게이션바 -->
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
        <form class="navbar-form navbar-right">
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/login/form'></c:url>'">Sign in</button>
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/join/form'></c:url>'">Join</button>
         </form>
        </div>
      </div>
    </nav>
    
  <body>
    <div class="jumbotron">
    <div class="container">
      <!-- Join container -->
      <form name="joinForm" method="POST" action="<c:url value='/user/join'/>">
      <table id="join">
		<caption><h2 class="h3 mb-3 font-weight-normal">회원 가입</h2></caption>
		<tr>
			<th><h3 class="joinTitle">아이디</h3></th>
			<td><input type="text" name="id" maxlength="15" size="30"/>
			<c:if test="${registerFailed}">이미 존재하는 ID입니다.</c:if>
 		</tr>
 		<tr>
 			<th><h3 class="joinTitle">비밀번호</h3></th>
			<td><input type="password" id="clientPW" name="pw" maxlength="15" size="30" /></td>
		</tr>
		<tr>
			<th><h3 class="joinTitle">비밀번호 재확인</h3></th>
			<td><input type="password" id="clientPWCk" name="pw2" maxlength="15" size="30"/></td>
		</tr>
		<tr>
			<th><h3 class="joinTitle">이름</h3></th>
			<td><input type="text" id="clientName" name="name" maxlength="10" size="30"/></td>
		</tr>
		<tr>
			<th><h3 class="joinTitle">연락처</h3></th>
			<td><input type="text" id="clientTel" name="tel" maxlength="15" size="30"/></td>
		</tr>
		<tr>
			<th><h3 class="joinTitle">주소</h3></th>
			<td><input type="text" id="clientAddr" name="addr" maxlength="20" size="30"/></td>
		</tr>
		<tr>
			<th><h3 class="joinTitle">계좌번호</h3></th>
			<td><input type="text" id="clientAccount" name="account" maxlength="20" size="30"/></td>
		</tr>
		<tr>
			<th><h3 class="joinTitle">후원 금액</h3></th>
			<td><input type="number" id="clientDon" name="don" maxlength="20" size="30" value="30000"/>&nbsp;후원은 30,000원 이상부터 가능합니다.</td>
		</tr>
		</table>
		<table align="center">
			<tr>
			<td><input type="button" class="btn btn-small btn-default" value="회원 가입" onClick="join()"></td>
			</tr>
		</table>
        </form>
        </div>
        </div><!-- /container -->

      <hr>
  </body>
</html>