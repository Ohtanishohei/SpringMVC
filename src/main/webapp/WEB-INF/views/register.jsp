<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>register</title>
	<style type="text/css">
    *{margin:0px;padding:0px;}
    ul{
      width:400px;
      list-style:none;
      margin:50px auto;
    }
    li{
      padding:12px;
      position:relative;
    }
    label{
      width:90px;
      display:inline-block;
      float:left;
      line-height:30px;
    }
    input[type='text'],input[type='password']{
      height:30px;
      width:250px;
    }
    img{
      margin-left:10px;
    }
    input[type="submit"]{
      margin-left:45px;
      padding:5px 5px;
    }
  </style>
</head>
<body>
<form action="register" method="post">
  <ul>
    <li>
      <label>UserID：</label>
      <input type="text" name="username" value="${username_value}" placeholder="ユーザーIDを入力してください"/>
      <p>${username_error}</p>
    </li>
    <li>
      <label>Password：</label>
      <input type="password" name="password" value="${password_value}" placeholder="パスワードを入力してください" />
      <p>${password_error}</p>
    </li>
    <li>
      <label>再度入力：</label>
      <input type="password" name="repassword" placeholder="もう一回パスワードを入力してください" />
      <p>${repassword_error}</p>
    </li>
    <li>
      <label>Email：</label>
      <input type="text" name="email" value="${email_value}" placeholder="メールアドレスを入力してください" />
      <p>${email_error}</p>
    </li>
    <li>
      <label>再度入力：</label>
      <input type="text" name="reemail" placeholder="もう一回メールアドレスを入力してください" />
      <p>${reemail_error}</p>
    </li>
    <li>
      <input type="submit" value="login" /><a href="login" class="button">Cancel</a>
    </li>
  </ul>
</form>
</body>
</html>
