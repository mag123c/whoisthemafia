<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script> 
<link href="/resources/css/gameroom.css" rel="stylesheet"></link>   
</head>
<body>
<!-- hidden info -->
<input type="hidden" id="roomIdx" value="${room_idx}">
<input type="hidden" value="${sessionScope.id}" id="sessionid">
<input type="hidden" value="${sessionScope.nickname}" id="sessionnick">
<!-- hidden info end -->
	
<div class="main_Con">
	<!-- left(User) -->
	<div class="left_Con">
		<div class="user" id="1">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
		
		<div class="user" id="2">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
		
		<div class="user" id="3">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
		
		<div class="user" id="4">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
	</div>
	<!-- left(User) end -->
	<!-- mid(chat) -->
	<div class="mid_Con">
		<h1 class="rname">방제목 나중에 여기 나오게하기</h1>
		<button class="ready_btn">Ready</button>
		<button class="exit_btn">Exit</button>
		<div class="chat">
			<div class="chat_view">
				<!-- 채팅영역 -->
			</div>
			<div class="chat_input">
				<input type="text" id="chatinput" spellcheck="false" placeholder="메세지 입력">
				<button class="send_btn">Send</button>
			</div>
		</div>
	</div>	
	<!-- mid(chat) end -->
	<!-- right(User) -->
	<div class="right_Con">
		<div class="user" id="5">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
		
		<div class="user" id="6">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
		
		<div class="user" id="7">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
		
		<div class="user" id="8">
			<div class="user_imgCon">
				<img class="user_img">
			</div>			
			<div class="user_idCon">
				<p class="user_nickname"></p>
			</div>						
		</div>
	</div>
	<!-- right(User) end -->

</div>
<script src="/resources/js/common/room_socket.js"></script>
<script src="/resources/js/gameroom.js"></script>
</body>
</html>
