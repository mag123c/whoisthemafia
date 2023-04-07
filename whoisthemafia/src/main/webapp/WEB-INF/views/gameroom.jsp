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

<div class="container">
  <div class="user-area">
    <div class="user">
      <div class="image"></div>
      <div class="id">User 1</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 2</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 3</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 4</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 5</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 6</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 7</div>
    </div>
    <div class="user">
      <div class="image"></div>
      <div class="id">User 8</div>
    </div>
  </div>
  <div class="chat-area">
    <div class="chat-box">
      <div class="chat-log"></div>
      <div class="chat-input">
        <input type="text" placeholder="Type your message here">
        <button class="btn">Send</button>
      </div>
    </div>
    <div class="ready-btn">
      <button class="btn">Ready</button>
    </div>
  </div>
  <div class="exit-btn">
    <button class="btn">Exit</button>
  </div>
</div>



<script src="/resources/js/common/room_socket.js"></script>
</body>
</html>