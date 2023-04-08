const hsid = document.querySelector('#sessionid');		//세션id
const roomIdx = document.querySelector("#roomIdx");		//방 idx
const id = document.querySelector("#sessionid");		//세션 id
const userAll = document.querySelectorAll(".user")		//user div 전체
var sock = null;
var idx = 0;
var imgArr =
	["https://item.kakaocdn.net/do/58119590d6204ebd70e97763ca933baff604e7b0e6900f9ac53a43965300eb9a",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsz0scKgLjtniQ2lUFOPG506o1T5TmWebsqaowtkbmu01glp4pe_m0971ZLY7a2nHS108&usqp=CAU",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS76AX-J8OqxodWRvV-7O0gQE9zB89f2LW_jRxPuWX6g6U9i9nZn0bNPi-5_lq4dnYR_kw&usqp=CAU",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJh4qGtIy7IWElX1d2kWUCWiRHhj5BArVEuFOozhKY0untrtfLET1kfRL7fHBSPRUyEJI&usqp=CAU",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4D1WnaCNP9tjG18CD65YO6rPAb5Sa2Lnxmu8-uE49L1tHXxvaL-EmBqqkAf2Ao9D-LBM&usqp=CAU",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQB0Vtk8FIvCD7bY-jgcbArExywIOaECAU6Xi-1yJn6EpdfpP5Re0msAS0pxJa9Omj56pg&usqp=CAU",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ8p82KLnI5jqDkWzEhyebgLf2P52ZSNhEVDXp9M0bfV3G2X8Mg9xunxkE56RwQVDn6I7w&usqp=CAU",
	"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjK6S_i8RzBO6w16GjVQ9_DLVw8MCAFFJRBMZqBjMm4-O1nbQqho2AklAKgy445Vth7PM&usqp=CAU"];


/* SockJS */
$(document).ready(function(){
	connectWs();

});

function connectWs(){
	var ws = new SockJS("http://localhost:8080/room-ws");
	sock = ws;

	ws.onopen = function() {
		console.log("게임open");
		ws.send("join/"+roomIdx.value+"/"+id.value);
	};

	ws.onmessage = function(message) {
		let data = message.data;
		let nickname = data.split("/")[0];
		let img = data.split("/")[1];
		if(img=="null"){
			img = getranimg();
			idx++;
			idxclear();
		}
		
		userintodiv(nickname, img);
		
	};

	ws.onclose = function() {
		console.log("게임close");
	};

};
/* SockJS end */

/* randomimg func */
function getranimg(){
	return imgArr[idx];
}

function idxclear(){
	if(idx==8) idx=0;
}
/* randomimg func end */

/* div -> user put */
function userintodiv(nickname, img){
	let userdiv;
	for(var i=0; i<userAll.length; i++){
		userdiv = userAll[i];
		let div_img = userAll[i].querySelector('.user_img');			
		if(div_img.src=="") {
			break;
		}
	}	
	userdiv.querySelector('.user_img').src = img;
	userdiv.querySelector('.user_nickname').textContent = nickname;	
}
/* div -> user put end */