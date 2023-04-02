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

	ws.onmessage = function(message) {
		let rdata = message.data;
		let msg = rdata.split("/")[0];
		let idx = rdata.split("/")[1];
		/*1. update(인원 +-)*/
		if(msg == 'update'){
			let pm = rdata.split("/")[2];
			let room = $("[data-idx='"+idx+"']");
			let h1_people = (room.find(".r_people")[0]);		
			let count = h1_people.textContent.substring(0, h1_people.textContent.indexOf(" "));
			
			if(pm == "+") {
				count++;
			}
			else {
				count--;
				ws.send("--/"+idx);
			}
			h1_people.innerText = count + " / 8";
		}
		else if(msg == "deleteroom"){
			let room = $("[data-idx='"+idx+"']");
			room.remove();
		}
		/*3. 처음 lobby입장 시 전체 update*/
		else{
			let idx = rdata.split("/")[1];
			let rname = rdata.split("/")[2];
			let pw = rdata.split("/")[3];		
			let room = roomFormat.cloneNode(true);
			let h1_name = document.createElement("h1");
			let h1_people = document.createElement("h1");
			h1_name.className = "r_name";
			h1_people.className = "r_people";
			h1_name.innerText = "("+idx+")"+rname;
			h1_people.innerText = rdata.split("/")[4] + " / 8";
			room.children[0].append(h1_name);      
			room.children[0].append(h1_people);
			room.dataset.idx = idx;
			roomCon.prepend(room);									 //query desc가 아닌 prepend사용
			room.style.display = "block";			
			roomname.value = "";
		}

	};

	ws.onclose = function() {
		console.log("close");
	};

};
/* SockJS end */