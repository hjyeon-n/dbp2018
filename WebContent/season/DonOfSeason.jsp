<%@page contentType="text/html; charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<title>키다리 집사</title>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="${pageContext.request.contextPath}/bootstrap/img/paw.ico">

    <title>Don Of Season</title>

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
<style>
		.img {
			margin-left: auto;
			margin-right: auto;
			left: 0;
			right: 40;
		}
    	.first {	
    	text-align:justify;
    	margin-top: 50px;
    	margin-right: 100px;
    	
    	}
    	.second {	
    	text-align:justify; 
    	margin-top: 50px;
    	margin-right: 400px;
    	}
    	.third {	
    	text-align:justify; 
    	margin-top: 20px;
    	margin-left: 200px;
    	}
    	tr {height: 40;}
    	th {width: 20%;}
    </style>
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
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/logout'></c:url>'">Logout</button>
        	<button type="button" class="btn btn-success" onClick="location.href='<c:url value='/user/mypage'></c:url>'">MyPage</button>
        </c:if>
         </form>
        </div><!--/.navbar-collapse -->
      </div>
    </nav>
    
	<br><br>
	<!-- 마이페이지 -->
	<div align="center">
	 <img class="img" src = "${pageContext.request.contextPath}/bootstrap/img/hallofdon.jpg" style="position: absolute; z-index: -9; align: center; opacity:0.6; padding:30px 15px; margin-top:50px; margin-bottom:10px;"/>
	 <img class="img" src = "${pageContext.request.contextPath}/bootstrap/img/hallofdon2.png" style="position: absolute; z-index: -6; opacity:0.6; padding:30px 15px; margin-top:50px; margin-bottom:10px;"/>
	<div class="input-group">
       <div class="table">
 	<table class="first">
 		<tr>
 			<td><h1>1위&nbsp;&nbsp;</h1></td>
 			<td><h1>${top1}</h1></td>
 		</tr>
 	</table>
 	<table class="second">
 		<tr>
 			<td><h2>2위&nbsp;&nbsp;</h2></td>
 			<td><h2>${top2}</h2></td>
 		</tr>
 	</table>
 	<table class="third">
 		<tr>
 			<td><h3>3위&nbsp;&nbsp;</h3></td>
 			<td><h3>${top3}</h3></td>
 		</tr>
 	</table>
 	</div>
    </div>
     <!-- /container -->
	</div>
</body>
</html>