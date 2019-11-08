<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div class="mypage_tab" style="width: 100%;height: 100%">
		<div>사원번호	: </div>
		<div>${user.getEmpno()} </div>
		<div>사원이름 	: </div>
		<div>${user.getEname()} </div>
		<div>job 	: </div>
		<div>${user.getJob()} </div>
		<div>mgr 	: </div>
		<div>${user.getMgr()} </div>
		<div>hiredate: </div>
		<div>${user.getHireDate()} </div>
		<div>sal 	: </div>
		<div>${user.getSal()} </div>
		<div>comm 	: </div>
		<div>${user.getComm()} </div>
		<div>deptno 	: </div>
		<div>${user.getDeptno()} </div>
		<div>dname 	: </div>
		<div>${user.getDname()} </div>
		<div>
		<c:forEach items="${company}" var="var">
		${var.getDeptno()} ${var.getDname()} ${var.getLoc()}</br>
		</c:forEach>
		</div>
		<div><br> <a id="back_login" href="#">돌아가기</a></div>
	</div>
	
	
