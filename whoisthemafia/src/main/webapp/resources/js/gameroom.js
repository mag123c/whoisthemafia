const exit_btn = document.querySelector(".exit_btn");				//exit btn
const ready_btn = document.querySelector(".ready_btn");				//ready btn
const send_btn = document.querySelector(".send_btn");				//send btn
const sessionid = document.querySelector("#sessionid");				//sessionID
const chatinput = document.querySelector('#chatinput');				//채팅 input

exit_btn.addEventListener("click", function(){
	$.ajax({
		url : '/rooms/'+roomIdx.value,
		method : 'put',
		data : {"id" : sessionid.value, "idx" : roomIdx.value},
		contentType : 'application/json; charset=utf-8',
		success : function(){
			location.assign("/lobby");
		}
	})
	
});

send_btn.addEventListener("click", function(){
	if(chatinput.value.length==0 || chatinput.value.indexOf('0')==" "){
		alert("채팅을 입력해주세요");
		return;
	}
	
	$.ajax({
		url : '/rooms/'+roomIdx.value+'/chat',
		method : 'put',
		data : JSON.stringify({"user_id" : sessionid.value, "room_idx" : roomIdx.value, "msg" : chatinput.value}),
		contentType : 'application/json; charset=utf-8',
		dataType : 'text',
		success : function(data){
			chatinput.value = "";
			sock.send(data);
		}
	})
});