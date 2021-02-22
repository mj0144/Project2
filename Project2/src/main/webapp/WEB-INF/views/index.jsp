<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html lang="en" class="h-100">
<head>
<meta charset="UTF-8">
<link href="../resources/css/index.css" rel="stylesheet">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<title>Counsel</title>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.bg-white {
	color: black;
}
</style>
<script>
	
</script>
</head>
<body class="d-flex h-100 text-center text-white bg-dark">
	<div class="cover-container d-flex w-100 h-100 p-3 mx-auto flex-column">
		<header class="mb-auto">
			<div>
				<h3 class="float-md-start mb-4" style="float: left; margin:5px;">Counsel</h3>
				<!-- 인증된 사용자가 아닐 때, -->
				<sec:authorize access="isAnonymous()">
					<nav class="nav nav-masthead justify-content-center float-md-end" style="float: right">
						<a onclick="loginModal()" data-toggle="modal" class="nav-link active" aria-current="page" href="#">login</a>
						
						
					</nav>
				</sec:authorize>
				<!-- 인증된 사용자일 때. -->
				<sec:authorize access="isAuthenticated()">
					<nav class="nav nav-masthead justify-content-center float-md-end" style="float: right; margin:5px;">
						<a class="nav-link active" onclick="logout()" href="#">로그아웃</a>
					</nav>
					<nav class="nav nav-masthead justify-content-center float-md-end" style="float: right; margin:5px;">
						<a class="nav-link active" onclick="goList()" href="#">게시판</a>
					</nav>
				</sec:authorize>
				<!-- 로그인 실패 시, security session에서 예외 문구 출력. -->
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION }">
				<div class="col-md-6 offset-md-4">
					<p style="color: red;">${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message }</p>
					<!-- 해당 예외 세션 제거. -->
					<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
				</div>
			</c:if>
			</div>
			
		</header>
		<main class="px-3">
			<h1 id="content"></h1>
			<p class="lead" id="time_content"></p>
			<p class="lead">
				<a href="http://naver.com/" target="_blank" class="btn btn-lg btn-secondary fw-bold border-white bg-white">Learn more</a>
			</p>
		</main>
		<footer class="mt-auto text-white-50"> </footer>
	</div>
	<!-- modal -->
	<jsp:include page="/WEB-INF/views/comm/modal/loginModal.jsp" />
</body>
<script>
	//1초마다 time_format실행
	$(document).ready(function() {
		setInterval(time_format, 1000)
	})

	/*
	 * 로그인 모달 
	 */
	function loginModal() {
		$('#loginModal').modal();
	}

	/*
	* 모달 닫기
	*/
	
	function closeModal() {
		$('#loginModal').modal('hide')
	}

	//현재시간을 특정format으로 연결.
	function time_format() {
		var day = new Date()

		//현재 날짜,시간을 얻어 하나의 구분자를 포함하여 하나의 문자열로 이어줌
		var time = [ day.getFullYear(), day.getMonth() + 1, day.getDate() ]
				.join("-")
		var time2 = [ day.getHours(), day.getMinutes(), day.getSeconds() ]
				.join(":")
		time = time + " " + time2

		//시간에 따른 문구출력
		print_time(time, day)
	}

	//시간에 맞춘 메세지 출력
	function print_time(time, day) {
		var hour = day.getHours()

		if (hour >= 0 && hour < 13) {
			content_text = '좋은 아침!'
		} else if (hour >= 12 && hour < 19) {
			content_text = '활기찬 오후!'
		} else {
			content_text = '잘자요!'
		}

		console.log(time)
		$('#time_content').text(time)
		$('#content').text(content_text)

	}

	//로그아웃
	function logout() {
		location.href = "/counsel/user/logout"
	}

	/*
	*	리스트로 들어가기
	*/
	function goList(){
		location.href="/counsel/list"
	}
</script>

<script>
	/*
	 * LoginModal Javacsript
	 */

	/*
	*	회원가입 폼을 이동.
	*/
	function joinPage() {
		location.href = "/counsel/user/join";
	}
</script>
</html>
