const regiBtn = document.querySelector("#login_register") 			//회원가입 창 버튼
const createBtn = document.querySelector("#create_user") 			//가입 처리하는 버튼
const regiCon = document.querySelector(".register_con") 			//가입 container
const xBtn = document.querySelector("#x_btn") 						//모달 x버튼
const loginBton = document.querySelector("#loginbtn");
const id = document.querySelector("#regi_id") 						//가입 id val
const nickname = document.querySelector("#regi_nickname") 		    //가입 nickname val
const pw = document.querySelector("#regi_pw") 						//가입 pw val
const regiConId = document.querySelector(".regi_con_id")			//가입 id 컨테이너
const regiConNick = document.querySelector(".regi_con_nickname")	//가입 nickname 컨테이너
const regiConPw = document.querySelector(".regi_con_pw")			//가입 pw 컨테이너

/* modal */
regiBtn.addEventListener("click", function(){
    regiCon.style.display = "block";
})

xBtn.addEventListener("click", function(){
    regiCon.style.display = "none";
})
/* modal end */

/* register with regExp */
createBtn.addEventListener("click", function(){
	for(let i=0; i<regiCon.children.length; i++){
		if(regiCon.children[i].tagName == "DIV" && regiCon.children[i].lastElementChild.tagName == "P"){
			regiCon.children[i].removeChild(regiCon.children[i].lastElementChild);
		}
	}
	if(!idnickExp(id.value)){
		let p = document.createElement("p");
		p.innerText = "8~20자 사이의 영문,숫자를 입력 해 주세요(공백X)";
		regiConId.append(p);
		return;
	}
	else if(!idnickExp(nickname.value)){
		let p = document.createElement("p");
		p.innerText = "8~20자 사이의 영문,숫자를 입력 해 주세요(공백X)";
		regiConNick.append(p);
		return;
	}
	else if(!pwExp(pw.value)){
		let p = document.createElement("p");
		p.innerText = "8~20자 사이의 영문,숫자,특수문자를 조합하여 입력 해 주세요(공백X)";
		regiConPw.append(p);
		return;
	}
	else {
		$.ajax({
			url : "/users",
			method : "post",
			data : {"id" : id.value, "nickname" : nickname.value, "pw" : pw.value},
			dataType : "text",
			success : function(msg){
				if(msg=="success"){
					alert("가입 성공");
				    regiCon.style.display = "none";
				}
				else{
					let p = document.createElement("p");
					if(msg.indexOf("ID")>=0){
						p.innerText = "이미 사용중인 ID입니다.";
						regiConId.append(p);
					}
					else if(msg.indexOf("NickName")>=0){
						p.innerText = "이미 사용중인 닉네임입니다.";
						regiConNick.append(p);
					}
					else{
						alert("가입 실패");
					}
				}
			}		
		})
	}
})
/* register with regExp end */

/* regExp ID(nickname), PW */
function idnickExp(value) {
	var regExp = /^[a-zA-Z0-9-_/,.][a-zA-Z0-9-_/,. ]*$/;
    
	return regExp.test(value);
}

function pwExp(value) {
	var regExp = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+]).{8,20}$/; 
	return regExp.test(value);
}
/* regExp end */

/* login */
loginbtn.addEventListener("click", function(){
	
})
/* login end */