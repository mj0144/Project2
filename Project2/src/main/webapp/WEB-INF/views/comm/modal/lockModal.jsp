<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 비밀글 비밀번호 Modal -->
<div class="modal fade" id="lockModal">
	<div class="modal-dialog modal-add">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">비밀글</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<form action="/counsel/accessLock" method="post" id="accessLockForm">
					<div class="form-group">
						<i class="fa fa-user"></i> <input type="password" class="form-control" id="lockPassword" name="lockPassword" placeholder="비밀번호를 입력하세요" required="required">
					</div>
					<div class="form-group">
						<input type="button" onclick="passwordSubmit()" class="btn btn-primary btn-block btn-lg" value="확인">
					</div>
					<input type="hidden" name="lockChk" id="lockChk" value="">
				</form>
				<input type="hidden" name="seq_counsel" id="seq_counsel" value="">
			</div>
		</div>
	</div>
</div>
