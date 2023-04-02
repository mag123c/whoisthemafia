const hsid = document.querySelector('#sessionid');		//세션id
const roomIdx = document.querySelector("#roomIdx");		//방 idx
var sock = null;
/* SockJS */
$(document).ready(function(){
	connectWs();

});

function connectWs(){
	var ws = new SockJS("http://localhost:8080/room-ws");
	sock = ws;

	ws.onopen = function() {
		console.log("게임open");
		ws.send("join/"+roomIdx.value);
	};

	ws.onmessage = function(event) {

	};

	ws.onclose = function() {
		console.log("게임close");
	};

};
/* SockJS end */