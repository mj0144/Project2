<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap core CSS, JS -->
<link href="../resources/css/jsdelivr.bootstrap.min.css" rel="stylesheet" />
<link href="../resources/css/reply.css.css" rel="stylesheet" />
<link href="../resources/css/counselForm.css" rel="stylesheet" />
<script type="../resources/js/netdna.bootstrap.min.js"></script>
<script src="../resources/js/jsdelivr.bootstrap.bundle.min.js"></script>
<script src="../resources/js/jquery-3.5.1.min.js"></script>
<meta charset="UTF-8">
<title>Counsel</title>
<style>
.form-control[readonly] {
	background-color: white;
	opacity: 1;
}

.btn-light {
	width: 70px;
	font-size: 15px;
}

.button_location {
	position: relative;
	margin: 0 auto;
	text-align: right;
}
</style>
</head>
<body>
	<div class="container">
		<div class="wrap">
			<div class="wrap_header">
				<h4 class="mb-3" style="width: 100%">${counselInfo.counsel_title }</h4>
				<div>
					<div style="text-align: left;">${counselInfo.counsel_date }</div>
				</div>
				<div>
					<div style="text-align: left;">작성자 : ${counselInfo.counsel_userId }</div>
				</div>
			</div>
			<hr size="5">
			<div>
				<textarea class="form-control" id="exampleFormControlTextarea1" cols="10" rows="20" placeholder="내용을 입력하세요" readonly="readonly">${counselInfo.counsel_content }</textarea>
			</div>
			<div class="row">
				<div class="panel panel-default widget">
					<div class="CommentBox">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-comment"></span>
							<h5 class="panel-title">댓글</h5>
						</div>
						<br>
						<!-- 댓글 목록 -->
						<div class="panel-body">
							<ul class="list-group" id="replyList"></ul>
						</div>
						<div class="col">
							<div class="panel-body">
								<div class="CommentWriter">
									<form role="form" class="form-group">
										<!-- 댓글 작성 -->
										<strong data-v-79bd409c="" class="blind">댓글을 입력하세요</strong>
										<textarea data-v-79bd409c="" id="reply_content" name="reply_content" placeholder="댓글을 남겨보세요" rows="1" class="comment_inbox_text" style="overflow: hidden; overflow-wrap: break-word; height: 18px;"></textarea>
										<div data-v-79bd409c="" class="register_box">
											<a data-v-79bd409c="" onclick="replySubmit()" id="reply_submit" role="button" class="button btn_register">등록</a>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="button_location">
					<input type="button" class="btn btn-light" onclick="backList()" value="목록">
				</div>
			</div>
		</div>
	</div>
</body>
<script>

	//페이지 로딩 시, 댓글 리스트업
	$(document).ready(function(){
		replyContentFormAdd();
	})

	/*
	 * 댓글 전송.
	 */
	function replySubmit(){
		//내용 비어있는지 체크
		check = checkContent();

		if(check == true){
			replyCreate();
		}else{
			alert('댓글 내용을 입력해주세요!!');
		}
	}
		
	/*
	* 게시물 목록으로 돌아가기.
	*/
	function backList(){
		location.href="/counsel/list";
	}
	
	/*
	* 댓글 목록 리스트 업
	*/
	function replyContentFormAdd(){
		var seq_counsel = ${counselInfo.seq_counsel}
		$.ajax({
			url : '/reply/list',
			type : 'post',
			dataType: 'json',
			contentType : 'application/json',
			data : JSON.stringify(seq_counsel),
			success : function(data){
				var html = "";
				for(i=0; i<data.length; i++){
					html += "<li class='list-group-item' >";
					html += "<div class='row'>";
					html += "<div class='col-xs-10 col-md-11' style='text-align: center; margin: 0 auto;'>"
					html += "<div><div class='mic-info'>작성일 :" + data[i].reply_date + "</div></div>"
					html += "<div class='comment-text'>" + data[i].reply_content + "</div></div></div></li>"
				}
				$('#replyList').html(html);		

				//입력한 내용 초기화
				$('#reply_content').val('')			
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
			}
		})
	}
	
	/*
	* 댓글 내용이 비었는지 확인.
	*/
	function checkContent(){
		if($('#reply_content').val() == ''){
			return false;
		}else{
			return true;
		}	
	}

	/*
	* 댓글 등록
	*/
	function replyCreate(){
		//입력한 댓글 내용
		var reply_content = $('#reply_content').val()
		//해당 게시글 번호
		var seq_counsel = ${counselInfo.seq_counsel}

		$.ajax({
			url : '/reply/create',
			type : 'POST',
			data : {'reply_content' : reply_content, 'seq_counsel' : seq_counsel},
			success : function(){
				replyContentFormAdd(seq_counsel);
			},
			error : function(error, status){
				console.log('status : ' + status)
			}			
		})
	}
</script>
</html>
