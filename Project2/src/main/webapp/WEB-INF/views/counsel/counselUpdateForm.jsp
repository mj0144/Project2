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
	height: 700px;
	margin: 10px auto;
	background: #fff;
	padding: 20px;
	box-shdow: 0 1px 1px rgba(0, 0, 0, .05);
}

.title_input {
	position: relative;
	text-align: left;
	border: none;
	margin: 0 auto;
	outline: none;
	width : 100%
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
	<div class="continer">
		<div class="table-wrapper">
			<form id="content_form" action="${counselInfo.seq_counsel }" method="post">
				<div class="wrap">
					<div class="wrap_header">
						<h5 class="mb-3" style="text-align: left">
							<input class="title_input" id="counsel_title" name="counsel_title" type="text" value="${counselInfo.counsel_title }" placeholder="제목">
						</h5>
					</div>
					<hr size="5">
					<div>
						<textarea class="form-control" id="counsel_content" name="counsel_content" cols="10" rows="20" placeholder="내용을 입력하세요">${counselInfo.counsel_content }</textarea>
					</div>
					<br>
					<div class="button_location">
						<input type="button" class="btn btn-light" onclick="backList()" value="목록">
						<button id="content_submit" onclick="contentSubmit()" type="button" class="btn btn-info add-new">
							<i class="fa fa-plus"></i> 완료
						</button>
					</div>					
				</div>
				<input type="hidden" name="counsel_userId" value="${counselInfo.counsel_userId }">
			</form>
		</div>
	</div>
</body>
<script>
	/*
	 * 글 전송
	 */
	function contentSubmit() {
		check = checkContent()
		if (check == true) {
			$('#content_form').submit()
		} else {
			alert('내용을 입력하세요!!');
		}
	}

	/*
	 * 내용 빈칸 체크
	 */
 	function checkContent() {
		if ($('#counsel_title').val() != ''
				&& $('#counsel_content').val() != '') {
			return true;
		} else {
			return false;
		}
	} 

	/*
	 * 게시물 목록으로 돌아가기.
	 */
	function backList() {
		location.href = "/counsel/list";
	}
</script>
</html>
