<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- https://bootsnipp.com/snippets/vNldb -- template -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="../resources/js/counselList.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link href="../resources/css/counselList.css.css" rel="stylesheet" id="bootstrap-css">
<link href="../resources/css/addUserModal.css" rel="stylesheet">
<link href="../resources/css/deleteChkModal.css" rel="stylesheet">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css">
<title>Counsel</title>
</head>
<body>
	<jsp:include page="/WEB-INF/views/comm/modal/lockModal.jsp" />
	<div class="container">
		<header class="mb-auto">
		<nav class="nav">
			<h3 class="float-md-start mb-4" style="float: left"><a onclick="backIndex()">Counsel</a></h3>		
			<div style="margin: 10px;">
				<!-- 인증된 자들. -->
				<sec:authorize access="isAuthenticated()">
					<nav class="nav" style="text-align: right; float:right;">
						<a onclick="logout()" href="#">로그아웃</a>
					</nav> 
					<sec:authorize access="hasRole('admin')">
						<nav class="nav" style="text-align: right; float: right; margin-right: 5px;">
							<a onclick="addUser()" data-target="#myModal" data-toggle="modal" href="">사용자 추가</a>
						</nav>
						<jsp:include page="/WEB-INF/views/comm/modal/addUserModal.jsp" />
					</sec:authorize>
				</sec:authorize>
			</div>
			</nav>
		</header>
		<div class="table-wrapper">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2">
					<form action="/counsel/list" name="searchForm" id="searchForm" method="post">
						<div class="input-group">
							<div class="input-group-btn search-panel" style="width: 144px">
								<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" style="width: 144px">
									<span id="search_concept">제목+내용</span> <span class="caret"></span>
								</button>
								<ul class="dropdown-menu" role="menu">
									<li><a href="" onclick="searchOption('all')">제목+내용</a></li>
									<li><a href="" onclick="searchOption('title')">제목</a></li>
									<li><a href="" onclick="searchOption('content')">내용</a></li>
								</ul>
							</div>
							<input type="hidden" name="search_option" value="all" id="search_option"> <input type="text" class="form-control" id="search_query" name="search_query" placeholder="검색어를 입력하세요!"> <span class="input-group-btn">
								<button class="btn btn-default" type="button" name="search_submit" id="search_submit" onclick="searchSubmit()">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</form>
				</div>
			</div>
			<br>
			<div class="table-title">
				<div class="row">
					<div class="col-sm-8">
						<h2>
							Counsel <b> Table</b>
							<c:if test="${search_text != null}">
								<h5>
									<b>입력한 검색어 : ${search_text }</b>
								</h5>
								<h5>
									<button type="button" class="btn btn-light" id="cansel" onclick="cansel()">검색초기화</button>
								</h5>
							</c:if>
						</h2>
					</div>
					<div class="col-sm-4">
						<button type="button" class="btn btn-info add-new" onclick="addNew()">
							<i class="fa fa-plus"></i> 글쓰기
						</button>
					</div>
				</div>
			</div>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th>action</th>
					</tr>
				</thead>
				<tbody>
					<!-- 현재 로그인 중인 username -->
					<sec:authentication var="username" property="principal" />
					<sec:authentication var="authorities" property="principal" />
					<c:forEach items="${counselList }" var="counselList">
						<tr class="counselInfo">
							<td class="seq_counsel">${counselList.seq_counsel }<!--  비밀글일 때 자물쇠 아이콘 --> <c:if test="${counselList.lockchk == true }">
									<c:set var="lockchk" value="${counselList.seq_counsel }"></c:set>
									<a class="fa fa-lock"></a>
								</c:if>
							</td>
							<td><a onclick="counselDetail(${counselList.seq_counsel}, ${counselList.lockchk })">${counselList.counsel_title }</a></td>
							<td>${counselList.counsel_userId }
							<td>${counselList.counsel_date }</td>
							<!-- 본인이 쓴글 또는 관리자일 때 수정,삭제 아이콘 보이도록. -->
							<td><c:if test="${(counselList.counsel_userId == username) or (authorities eq '[admin]')}">
									<a class="add" title="Add" data-toggle="tooltip"> <i class="material-icons"></i>
									</a>
									<a title="Edit" data-toggle="tooltip" onclick="edit(${counselList.seq_counsel})"> <i class="material-icons"></i>
									</a>
									<a class="delete" title="Delete" onclick="deleteChk(${counselList.seq_counsel })" data-toggle="modal" href=""> <i class="material-icons"></i>
									</a>
								</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/comm/modal/deleteChkModal.jsp" />
</body>
<script>
	//선택한 게시글 번호
	var seqCounsel

	/*
	* delete modal 실행.
	*/
	function deleteChk(seq_counsel){
		seqCounsel = seq_counsel
		$('#deleteModal').modal()
	}

	/*
	* modal에서 delete 버튼을 눌렀을 때.
	*/
	function deleteCommit(){
		//삭제버튼누른 게시물의 번호
		//var seq_counsel = $(this).parent().parent().children('.seq_counsel').text()

		$.ajax({
			url : '/counsel/delete/' + seqCounsel,
			type : 'GET',
			success : function(data) {
				alert('삭제완료')
				location.reload();
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
				alert(request.responseText);
			}
		})
	}

	/*
	 *  글쓰기 버튼 클릭시, counselCreateForm으로 이동. 
	 */
	function addNew() {
		//location.replace('/counsel/create');
		location.href = '/counsel/create';
	}

	/*
	 *  제목 클릭 시, 해당 글 내용 보기.
	 */
	function counselDetail(seq_counsel, lockchk) {
		seqCounsel = seq_counsel	//클릭한 게시글 번호

		//비밀글일 때.
		if(lockchk == true){
			//lockModal의 hidden에 해당 게시판 번호와 비밀번호여부 넘김.
			$('#seq_counsel').val(seq_counsel);
			$('#lockChk').val(false);
			$('#lockModal').modal()
		}else{
			counselNoLockDetail();
		}
	}

	/*
	* 비밀글 아닌 글 자세히 보기
	*/
	function counselNoLockDetail(){
		var form = document.createElement("form");
		 form.action = "/counsel/"+seqCounsel;
	     form.method = "post";
	     document.body.appendChild(form);
	     form.submit();
	}

	/*
	 *  게시글 수정 페이지로 이동
	 */
	function edit(seq_counsel) {
		seqCounsel = seq_counsel
		location.href = 'update/' + seq_counsel
	}

	/*
	 *  검색 옵션 선택 시, input hidden값 변경.
	 */
	function searchOption(search_option) {
		$('#search_option').val(search_option)
	}

	/*
	 * 검색값 전송.
	 */
	function searchSubmit() {
		var search_text = $('#search_query').val()
		if (search_text != '') {
			$('#searchForm').submit();
		} else {
			alert('검색어를 입력하세요')
		}
	}
	
	/*
	* '검색초기화'버튼 클릭 시, 전체보기로 이동
	*/
	function cansel(){
		location.href="/counsel/list";
	}

	/*
	* 로그아웃
	*/
	function logout(){
		location.href="/counsel/user/logout"
	}

	/*
	* admin 권한일 때, 사용자 추가 modal
	*/
	function addUser(){
		$('#modal_location').modal();		
	}
</script>
<script>
	/*
	 * LockModal Javascript
	 */

	 //비밀글 클릭 시, 비밀번호 대조
	 function passwordSubmit() {
		var password = $('#lockPassword').val()
		var seq_counsel = $('#seq_counsel').val()

		$.ajax({
			url : '/counsel/accessLock',
			data : {'lockPassword' : password, 'seq_counsel' : seq_counsel},
			type : 'POST',
			success : function(data) {
				$('#lockChk').val(true);
				$('#accessLockForm').attr('action', '/counsel/'+seq_counsel)
				$('#accessLockForm').submit();
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
				alert(request.responseText)
			}
		})
	}
		
	/*
	*	index로 돌아가기
	*/
	function backIndex(){
		location.href="/"
	} 	
</script>

<script>
	/*
	 * addUserModal Javascript
	 */

	var idChk = false;	//id 유효성
	var pwdChk = false;	//비밀번호 유효성
	
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
	});
	
	//id 유효성 체크
	function checkId(event) {
		var id = $('#id').val();
		var idMsg = $('#idMsg');
	
		if (id == '') {
			showErrorMsg(idMsg, "필수 정보입니다");
			return false;
		}		
	
		idcheck = false;
	
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
					return false;
				}
			},
			error : function(request, status, error) {
				console.log("code:" + request.status + "\n"
						+ "message:" + request.responseText + "\n"
						+ "error:" + error);
				alert(request.responseText);
	
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
	
		pwdChk = false;
	
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
	function addUserSubmit(){
		if (idChk && pwdChk) {
			$('#addUserForm').submit();
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
</script>
</html>