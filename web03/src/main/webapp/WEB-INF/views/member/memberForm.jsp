<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-1.12.4.js"></script>
</head>
<body>
<form id="form1" method="post" action="/web03//member/memberInsert">
이름 : <input name="username"><br>
아이디 : <input name="userid"><br>
비밀번호 : <input type="password"><br>
이메일: <input name="email"><br>
<input type="submit" value="확인">
</form>

<span style="color: red";">${message}</span>

</body>
</html>