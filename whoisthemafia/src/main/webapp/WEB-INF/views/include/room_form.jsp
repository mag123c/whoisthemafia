<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<form action="room" method="post" name="room">
	<!-- roomcreate start -->
	<div class="room_con_create">
	    <a href="#" id="x_btn">X</a>
	    <div class="room_con_create_id">
	        <span>방제</span><br><input type="text" name="rname" id="roomname" placeholder="20자 이하 입력">
	    </div>
	    <div class="room_con_create_pw">
	        <span>PW</span><br><input type="password" name="pw" id="roompw" placeholder="선택사항" autocomplete="off">
	    </div>
	    <div class="room_con_create_button">
	        <button type="button" id="create_room">CREATE</button>
	    </div>
	</div>
	<!-- roomcreate end -->
</form>
</body>
</html>