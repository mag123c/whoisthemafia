var sock = null;
const hsid = document.querySelector('#sessionid');				//hsid

/* SockJS */
$(document).ready(function(){
	connectWs();

});

function connectWs(){
	var ws = new SockJS("http://localhost:8080/lobby-ws");
	sock = ws;

	ws.onopen = function() {
		console.log("open");
		ws.send(hsid.value);
	};

	ws.onmessage = function(event) {
		console.log("message");
	};

	ws.onclose = function() {
		console.log("close");
	};

};
/* SockJS end */