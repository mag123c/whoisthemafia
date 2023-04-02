<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>    
<title>Game Room</title>
</head>
<body>
	<input type="hidden" id="roomIdx" value="${idx}">
	<input type="hidden" value="${sessionScope.id}" id="sessionid">	
	<h1>하이</h1>	
	<script src="/resources/js/common/room_socket.js"></script>
</body>
</html>