const exit_btn = document.querySelector(".exit_btn");				//exit btn
const ready_btn = document.querySelector(".ready_btn");				//ready btn

exit_btn.addEventListener("click", function(){
	$.ajax({
		url : '/rooms/'+roomIdx.value,
		method : 'put',
		data : {"idx" : roomIdx.value},
		success : function(){
			location.assign("/lobby");
		}
	})
	
})
