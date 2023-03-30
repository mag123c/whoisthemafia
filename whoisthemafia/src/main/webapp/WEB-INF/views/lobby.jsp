<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.3.0/css/all.min.css">
    <link rel="stylesheet" href="/resources/css/lobby.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>    
    <title>대기실</title>    
</head>
<body>
	<input type="hidden" value="${sessionScope.id}" id="sessionid">
    <div class="main_con">
        <div class="header_con">
            <h1>Room List</h1>
        </div>        
        <div class="header_btn_con">
            <button type="button" class="roombtn">CREATE</button>
        </div>
        
        <%@ include file="/WEB-INF/views/include/room_form.jsp" %>
        
        <div class="room_con">
            <div class="room">
                <div class="room_text">
                </div>
                <button type="button" class="joinbtn">JOIN</button>
            </div>            
        </div>
    </div>
    <script src="/resources/js/lobby.js"></script>
    <script src="/resources/js/common/lobby_socket.js"></script>
</body>
</html>