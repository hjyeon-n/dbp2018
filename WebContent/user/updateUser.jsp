<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>회원정보 변경</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/bootstrap/img/paw.ico">

    <title>MyPage</title>

    <!-- Bootstrap core CSS -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	
    <!-- Custom styles for this template -->
    <link href="signin.css" rel="stylesheet">
	<!-- <link href="jumpbotron.css" rel="stylesheet"> -->
	
    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="assets/js/ie-emulation-modes-warning.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
    	function update() {
    		if(updateForm.id.value=="") {
    			alert("사용자 아이디를 입력하십시오.");
    			updateForm.id.focus();
    			return false;
    		}
    		var idEx = /^[A-za-z0-9]{1,15}/g;
    		if(idEx.test(updateForm.id.value)==false){
    			alert("아이디는 15자까지 입력할 수 있습니다.");
    			form.id.focus();
    			return false;
    		}
    		if(updateForm.pw.value=="") {
    			alert("비밀번호를 입력하십시오.");
    			updateForm.pw.focus();
    			return false;
    		}
    		var passEx = /^[A-za-z0-9]{1,20}/g;
    		if(passEx.test(updateForm.pw.value)==false){
    			alert("비밀번호는 20자까지 입력할 수 있습니다.");
    			updateForm.pw.focus();
    			return false;
    		}
    		var pass1 = updateForm.pw.value;
    		var pass2 = updateForm.pw2.value;
    		if(pass1 != pass2) {
    			alert("비밀번호가 일치하지 않습니다.");
    			updateForm.pw2.focus();
    			return false;
    		} 
    		if(updateForm.name.value=="") {
    			alert("이름을 입력하십시오.");
    			updateForm.name.focus();
    			return false;
    		}
    		var phoneEx = /^\d{2,3}-\d{3,4}-\d{4}$/;
    		if(phoneEx.test(updateForm.tel.value)==false) {
    			alert("전화번호 형식이 올바르지 않습니다.");
    			updateForm.tel.focus();
    			return false;
    		}
    		if(updateForm.addr.value=="") {
    			alert("주소를 입력하십시오.");
    			updateForm.addr.focus();
    			return false;
    		}
    		if(updateForm.account.value=="") {
    			alert("계좌번호를 입력하십시오.");
    			updateForm.account.focus();
    			return false;
    		}
    		if(updateForm.don.value<30000) {
    			alert("30,000원 이상 후원해주세요.");
    			updateForm.account.focus();
    			return false;
    		}
    		updateForm.submit();
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
        <form class="navbar-form navbar-right">
        <c:if test="${clientId eq null}">
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/login/form'></c:url>'">Sign in</button>
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/join/form'></c:url>'">Join</button>
        </c:if>
        <c:if test="${clientId ne null}">
        	<span style="color:white">${client.clientName} 후원자님 안녕하세요!</span>
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/logout'></c:url>'">Logout</button>
        </c:if>
         </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    
	<br><br>
	<!-- 마이페이지 -->
	<div align="center">
	<div class="input-group"">
        <div class="row">
        <h1 class="h3 mb-3 font-weight-normal">회원정보변경</h1>
        	<form name = "updateForm" method="POST" action="<c:url value='/user/mypage/update'/>">
        	<table class="table table-bordered" id="update">
        		<tr>
        			<td>아이디</td>
        			<td colspan="2"><input type="text" name="id" maxlength="15" size="30"/ value="${client.clientID}" readonly></td>
        		</tr>
        		<tr>
        			<td>비밀번호</td>
        			<td><input type="password" id="clientPW" name="pw" maxlength="15" size="30" /></td>
        		</tr>
        		<tr>
					<td>비밀번호 확인</td>
					<td><input type="password" id="clientPWCk" name="pw2" maxlength="15" size="30"/></td>
				</tr>
        		<tr>
        			<td>이름</td>
        			<td colspan="2"><input type="text" id="clientName" name="name" maxlength="10" size="30" value="${client.clientName}"/></td>
        		</tr>
        		<tr>
        			<td>연락처</td>
        			<td colspan="2"><input type="text" id="clientTel" name="tel" maxlength="15" size="30" value="${client.clientTel}"/></td>
        		</tr>
        		<tr>
        			<td>주소</td>
        			<td colspan="2"><input type="text" id="clientAddr" name="addr" maxlength="20" size="30" value="${client.clientAddr}"/></td>
        		</tr>
        		<tr>
        			<td>후원금액</td>
        			<td colspan="2"><input type="number" id="clientDon" name="don" maxlength="20" size="30" value="${client.clientDon}"/></td>
        		</tr>
        		<tr>
        			<td>계좌번호</td>
        			<td colspan="2"><input type="text" id="clientAccount" name="account" maxlength="20" size="30" value="${client.clientAccount}"/></td>
        		</tr>
        	</table>
        	
       		<div id="button" class="btn-group btn-group-sm" align="center">
       			<input type="button" class="btn btn-small btn-default" value="수정" onClick="update()">
       		<a href="<c:url value='/user/mypage'>
        			</c:url>" class="btn btn-default">취소</a>
        	</form>
       		</div>
    	</div>
     <!-- /container -->
	</div>
	</div>
</body>
</html>