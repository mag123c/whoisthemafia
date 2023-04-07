const createModalBtn = document.querySelector('.roombtn');			//모달생성 버튼
const xBtn = document.querySelector("#x_btn");						//x버튼
const createRoomBtn = document.querySelector("#create_room");		//방 생성버튼
const roomModalCon = document.querySelector(".room_con_create");	//모달 창
const mainCon = document.querySelector(".main_con");				//전체 컨테이너
const roomCon = document.querySelector(".room_con");				//방 전체 컨테이너
const roomFormat = document.querySelector(".room");					//방 형태 = cloneNode활용
const roomname = document.querySelector("#roomname");				//방제목 input val
const roompw = document.querySelector("#roompw");					//비번 input val

/* modal */
createModalBtn.addEventListener("click", function(){
	document.body.style.backgroundColor = "rgba(0, 0, 0, 0.5)";
	roomModalCon.style.display = "block";
})

xBtn.addEventListener("click", function(){
	document.body.style.backgroundColor = "rgba(36, 41, 45, 1)";
	roomModalCon.style.display = "none";
})
/* modal end */

/* create room start */
createRoomBtn.addEventListener("click", function(){
	if(roomname.value.length == 0 || roomname.value.charAt(0) == " "){
		alert("방 제목을 입력해주세요");
		return;
	}
	else {
		$.ajax({
			url : '/room',
			method : 'post',
			data : {'rname' : roomname.value, 'pw' : roompw.value},
			dataType : 'text',
			success : function(idx){
				sock.send("create/" + idx);
				joinroom(idx);
			}
		})
	}
})
/* create room end */

/* join room start */
//버튼 정보 -> 비동기처리
document.addEventListener("DOMContentLoaded", function(){
	setTimeout(function(){
		const joinBtn = document.querySelectorAll(".joinbtn");
		joinBtn.forEach(function(btn){
			btn.addEventListener("click", function(e){
				let roominfo = e.target.parentNode.children[0].children[0].textContent;
				let last = roominfo.indexOf(")");
				let idx = roominfo.substring(1, last);
				let people = btn.previousElementSibling.children[1].innerText.split(" / ")[0];
				
				if(people < 8) joinroom(idx);
				else {
					alert("풀방임");
					return;
				}
			})
		})
	}, 1000);
})

function joinroom(idx){
	$.ajax({
		url : '/room/'+idx,
		method : 'post',
		data : {'idx' : idx},
		success : function(data){
			location.href="/room/"+idx;
		}
	})
}
/* join room end */


