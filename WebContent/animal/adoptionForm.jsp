<%@page language="java" contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	
    <!-- Custom styles for this template -->
    
<script>
function adoption(clientGrade, clientId, dclientId) {
	var did = dclientId
	var vgrade = clientGrade
	var vid = clientId
	
	if(vgrade != "애옹") {
		alert("입양 신청은 [애옹]등급 이상의 회원만 가능합니다.");
		return false;
	}
	if(did != vid) {
		alert("자신이 후원 중인 동물만 입양 가능합니다.");
		return false;
	}
	
	adoptionForm.submit();
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
	
	<br><br>
	<!-- 입양 폼 -->
	<div align="center">
	<div class="input-group">
	<h1 class="h3 mb-3 font-weight-normal">입양 신청하기</h1>
	<form name=adoptionForm method="POST" action="<c:url value='/animal/adoption' />">
	<table class="table table-bordered">
		<tr>
        	<td colspan="3" align="center">
        		<img src="${pageContext.request.contextPath}/images/animal/${animal.animalID}.JPG" width="200px" height="250px" class="img-thumbnail">
        	</td>
        </tr>
        <tr>
        	<td colspan="3" align="center">${animal.animalName}</td>
        </tr>
        <tr>
        	<td>신청자 이름</td>
        	<td>${client.clientName}</td>
        </tr>
        <tr>
        	<td>신청자 등급</td>
        	<td>${client.clientGrade}</td>
        </tr>
        <tr>
        	<td>연락처</td>
        	<td>${client.clientTel}</td>
        </tr>
        <tr>
        	<td>주소</td>
        	<td>${client.clientAddr}</td>
        </tr>
	</table>
	
	<table class="table table-bordered">
		<th><h3>안내 사항</h3></th>
        <tr>
        	<td colspan="5">
        		<ul>
	        		<li>유기 동물 입양은 <b>"애옹"등급</b> 회원부터 가능합니다.</li>
	        		<li>안내 사항을 숙지한 후 확인 버튼을 누르면 위의 작성 내용을 바탕으로 입양 신청이 완료됩니다.</li>
	        		<li>입양한 동물의 목록은 마이페이지에서 확인 가능합니다.</li>
	        		<li>입양한 후 정기적인 의료 검진 등의 사항들이 포함된 보고서 폼을 작성할 수 있습니다.</li>
        			보고서 폼은 [마이페이지]-[입양한 동물]에서 해당 동물 클릭 시 이용할 수 있습니다.
        			<li><b>입양 후에 재유기 및 학대 등 동물에 유해한 행동을 하였을 경우, 본 사이트에서의 활동이 불가능하며 법적 처벌을 받을 수 있습니다.</b></li>
        			<li><b>한 동물의 소중한 삶과 관련된 사항입니다. 신중한 고민 후에 결정 부탁드립니다.</b></li>
        		</ul>
        			</textarea>
        			<br>
        			<div class="checkbox" align="right">
        				<label>
        					<input type="checkbox" value="agree" id="agree" checked disabled="true">
        					위의 내용을 모두 숙지하였습니다.
        				</label>
        			</div>	
        	</td>
        </tr>
    </table>
        <div id="button" class="btn-group btn-group-sm">
        	<a href="<c:url value='/animal/adoption'>
        				<c:param name='clientId' value='${client.clientID}'/>
        				<c:param name='animalId' value='${animal.animalID}'/>
        				<c:param name='careTerm' value='0'/>
        				<c:param name='state' value='3'/>
        			</c:url>" class="btn btn-default" onclick="return adoption('${client.clientGrade}', '${client.clientID}', '${donation.clientID}')">신청하기</a></div>
    	 		<a href="<c:url value='/animal/animalList'><c:param name='currentPage' value='${currentPage}'/></c:url>" class="btn btn-default">취소</a>
    </form>
    </div>
     <!-- /container -->
	</div>
</body>
</html>