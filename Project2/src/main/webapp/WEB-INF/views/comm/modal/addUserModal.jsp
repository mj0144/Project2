<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- 유저추가 Modal HTML -->
<div role="dialog" class="modal fade" id="modal_location">
	<div class="modal-dialog modal-add">
		<div class="modal-content">
			<div class="modal-header">				
				<h4 class="modal-title">[admin] Add User</h4>
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
			</div>
			<div class="modal-body">
				<form action="/counsel/admin/addUser" method="post" id="addUserForm">
					<div class="form-group">
						<i class="fa fa-user"></i>
						<input type="text" class="form-control" id="id" name="id" placeholder="id" required="required">
						<span class="error_msg" id="idMsg" style="display: none"></span>										
					</div>
					<div class="form-group">
						<i class="fa fa-lock"></i>
						<input type="password" class="form-control" id="password" name="password" placeholder="Password" required="required">
						<span class="error_msg" id="pwdMsg" style="display: none"></span>					
					</div>
					<div class="form-group">
						<i class="fa fa-lock"></i>
						<input type="password" class="form-control" id="passwordcheck" placeholder="Password" required="required">
						<span class="error_msg" id="pwdchkMsg" style="display: none"></span>					
					</div>
					<div class="form-group">
						<input type="button" onclick="addUserSubmit()" class="btn btn-primary btn-block btn-lg" value="확인">
					</div>
				</form>
			</div>
		</div>
	</div>
</div>     
