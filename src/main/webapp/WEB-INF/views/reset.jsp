<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
	<title>forget</title>
	<style type="text/css">
    *{margin:0px;padding:0px;}
    ul{
      width:700px;
      list-style:none;
      margin:50px auto;
    }
    li{
      padding:12px;
      position:relative;
    }
    label{
      width:150px;
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
<form action="reset" method="post">
  <ul>
 <li>
      <label>新パスワード：</label>
      <input type="password" name="email" placeholder="新パスワードを入力してください" />
    </li>
    <li>
      <label>再度入力：</label>
      <input type="password" name="reemail" placeholder="もう一回パスワードを入力してください" />
    </li>
    <li>
      <input type="submit" value="send" />
    </li>
    <li>
    <a href="login" class="button">Cancel</a>
    </li>
  </ul>
</form>
</body>
</html>
