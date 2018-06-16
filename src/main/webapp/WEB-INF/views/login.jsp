<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>login</title>
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
<form action="login" method="post">
  <ul>
    <li>
      <label>UserID：</label>
      <input type="text" name="username" placeholder="ユーザーIDを入力してください"/>
    </li>
    <li>
      <label>Password：</label>
      <input type="password" name="password" placeholder="パスワードを入力してください" />
    </li>
    <li>
      <input type="submit" value="login" /><a href="register" class="button">register</a><a href="forget" class="button">forget</a>
    </li>
  </ul>
</form>
</body>
</html>
