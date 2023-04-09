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
		let idx = rdata.split("/")[1];
		let msg = rdata.split("/")[0];
		/* 1. update(인원 +-)*/
		if(msg == 'update') update(rdata);
		/* 2. 방 삭제 시 */
		else if(msg == 'deleteroom') roomdel(idx);
		/* 3. 처음 lobby입장 시 전체 update*/
		else join(rdata);
	};

	ws.onclose = function() {
		console.log("close");
	};

};
/* SockJS end */

/* message func */
	/* 1. update */
	function update(rdata){
		let idx = rdata.split("/")[1];
		let pm = rdata.split("/")[2];
		let room = $("[data-idx='"+idx+"']");
		let h1_people = (room.find(".r_people")[0]);		
		let count = h1_people.textContent.substring(0, h1_people.textContent.indexOf(" "));
		console.log(pm);
		if(pm == "+") {
			count++;
		}
		else {
			count--;
		}
		if(count==0) roomdel(idx);
		h1_people.innerText = count + " / 8";
	}
	/* end */

	/* 2. join */
	function join(rdata){
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
		roomCon.prepend(room); 
		room.style.display = "block";			
		roomname.value = "";
	}
	/* end */
/* message func end */
	
/* roomdelete */
	function roomdel(idx){
		let room = $("[data-idx='"+idx+"']");
		room.remove();
	}
/* end */