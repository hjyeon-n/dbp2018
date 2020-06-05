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
function missingCreate() {
	if (form.missingName.value == "") {
		alert("실종 동물 이름를 입력하십시오.");
		form.missingName.focus();
		return false;
	} 
	if (form.missingType.value == "") {
		alert("실종동물 종을 입력하십시오.");
		form.missingType.focus();
		return false;
	}
	if (form.missingAddr.value == "") {
		alert("실종 지역을 입력하십시오.");
		form.missingAddr.focus();
		return false;
	}
	if (form.missingDate.value == "") {
		alert("실종 날짜를 입력하십시오.(yy/mm/dd)");
		form.missingDate.focus();
		return false;
	}
	var emailExp = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
	if(emailExp.test(form.email.value)==false) {
		alert("이메일 형식이 올바르지 않습니다.");
		form.email.focus();
		return false;
	}
	var dateExp = /^\d{4}-\d{2}-\d{2}$/;
	if(dateExp.test(form.missingDate.value)==false) {
		alert("실종날짜 형식이 올바르지 않습니다.");
		form.missingDate.focus();
		return false;
	}
	
	
	alert("실종동물이 등록되었습니다.");
	
	form.submit();
}

function userList(targetUri) {
	form.action = targetUri;
	form.submit();
}

var request = new XMLHttpRequest();

function getCommunityList() {
	// Ajax를 이용하여 커뮤니티 목록 정보를 요청
	request.open("GET", "${pageContext.request.contextPath}/community/list/json?t=" + new Date().getTime(), true);
	request.onreadystatechange = showCommunityList;
	request.send(null);
}

function showCommunityList() {
	// 전송된 커뮤니티 목록 정보를 이용하여 Select 메뉴 생성
	if (request.readyState == 4 && request.status == 200) {
		/* Get the response from the server */
		var commList = JSON.parse(request.responseText);
		var select = document.getElementById("commSelect");
		var i;
		for (i = 0; i < commList.length; i++) {				
			var option = document.createElement("option");
			option.setAttribute("value", commList[i].id)
			var name = document.createTextNode(commList[i].name);
			option.appendChild(name);
			select.appendChild(option);			    	
		}				 
	}
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
      <form name="form" method="POST" action="<c:url value='/missing/write'/>" enctype="multipart/form-data">
  <div class="form-group">
    <label for="missingName">실종 동물 이름</label>
    <input type="text" class="form-control" name="missingName" id="missingName"
    placeholder="실종 동물 이름을 입력하세요" size="10" <c:if test="${postingFailed}">value="${missing.missingName}"</c:if>>
  </div>
  <div class="form-group">
    <label for="missingType">종</label>
    <input type="text" class="form-control" name="missingType" id="missingType"
    placeholder="실종 동물 종을 입력하세요" size="15" <c:if test="${postingFailed}">value="${missing.missingType}"</c:if>>
  </div>
   <div class="form-group">
    <label for="missingAddr">실종 장소</label>
    <input type="text" class="form-control" name="missingAddr" id="missingAddr"
    placeholder="동물이 실종된 장소를 입력하세요" <c:if test="${postingFailed}">value="${missing.missingAddr}"</c:if>>
  </div>
   <div class="form-group">
    <label for="missingDate">실종 날짜(ex.yy/mm/dd)</label>
    <input type="text" class="form-control" name="missingDate" id="missingDate"
    placeholder="실종된 날짜를 입력하세요" size="10" <c:if test="${postingFailed}">value="${missing.missingDate}"</c:if>>
  </div>
   <div class="form-group">
    <label for="missingDetail">특이 사항</label>
    <input type="text" class="form-control" name="missingDetail" id="missingDetail"
    placeholder="특이 사항을 입력하세요" <c:if test="${postingFailed}">value="${missing.missingDetail}"</c:if>>
  </div>
  <div class="form-group">
    <label for="exampleInputFile">파일 업로드</label>
    <input type="file" name="missingImg" id="missingImg">
    <p class="help-block">실종 동물의 사진을 업로드 해주세요.</p>
  </div>
  <button type="submit" class="btn btn-default" onClick="missingCreate()">등록</button>
</form>
	  </div>
  </body>
</html>