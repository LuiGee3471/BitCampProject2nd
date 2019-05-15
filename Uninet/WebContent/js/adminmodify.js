$(document).ready(function() {
	$('#updateBtn').click(function(event) {
		const phoneRegExp = /^010\d{4}\d{4}$/;
		if(!$('#phoneNumber').val()){
			alert('전화번호를 입력해주세요');
			event.preventDefault();
		}else if(!$('#phoneNumber').val().match(phoneRegExp)){
			alert('올바른 전화번호가 아닙니다.')
			event.preventDefault();
		}
	})
	console.log($('#totalAdmin checkbox'));
	console.log($('#adminCheck').val());
	const adminCheck = $('#adminCheck').val();
	$("#citeAdmin input").each(function(index, element){
		if($(element).attr("value")==adminCheck){
			$(element).attr('checked',true);
		}
	});
	const deptCheck = $('#deptCheck').val();
	$("#deptAdmin input").each(function(index, element){
		if($(element).attr("value")==deptCheck){
			$(element).attr('checked',true);
		}
	});
	
});