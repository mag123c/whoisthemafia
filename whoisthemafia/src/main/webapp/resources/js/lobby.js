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
		roomModalCon.style.display = "none";
		document.body.style.backgroundColor = "rgba(36, 41, 45, 1)";
		let rname = roomname.value;
		let pw = roompw.value;
		let room = roomFormat.cloneNode(true);
		let h1_name = document.createElement("h1");
		let h1_people = document.createElement("h1");
		h1_name.innerText = rname;
		h1_people.innerText = "1 / 8";
		room.children[0].append(h1_name);		
		room.children[0].append(h1_people);
		roomCon.append(room);
		room.style.display = "block";		
		roomname.value = "";
		document.room.submit();
	}
})
/* create room end */