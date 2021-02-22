<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS, JS -->
<link href="../resources/css/jsdelivr.bootstrap.min.css" rel="stylesheet" />
<link href="../resources/css/reply.css.css" rel="stylesheet" />
<link href="../resources/css/createForm.css" rel="stylesheet" />
<link href="../resources/css/counselForm.css" rel="stylesheet" />
<script type="../resources/js/netdna.bootstrap.min.js"></script>
<script src="../resources/js/jsdelivr.bootstrap.bundle.min.js"></script>
<script src="../resources/js/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>Counsel</title>
<style type="text/css">
body {
	color: #404E67;
	background: #F5F7FA;
	font-family: 'Open Sans', sans-serif;
}

.table-wrapper {
	width: 900px;
	height: 800px;
	margin: 10px auto;
	background: #fff;
	padding: 20px;
	box-shadow: 0 1px 1px rgba(0, 0, 0, .05);
}

.title_input {
	position: relative;
	text-align: left;
	border: none;
	margin: 0 auto;
	outline: none;
	width: 100%
}

.btn-light {
	width: 70px;
	font-size: 15px;
}

.button_location {
	position: relative;
	text-align: left;
}
</style>
</head>
<body>
	<div class="container">
		<div class="table-wrapper">
			<form id="content_form" action="/counsel/create" method="post">
				<div class="wrap">
					<div class="wrap_header">
						<h5 class="mb-3" style="text-align: left">
							<input class="title_input" id="counsel_title" name="counsel_title" type="text" placeholder="제목">
						</h5>
					</div>
					<hr size="5">
					<div>
						<textarea class="form-control" id="counsel_content" name="counsel_content" cols="10" rows="20" placeholder="내용을 입력하세요"></textarea>
					</div>
					<br>
					<!-- 비밀글 -->
					<div style="text-align: left;">
						<input type="checkbox" value="비밀글" id="secret"> <label for="sport">비밀글</label>
					</div>
					<div class="input-group input-group-sm mt-6" id="secretPwd" style="width: 300px; margin-top: 20px; display: none">
						<div class="input-group-prepend">
							<span class="input-group-text" style="width: 95px; text-align: center; font-size: 13px;">비밀번호</span>
						</div>
						<input type="password" class="form-control" id="lockPassword" name="">
					</div>
					<br>
					<div class="button_location">
						<input type="button" class="btn btn-light" onclick="backList()" value="목록">
						<button id="content_submit" type="button" class="btn btn-info add-new" onclick="contentSubmit()">
							<i class="fa fa-plus"></i> 완료
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
<script>
	//비밀글 여부 확인
	var secretPwdchk;

	/*
		제목 미입력시, 에러 메세지 출력
	 */
	$(document).ready(function() {
		var errorMessage = '${errorMessage}';
		if (errorMessage != '') {
			alert(errorMessage);
		}
	})
	
	/*
	 * 글 전송. 비밀글 체크를 했거나(checked), 체크를 안했다면(unchecked) 전송.
	 */
	function contentSubmit(){
		check = checkSecretPwd()
		if (check == 'notchecked' || check == 'checked') {
			$('#lockPassword').attr('name', 'lockPassword')
			$('#content_form').submit()
		} else {
			alert('비밀글 여부를 확인하세요!!');
		}
	}

	/*
	 * 비밀글을 체크했지 여부 & 체크했다면 비밀번호 빈칸 체크
	 */
	function checkSecretPwd() {
		if ($('#secret').is(":checked")) {
			var pwd = document.getElementById("lockPassword").value;
			if (pwd != '') {
				return 'checked';
			} else {
				return 'checkedTextBalnk';
			}
		} else {
			return 'notchecked'
		}
	}

	/*
	 * 내용 빈칸 체크
	 */
	/* 	function checkContent() {
	 if ($('#counsel_title').val() != ''
	 && $('#counsel_content').val() != '') {
	 return true;
	 } else {
	 return false;
	 }
	 } */

	/*
	 * 게시물 목록으로 돌아가기.
	 */
	function backList(){
		location.href = "/counsel/list";
	}

	/*
	 * 비밀글 체크박스를 눌렀을 때, 비밀글 text 보여지는 이벤트
	 */
	$('#secret').change(function() {
		if ($('#secret').is(":checked")) {
			$('#secretPwd').show()
			secretPwdchk = true;
		} else {
			$('#secretPwd').hide()
			secretPwdchk = false;
		}
	})
</script>
</body>
</html>
