<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="UTF-8"%>
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
		function missingRemove(id, writer) {
			var vid = id;
			var vwriter = writer;
    		if(vid != vwriter) {
    			alert("자신의 글만 삭제할 수 있습니다.");
    			return false;
    		}
			return confirm("${missing.missingName}은{는} 실종동물목록에서 삭제됩니다.");		
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
        	<span style="color:white">${clientName} 후원자님 안녕하세요!</span>
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
      <form>
  <div class="form-group">
    <label for="inputName">실종 동물 이름</label>
    <br/>
    ${missing.missingName}
  </div>
  <div class="form-group">
    <label for="inputType">종</label>
    <br/>
    ${missing.missingType}
  </div>
   <div class="form-group">
    <label for="inputAddr">실종 장소</label>
    <br/>
    ${missing.missingAddr}
  </div>
   <div class="form-group">
    <label for="inputDate">실종 날짜</label>
    <br/>
    ${missing.missingDate}
  </div>
   <div class="form-group">
    <label for="inputDetail">특이 사항</label>
    <br/>
    ${missing.missingDetail}
  </div>
  <div class="form-group">
    <label for="exampleInputFile">실종동물 사진</label>
    <br/>
    <img src="${pageContext.request.contextPath}/images/missing/${missing.missingImgPath}" width="200px" height="250px">
  </div>
  <a href="<c:url value='/missing/list'>
  				<c:param name='page' value='${currentPage}'/>
  			</c:url>" class="btn btn-default">목록 보기</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <a href="<c:url value='/missing/delete'>
  				<c:param name='page' value='${currentPage}'/>
  				<c:param name='postId' value='${missing.postId}'/>
  				<c:param name='writerId' value='${missing.clientId}'/>
  			</c:url>" class="btn btn-info" onclick="return missingRemove('${client.clientID}', '${missing.clientId}');">${missing.missingName}은(는) 집으로 돌아왔어요!</a>
</form>
	  </div>
  </body>
</html>