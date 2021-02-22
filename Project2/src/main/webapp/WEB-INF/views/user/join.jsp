<!-- https://www.tutorialrepublic.com/codelab.php?topic=bootstrap&file=simple-join-form -->
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>counsel</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<style>
.join-form {
	width: 340px;
	margin: 50px auto;
	font-size: 15px;
}

.join-form form {
	margin-bottom: 15px;
	background: #f7f7f7;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	padding: 30px;
}

.join-form h2 {
	margin: 0 0 15px;
}

.form-control, .btn {
	min-height: 38px;
	border-radius: 2px;
}

.btn {
	font-size: 15px;
	font-weight: bold;
}
</style>
</head>
<body>
	<div class="join-form">
		<form action="/counsel/user/join" method="post" id="joinForm">
			<h2 class="text-center">회원가입</h2>
			<div class="form-group">
				<input type="text" id="id" name="id" class="form-control" placeholder="아이디를 입력하세요" required="required">
				<span class="error_msg" id="idMsg" style="display: none"></span>				
			</div>
			<div class="form-group">
				<input type="password" id="password" name="password" class="form-control" placeholder="비밀번호를 입력하세요" required="required">
				<span class="error_msg" id="pwdMsg" style="display: none"></span>
			</div>
			<div class="form-group">
				<input type="password" id="passwordcheck" class="form-control" placeholder="비밀번호를 한번 더 입력하세요" required="required">
				<span class="error_msg" id="pwdchkMsg" style="display: none"></span>
			</div>
			<div class="form-group">
				<button type="button" onclick="joinFormSubmit()" class="btn btn-primary btn-block">확인</button>
				<button type="button" onclick="backIndex()" class="btn btn-primary btn-block">취소</button>
			</div>
		</form>
	</div>
</body>
<script>
	var idChk = false; 	//id 유효성 체크
	var pwdChk = false;	//비밀번호 유효성 체크

	/*
	* 각 text들이 값이 바뀔때마다 실행.
	*/
	$(document).ready(function() {
		$("#id").blur(function() {
			checkId();
		});
		$("#password").blur(function() {
			checkPasswd();
		});
		$("#passwordcheck").blur(function() {
			checkPasswd();
		});
		$("#id").change(function() {
			idChk = false;
		});
		$("#password").change(function() {
			pwdChk = false;
		});
		$("#passwordcheck").change(function() {
			pwdChk = false;
		});
	});

	//id 유효성 체크
	function checkId(event) {
		var id = $('#id').val();
		var idMsg = $('#idMsg');

		if (id == '') {
			showErrorMsg(idMsg, "필수 정보입니다");
			return false;
		}		

		//id 중복확인
		$.ajax({
			type : 'GET',
			url : "/counsel/user/idCheck?id=" + id,
			success : function(data) {
				if (data == 'yes') {
					showSuccessMsg(idMsg, "사용가능한 아이디입니다.");
					idChk = true;
				} else {
					showErrorMsg(idMsg, "이미 사용중인 아이디입니다.");
					idChk = false;
					return false;
				}
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
				alert(request.responseText);
				return false;
			}
		});
		return true;
	}
	
	// password 확인
	function checkPasswd() {
		var pw = $("#password").val();
		var passwdchk = $("#passwordcheck").val();
		var pwMsg = $("#pwdMsg");
		var pwchkMsg = $("#pwdchkMsg")

		if (pw == "") {
			showErrorMsg(pwMsg, "필수정보입니다")
			return false;
		}

		if (pw == passwdchk) {
			showSuccessMsg(pwchkMsg, "비밀번호가 같습니다.");
			showSuccessMsg(pwMsg, "");

			pwdChk = true;
			return true;
		} else {
			showErrorMsg(pwchkMsg, "비밀번호가 일치하지 않습니다.")
			showErrorMsg(pwMsg, "");

			return false;
		}
	}

	/*
	*  회원가입 완료.
	*/
	function joinFormSubmit(){
		if (idChk && pwdChk) {
			$('#joinForm').submit();
		}
	}

	/*
	* 	보여질 에러 메세지
	*/
	function showErrorMsg(obj, msg) {
		obj.attr("class", "error_msg");
		obj.html(msg);
		obj.show();
	}
	
	/*
	* 보여질 성공 메세지
	*/
	function showSuccessMsg(obj, msg) {
		obj.attr("class", "error_msg green");
		obj.html(msg);
		obj.show();
	}

	/*
	*	index로 돌아가기
	*/
	function backIndex(){
		location.href="/"
	}
</script>
</html>