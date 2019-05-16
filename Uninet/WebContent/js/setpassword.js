$(document).ready(function() {
		$('#updatePwd2').keyup(function() {
			if ($('#updatePwd2').val() != $('#updatePwd').val()) {
				$('#check').val('변경할 비밀번호와 일치하지 않습니다.');
				$('#check').css('color', 'red');
			} else {
				$('#check').val('암호가 일치합니다.');
		    	$('#check').css('color','blue');
			}
		});
		$('#updatePwd').keyup(function (){
			if($('#updatePwd').val().length <4){
				$('#firstCheck').val('4자리 이상입력해주세요.');
				$('#firstCheck').css('color','red');
			} else{
				$('#firstCheck').val('사용가능한 비밀번호입니다.');
				$('#firstCheck').css('color','blue');
			}
		});
		$('#currentPwd').keyup(function(event){
			if($('#pwd').val() != $('#currentPwd').val()){
				$('#checkPwd').val('현재 비밀번호와 일치하지 않습니다.');
				$('#checkPwd').css('color','red');
				const pwd = $('#pwd').val();
			}else{
				$('#checkPwd').val('현재 비밀번호와 일치합니다.');
				$('#checkPwd').css('color','blue');
			}
		});		
			$('#update').click(function (event){
				const length = $('#updatePwd2').val().length;
				if($('#currentPwd').val() != $('#pwd').val()){
					alert("현재 암호를 확인해주세요");
					event.preventDefault();
				}else if(length<4){
					alert('암호는 4자리 이상이어야 합니다');
					event.preventDefault();
				}else if($('#updatePwd2').val() != $('#updatePwd').val()){
					alert('재확인 비밀번호와 맞지 않습니다.');
					event.preventDefault();
				}
			})
	});