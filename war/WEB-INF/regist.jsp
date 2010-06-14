<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<html>
<head>
	<title>登録とかします。</title>
</head>
<body>
<font color="red">${result}</font>
登録 / 削除 します<br />
<form method="get" action="/regist">
	<select name="mode">
		<option value="regist">登録</option>
		<option value="delete">削除</option>
	</select><br />
	Twitter:<input type="text" name="twitter" value="" /><br />
	Github:<input type="text" name="github" value="" /><br />
	API Key:<input type="text" name="apikey" value="" /><br />
	<input type="submit" />
</form>
<a href="/">戻る</a>
</body>
</html>
