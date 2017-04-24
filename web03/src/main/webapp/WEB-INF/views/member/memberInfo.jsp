<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>
<script>
$(document).ready(function(){
	$("#btnUpdate").click(function(){
		document.form1.action="/web03/member/memberUpdate";
		document.form1.submit();
	});
});
</script>
</head>
<body>
<form name="form1" method="post">
이름 : <input name="username" value="${dto.username}"><br>
아이디 : <input name="userid" value="${dto.userid}" readonly><br>
비밀번호 : <input type="password" name="userpw" value="${dto.userpw}"><br>
이메일: <input name="email" value="${dto.email}"><br>
<input type="submit" value="수정" id="btnUpdate">
</body>
</html>