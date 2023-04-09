<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/home.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <title>Who is The Mafia</title>
</head>
<body>
    <!--header start-->
    <div class="header">
        <div class="header_con1">
            <img class="mafia_img" src="/resources/img/mafia.png">
            <h1>Who is the <span class="mafia">Mafia</span></h1>
        </div>
    </div>
    <!-- header end -->

    <!-- login start -->
    <form action="/users/login" method="post">
	    <div class="login_con">
	        <div class="login_con_id">
	            <span>ID</span><br><input type="text" name="id" id="login_id" value="${id}">
	        </div>
	        <div class="login_con_pw">
	            <span>PW</span><br><input type="password" name="pw" id="login_pw" autocomplete="off">
	        </div>
	        <div class="login_con_button">
	            <button type="submit" id="loginbtn">LOGIN</button>
	            <button type="button" id="login_register">REGISTER</button>
	        </div>
	        <div class="login_msg">
		        <c:if test="${message=='login_error'}">
		        	<p>로그인 실패. ID,PW를 확인 해 주세요</p>
		        </c:if>
	        </div>
	    </div>
    </form>
    <!-- login end -->

    <!-- signup start -->
	<%@ include file="/WEB-INF/views/include/register_form.jsp" %>
    <!-- signup end -->
    <script src="/resources/js/home.js"></script>
</body>
</html>
