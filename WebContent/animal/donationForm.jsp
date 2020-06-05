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

    <title>�Ϳ��� �۳���</title>

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
          <a class="navbar-brand" href="<c:url value='/mainPage'></c:url>">Ű�ٸ� ����</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
           <li class="active"><a href="<c:url value='/animal/animalList'><c:param name='page' value='${currentPage}'/></c:url>">�Ŀ����� ����</a></li>
           <li><a href="<c:url value='/missing/list'><c:param name='page' value='${currentPage}'/></c:url>">�������� �Ű�</a></li>
           <li><a href="<c:url value='/season/top'></c:url>">Ű�ٸ� ����</a></li>
        </ul>
        <form class="navbar-form navbar-right">
        <c:if test="${clientId eq null}">
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/login/form'></c:url>'">Sign in</button>
             <button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/join/form'></c:url>'">Join</button>
        </c:if>
        <c:if test="${clientId ne null}">
        	<span style="color:white">${client.clientName} �Ŀ��ڴ� �ȳ��ϼ���!</span>
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/logout'></c:url>'">Logout</button>
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/mypage'></c:url>'">MyPage</button>
        </c:if>
         </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    <body>
	<br><br>
	
<!-- �Ŀ��ϱ� �� -->
	<div align="center">
	<div class="input-group">
        <div class="row">
        <h1 class="h3 mb-3 font-weight-normal">�Ŀ��ϱ�</h1>
        	<table class="table table-bordered">
        		<tr>
        			<td colspan="3" align="center"><img src="${pageContext.request.contextPath}/images/animal/${DonAnimal.animalID}.JPG" width="200px" height="250px"></td>
        		</tr>
        		<tr>
        			<td colspan="3" align="center">${DonAnimal.animalName}</td>
        		</tr>
        		<tr>
        			<td>�̸�</td>
        			<td>${DonClient.clientName}</td>
        		</tr>
        		<tr>
        			<td>����ó</td>
        			<td>${DonClient.clientTel}</td>
        		</tr>
        		<tr>
        			<td>�Ŀ��ݾ�</td>
        			<td>${DonClient.clientDon}</td>
        		</tr>
        		<tr>
        			<td>���¹�ȣ</td>
        			<td>${DonClient.clientAccount}</td>
        		</tr>
        	</table>
        	<table class="table table-bordered">
        		<th><h3>�ȳ� ����</h3></th>
        		<tr>
        			<td colspan="5">
        			<ul>
        				<li>���� �ۼ� ������ ���� �Ŀ��� ����˴ϴ�.</li>
        				<li>�Ŵ� �Ŀ��� ����Ǹ�, ���� ��õ� ���¿��� �ش� �ݾ׸�ŭ ��ü�˴ϴ�.</li>
        				<li>�Ŀ� �ݾ� �� ���¹�ȣ �� <b>�������� ������ ������������ ȸ������������ ���� �����մϴ�.</b></li>
						<li>�Ŀ� �ݾ��� �Ŀ��� ������ ������� �����˴ϴ�.</li>					
						<li><b>�Ŀ��� �����Ͻ� ���, ��Ҵ� �Ұ����մϴ�.</b></li>
						<li><b>�Ŀ��� ���� <%=animal.getAnimalName()%>�� ���ο� ������ �Ǿ��ּ���!</b></li>
					</ul>
        			</td>
        		</tr>
        	</table>
    	</div>
     <!-- /container -->
	</div>	      	    
	   <a href="<c:url value='/animal/donation/submit'><c:param name='animalId' value='${DonAnimal.animalID}'/></c:url>" 
	   class="btn btn-default">�Ŀ��ϱ�</a>
	   <a href="<c:url value='/animal/animalList'><c:param name='currentPage' value='${currentPage}'/></c:url>" 
	   class="btn btn-default">���</a>
			    
	</div>
</body>
</html>