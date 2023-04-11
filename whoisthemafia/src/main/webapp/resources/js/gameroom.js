const exit_btn = document.querySelector(".exit_btn");				//exit btn
const ready_btn = document.querySelector(".ready_btn");				//ready btn
const send_btn = document.querySelector(".send_btn");				//send btn
const sessionid = document.querySelector("#sessionid");				//sessionID
const sessionnick = document.querySelector("#sessionnick");			//sessionNick
const chatinput = document.querySelector('#chatinput');				//채팅 input
const main_Con = document.querySelector(".main_Con");				//mainCon

/* exit */
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
/* exit end */

/* send */
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
/* send end */

/* ready */
ready_btn.addEventListener("click", function(){
	let divid = find_divid();
	let pm;
	if(ready_btn.className.includes("done")){
		sock.send("start!!!");
	}
	
	else{
		ready_btn.classList.toggle("done");
		ready_btn.textContent="Done";
		pm = "+";
	}
	sock.send("readyStatus/"+pm+"/"+divid);
})
/* ready end */

/* ready users div id */
function find_divid(){
	let pAll = $(main_Con).find('.user_nickname');
	let userp;
	for(var i=0; i<pAll.length; i++){
		if(pAll[i].textContent == sessionnick.value){
			userp = pAll[i];
			break;
		}
	}
	return userp.parentElement.parentElement.id;
}
/* ready users div id end */
