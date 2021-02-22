<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 로그인 Modal HTML -->
<div id="loginModal" class="modal fade">
	<div class="modal-dialog modal-login">
		<div class="modal-content" style="background-color: rgb(152, 153, 154);">
			<div class="modal-header">			
				<h4 class="modal-title">Member Login</h4>	
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<form action="/counsel/user/login" method="post" id="loginForm">
					<div class="form-group">
						<input type="text" class="form-control" id="id" name="id" placeholder="아이디를 입력하세요" required="required">		
					</div>
					<div class="form-group">
						<input type="password" class="form-control" id="password" name="password" placeholder="비밀번호를 입력하세요" required="required">	
					</div>        
					<div class="form-group">
						<button type="submit" class="btn btn-light btn-lg btn-block login-btn">Login</button>
					</div>
					<div class="form-group">
						<button type="button" class="btn btn-light btn-lg btn-block login-btn" onclick="joinPage()">회원가입</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>     