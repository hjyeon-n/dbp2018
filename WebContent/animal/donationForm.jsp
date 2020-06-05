<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@page import="model.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	Animal animal = (Animal)request.getAttribute("DonAnimal");
	Client client = (Client)request.getAttribute("DonClient");
%>
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

    <title>귀여운 멍냥이</title>

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
           <li class="active"><a href="<c:url value='/animal/animalList'><c:param name='page' value='${currentPage}'/></c:url>">후원동물 보기</a></li>
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
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/mypage'></c:url>'">MyPage</button>
        </c:if>
         </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <body>
	<br><br>
	
<!-- 후원하기 폼 -->
	<div align="center">
	<div class="input-group">
        <div class="row">
        <h1 class="h3 mb-3 font-weight-normal">후원하기</h1>
        	<table class="table table-bordered">
        		<tr>
        			<td colspan="3" align="center"><img src="${pageContext.request.contextPath}/images/animal/${DonAnimal.animalID}.JPG" width="200px" height="250px"></td>
        		</tr>
        		<tr>
        			<td colspan="3" align="center">${DonAnimal.animalName}</td>
        		</tr>
        		<tr>
        			<td>이름</td>
        			<td>${DonClient.clientName}</td>
        		</tr>
        		<tr>
        			<td>연락처</td>
        			<td>${DonClient.clientTel}</td>
        		</tr>
        		<tr>
        			<td>후원금액</td>
        			<td>${DonClient.clientDon}</td>
        		</tr>
        		<tr>
        			<td>계좌번호</td>
        			<td>${DonClient.clientAccount}</td>
        		</tr>
        	</table>
        	<table class="table table-bordered">
        		<th><h3>안내 사항</h3></th>
        		<tr>
        			<td colspan="5">
        			<ul>
        				<li>위의 작성 내용을 토대로 후원이 진행됩니다.</li>
        				<li>매달 후원이 진행되며, 위에 명시된 계좌에서 해당 금액만큼 이체됩니다.</li>
        				<li>후원 금액 및 계좌번호 등 <b>개인정보 변경은 마이페이지의 회원정보변경을 통해 가능합니다.</b></li>
						<li>후원 금액은 후원을 시작한 당월부터 누적됩니다.</li>					
						<li><b>후원을 결정하신 경우, 취소는 불가능합니다.</b></li>
						<li><b>후원을 통해 <%=animal.getAnimalName()%>의 새로운 가족이 되어주세요!</b></li>
					</ul>
        			</td>
        		</tr>
        	</table>
    	</div>
     <!-- /container -->
	</div>	      	    
	   <a href="<c:url value='/animal/donation/submit'><c:param name='animalId' value='${DonAnimal.animalID}'/></c:url>" 
	   class="btn btn-default">후원하기</a>
	   <a href="<c:url value='/animal/animalList'><c:param name='currentPage' value='${currentPage}'/></c:url>" 
	   class="btn btn-default">취소</a>
			    
	</div>
</body>
</html>