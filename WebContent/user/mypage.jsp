<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>마이페이지</title>
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
function clientUpdate(targetUri) {
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
        <h1 class="h3 mb-3 font-weight-normal">마이페이지</h1>
        	<table class="table table-bordered">
        		<tr>
        			<td>아이디</td>
        			<td colspan="2">${client.clientID}</td>
        		</tr>
        		<tr>
        			<td>이름</td>
        			<td colspan="2">${client.clientName}</td>
        		</tr>
        		<tr>
        			<td>회원등급</td>
        			<td colspan="2">${client.clientGrade}</td>
        		</tr>
        		<tr>
        			<td>연락처</td>
        			<td colspan="2">${client.clientTel}</td>
        		</tr>
        		<tr>
        			<td>주소</td>
        			<td colspan="2">${client.clientAddr}</td>
        		</tr>
        		<tr>
        			<td>후원금액</td>
        			<td colspan="2">${client.clientDon}</td>
        		</tr>
        		<tr>
        			<td>계좌번호</td>
        			<td colspan="2">${client.clientAccount}</td>
        		</tr>
        		<tr><td colspan="3" align="left">후원한 동물</td></tr>
        		<tr>
        		<c:forEach var="donationAnimals" items="${donationAnimals}">
        			<td>
        				<img src="../images/animal/${donationAnimals.animalID}.JPG" width="200px" height="250px"><br>
        				<p class="mt-5 mb-3">${donationAnimals.animalName}</p>
        				
        			</td>
        		</c:forEach>
        		</tr>
        		<tr><td colspan="3" align="left">임시 보호 중인 동물</td></tr>
        		<tr>
        		<c:forEach var="adoptcareAnimals" items="${adoptcareAnimals}">
        		<c:if test="${adoptcareAnimals.state == 2}">
        			<td>
        			<a href="<c:url value='../report/reportlist'>
        			<c:param name='animalId' value='${adoptcareAnimals.animalID}'/><c:param name='page' value='${currentPage}'/></c:url>">
        				<img src="../images/animal/${adoptcareAnimals.animalID}.JPG" width="200px" height="250px">
        			</a>
        				<p class="mt-5 mb-3">${adoptcareAnimals.animalName}</p>
        			</td>
        		</c:if>
        		</c:forEach>
        		<tr><td colspan="3" align="left">입양한 동물</td></tr>
        		<tr>
        		<c:forEach var="adoptcareAnimals" items="${adoptcareAnimals}">
        		<c:if test="${adoptcareAnimals.state == 3}">
        			<td>
        			<a href="<c:url value='../report/reportlist'>
        			<c:param name='animalId' value='${adoptcareAnimals.animalID}'/><c:param name='page' value='${currentPage}'/></c:url>">
        				<img src="${pageContext.request.contextPath}/images/animal/${adoptcareAnimals.animalID}.JPG" width="200px" height="250px"><br>
        				</a>
        				<p class="mt-5 mb-3">${adoptcareAnimals.animalName}</p>
        			</td>
        		</c:if>
        		</c:forEach>
        		</tr>
        	</table>
       		<div id="button" class="btn-group btn-group-sm" align="center">
       			<a href="<c:url value='/user/mypage/update/form'>
       				</c:url>"class="btn btn-default">회원정보변경</a>
       		</div>
    	</div>
     <!-- /container -->
	</div>
	</div>
</body>
</html>