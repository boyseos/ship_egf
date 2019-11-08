<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form id="login_form">
	<div class="login_tab">
		<div>
			부서명
		</div>
		<div>
			<input id="login_userdept" class="input_text" type="text" name="dName"/>
		</div>
		<div>
			아이디
		</div>
		<div>
			<input id="login_username" class="input_text" type="text" name="empNo"/>
		</div>
		<div>
			비밀번호
		</div>
		<div>
			<input id="login_userpass" class="input_text" type="text" name="eName">
		</div>
		<div>
			<input type="hidden" name="action" value="login"/>
			<input type="hidden" name="page" value="mypage"/>
			<input id="login_btn" type="button" value="로그인"/>
		</div>
			<div>
			<h3 id="join_a"><a id="a_join"href="#">회원가입</a></h3>
			
			</div>
	</div>
</form>